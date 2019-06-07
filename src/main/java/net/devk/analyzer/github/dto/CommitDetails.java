package net.devk.analyzer.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommitDetails {

	private GithubBreifPerson author;
	private GithubBreifPerson committer;
	private String message;
	private Tree tree;
	private String url;
	@JsonProperty("comment_count")
	private int commentCount;
	private Verification verification;

}
