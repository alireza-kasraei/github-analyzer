package net.devk.analyzer.github;

import java.util.List;
import java.util.Map;

import net.devk.analyzer.github.dto.Commit;
import net.devk.analyzer.github.dto.GithubSearchResult;
import net.devk.analyzer.github.dto.Repository;

public interface GithubServices {

	public GithubSearchResult<Repository> findRepositories(String key, int pageNumber, int pageSize);

	public List<String> findContributors(String fullRepoName);

	public Map<String, Long> findUserImpact(List<Commit> commits);

	public List<Commit> findCommits(String fullRepoName, int pageNumber, int pageSize);

}
