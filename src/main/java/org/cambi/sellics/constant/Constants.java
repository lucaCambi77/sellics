package org.cambi.sellics.constant;

import org.cambi.sellics.model.amazon.SuggestionResult;

/**
 * Class of amazon completion api constants
 * 
 * @author luca
 *
 */
public class Constants {

	/**
	 * We are using suggestions as reverse engineering of Network informations from
	 * a Browser. See {@link SuggestionResult}
	 */
	public static final String AMAZON_COMPLETION_SUGGESTIONS = "https://completion.amazon.co.uk/api/2017/suggestions";
	/**
	 * See
	 * https://docs.developer.amazonservices.com/en_US/dev_guide/DG_Endpoints.html
	 */
	protected static final String MID_VALUE = "APJ6JRA9NG5V4";
	/**
	 * Alias value
	 */
	protected static final String ALIAS_VALUE = "aps";
}
