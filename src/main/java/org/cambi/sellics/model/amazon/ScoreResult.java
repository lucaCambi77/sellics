package org.cambi.sellics.model.amazon;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The score should be in the range [0 → 100] and represent the estimated
 * search-volume (how often Amazon customers search for that exact keyword). A
 * score of 0 means that the keyword is practically never searched for, 100
 * means that this is one of the hottest keywords right now
 */
public class ScoreResult {

	@JsonProperty(value = "keyword")
	private String keyword;
	private int score = 0;
	private String exception = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
