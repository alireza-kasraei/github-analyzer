package net.devk.analyzer.github;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.devk.analyzer.github.dto.Commit;
import net.devk.analyzer.github.dto.Contributor;
import net.devk.analyzer.github.dto.GithubSearchResult;
import net.devk.analyzer.github.dto.Repository;

@Service
class GithubServiceImpl implements GithubServices {

	private static final String BASE_URL = "https://api.github.com";

	private static final String SEARCH_REPOSITORIES = "/search/repositories?q={q}&page={page}&per_page={per_page}";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public GithubSearchResult<Repository> findRepositories(String key, int pageNumber, int pageSize) {
		final String url = BASE_URL + SEARCH_REPOSITORIES;
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("q", key);
		uriVariables.put("page", pageNumber);
		uriVariables.put("per_page", pageSize);
		ResponseEntity<GithubSearchResult<Repository>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<GithubSearchResult<Repository>>() {
				}, uriVariables);
		return responseEntity.getBody();

	}

	@Override
	public List<String> findContributors(String fullRepoName) {
		ResponseEntity<List<Contributor>> responseEntity = restTemplate.exchange(
				String.format("%s/repos/%s/contributors", BASE_URL, fullRepoName), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Contributor>>() {
				});
		return responseEntity.getBody().stream().map(Contributor::getLogin).collect(Collectors.toList());
	}

	@Override
	public Map<String, Long> findUserImpact(List<Commit> commits) {
		return commits.stream().map(c -> c.getCommitDetails().getAuthor().getName())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	@Override
	public List<Commit> findCommits(String fullRepoName, int pageNumber, int pageSize) {
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("page", pageNumber);
		uriVariables.put("per_page", pageSize);
		uriVariables.put("repo", fullRepoName);
		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				String.format("%s/repos/%s/commits?page={page}&per_page={per_page}", BASE_URL, fullRepoName),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Commit>>() {
				}, uriVariables);
		return responseEntity.getBody();
	}

}
