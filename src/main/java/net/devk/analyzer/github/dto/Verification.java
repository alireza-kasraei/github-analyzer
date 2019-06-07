package net.devk.analyzer.github.dto;

import lombok.Data;

@Data
public class Verification {

	private boolean verified;
	private String reason;
	private String signature;
	private String payload;

}
