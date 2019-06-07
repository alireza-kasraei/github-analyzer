package net.devk.analyzer.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Parent {

	private String sha;
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;

}
