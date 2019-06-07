package net.devk.analyzer.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GithubSearchResult<T> {

	@JsonProperty("total_count")
	private int totalCount;

	@JsonProperty("incomplete_results")
	private boolean incompleteResults;

	private T[] items;

}
