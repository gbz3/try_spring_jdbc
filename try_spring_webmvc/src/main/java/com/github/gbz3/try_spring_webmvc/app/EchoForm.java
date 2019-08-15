package com.github.gbz3.try_spring_webmvc.app;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EchoForm implements Serializable {

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[_a-zA-Z0-9]+@[._a-zA-Z0-9]+")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
