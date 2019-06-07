package net.devk.analyzer.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Commit {

	private String sha;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("commit")
	private CommitDetails commitDetails;
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("comments_url")
	private String commentsUrl;
	private GithubFullPerson author;
	private GithubFullPerson committer;
	private Parent[] parents;

}
