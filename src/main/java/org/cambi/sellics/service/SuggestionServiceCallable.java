/**
 * 
 */
package org.cambi.sellics.service;

import java.util.concurrent.Callable;

import org.apache.http.client.utils.URIBuilder;
import org.cambi.sellics.model.amazon.SuggestionResult;
import org.springframework.web.client.RestTemplate;

/**
 * @author luca
 *
 */

public class SuggestionServiceCallable implements Callable<SuggestionResult> {

	private RestTemplate restTemplate;
	private URIBuilder uriBuilder;

	/**
	 * @param addParameter
	 * @param restTemplate2
	 */
	public SuggestionServiceCallable(URIBuilder uriBuilder, RestTemplate restTemplate) {
		this.uriBuilder = uriBuilder;
		this.restTemplate = restTemplate;
	}

	@Override
	public SuggestionResult call() throws Exception {

		return restTemplate.getForObject(uriBuilder.build().toURL().toURI(), SuggestionResult.class);
	}

}