package org.cambi.sellics.model.amazon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Suggestion {

	private String suggType;
	private String type;
	private String value;
	private String refTag;
	private Boolean ghost;
	private Boolean help;
	private Boolean xcatOnly;
	private Boolean fallback;
	private Boolean spellCorrected;
	private Boolean blackListed;

	public String getSuggType() {
		return suggType;
	}

	public void setSuggType(String suggType) {
		this.suggType = suggType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRefTag() {
		return refTag;
	}

	public void setRefTag(String refTag) {
		this.refTag = refTag;
	}

	public Boolean getGhost() {
		return ghost;
	}

	public void setGhost(Boolean ghost) {
		this.ghost = ghost;
	}

	public Boolean getHelp() {
		return help;
	}

	public void setHelp(Boolean help) {
		this.help = help;
	}

	public Boolean getXcatOnly() {
		return xcatOnly;
	}

	public void setXcatOnly(Boolean xcatOnly) {
		this.xcatOnly = xcatOnly;
	}

	public Boolean getFallback() {
		return fallback;
	}

	public void setFallback(Boolean fallback) {
		this.fallback = fallback;
	}

	public Boolean getSpellCorrected() {
		return spellCorrected;
	}

	public void setSpellCorrected(Boolean spellCorrected) {
		this.spellCorrected = spellCorrected;
	}

	public Boolean getBlackListed() {
		return blackListed;
	}

	public void setBlackListed(Boolean blackListed) {
		this.blackListed = blackListed;
	}
}
