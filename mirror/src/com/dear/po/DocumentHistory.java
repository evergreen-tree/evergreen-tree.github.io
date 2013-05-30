package com.dear.po;

public class DocumentHistory {
	private String ID;
	private String name;
	private String candidate;
	private String career;

	private String fullText;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

}
