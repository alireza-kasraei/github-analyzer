package net.devk.analyzer.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class License {

	private String key;
	private String name;
	@JsonProperty("spdx_id")
	private String spdxId;
	private String url;
	@JsonProperty("node_id")
	private String nodeId;

}
