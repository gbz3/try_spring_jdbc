package com.github.gbz3.try_spring_webmvc.app;

import java.io.Serializable;

public class EchoForm implements Serializable {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
