package net.devk.analyzer.github;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.devk.analyzer.github.dto.Commit;
import net.devk.analyzer.github.dto.GithubSearchResult;
import net.devk.analyzer.github.dto.Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubAnalyzerApplicationTests {

	@Autowired
	private GithubServices githubServices;

	@Test
	public void testFindRepositories() {
		GithubSearchResult<Repository> repositories = githubServices.findRepositories("kalah", 1, 10);
		Assert.assertEquals(10, repositories.getItems().length);
	}

	@Test
	public void testFindContributors() {
		List<String> contributors = githubServices.findContributors("alireza-kasraei/kalah");
		Assert.assertEquals(1, contributors.size());
	}

	@Test
	public void testFindUserImpact() {
		List<Commit> commits = githubServices.findCommits("alireza-kasraei/kalah", 1, 100);
		Map<String, Long> userImpact = githubServices.findUserImpact(commits);
		Assert.assertNotNull(userImpact);
	}

}
