package net.devk.analyzer.github;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.devk.analyzer.github.dto.Commit;
import net.devk.analyzer.github.dto.DatatablesDTO;
import net.devk.analyzer.github.dto.GithubSearchResult;
import net.devk.analyzer.github.dto.Repository;

//@RestController
@Controller
public class GithubController {

	@Autowired
	private GithubServices githubServices;

	@PostMapping(path = "/github/repo")
	@ResponseBody
	public ResponseEntity<DatatablesDTO<Repository>> findRepository(
			@RequestParam(name = "start", defaultValue = "0") int start,
			@RequestParam(name = "length", defaultValue = "10") int length, @RequestParam(name = "draw") int draw,
			@RequestParam(name = "keyword", required = true) String keyword) {
		final int pageNumber = ((start / length) + 1) + ((start % length) != 0 ? 1 : 0);
		GithubSearchResult<Repository> repositories = githubServices.findRepositories(keyword, pageNumber, length);
		return ResponseEntity.ok(new DatatablesDTO<Repository>(Arrays.asList(repositories.getItems()),
				repositories.getTotalCount(), repositories.getTotalCount(), draw));
	}

	@GetMapping(value = "/search")
	public String gotoSearchPage() {
		return "pages/search";
	}

	@GetMapping(value = "/analyze")
	public String gotoResultPage(@RequestParam(name = "repo", required = true) String repo, ModelMap model) {
		model.addAttribute("repoName", repo);
		final List<String> contributors = githubServices.findContributors(repo);
		model.addAttribute("contributors", contributors);
		List<Commit> commits = githubServices.findCommits(repo,1,100);
		Map<String, Long> userImpact = githubServices.findUserImpact(commits);
		model.addAttribute("userImpacts", userImpact);
		return "pages/result";
	}

}
