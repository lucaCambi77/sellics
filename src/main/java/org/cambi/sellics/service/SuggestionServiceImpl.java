package org.cambi.sellics.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URIBuilder;
import org.cambi.sellics.constant.Constants;
import org.cambi.sellics.model.amazon.ScoreResult;
import org.cambi.sellics.model.amazon.Suggestion;
import org.cambi.sellics.model.amazon.SuggestionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.reactivex.Observable;

@Service
public class SuggestionServiceImpl extends Constants implements ISuggestionService {

	private static final Logger log = LoggerFactory.getLogger(SuggestionServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ScoreResult getScore(String keyword) throws URISyntaxException {

		ScoreResult result = new ScoreResult();
		result.setKeyword(keyword);

		try {

			result.setScore(calculateScore(keyword, getSuggestionsList(keyword)));
		} catch (Exception e) {
			result.setException(e.getMessage());
		}
		return result;
	}

	private List<SuggestionResult> getSuggestionsList(String keyword) throws URISyntaxException {

		List<SuggestionResult> results = new ArrayList<>();

		ExecutorService executor = Executors.newFixedThreadPool(keyword.length());

		List<Future<SuggestionResult>> commands = new ArrayList<Future<SuggestionResult>>();
		for (int i = 1; i <= keyword.length(); i++) {
			FutureTask<SuggestionResult> myTask = new FutureTask<SuggestionResult>(new SuggestionServiceCallable(
					new URIBuilder(AMAZON_COMPLETION_SUGGESTIONS).addParameter("mid", MID_VALUE)
							.addParameter("alias", ALIAS_VALUE).addParameter("prefix", keyword.substring(0, i)),
					restTemplate));
			executor.execute(myTask);
			commands.add(myTask);
		}

		Observable.fromIterable(commands).take(9, TimeUnit.SECONDS).subscribe(

				s -> results.add(s.get()), e -> log.error("Error appeared", e),
				() -> log.debug("Processing of commands is finished. Sent '{}' requests.", results.size()));

		executor.shutdown();

		return results;
	}

	private int calculateScore(String keyword, List<SuggestionResult> results) {

		Pattern pattern = Pattern.compile("\\b" + keyword + "\\b");
		long numberOfMatches = results.stream().map(SuggestionResult::getSuggestions).flatMap(Collection::stream)
				.map(Suggestion::getValue).filter(f -> !f.isEmpty()).filter(f -> pattern.matcher(f).find(0)).count();

		return (int) Math.round((double) numberOfMatches / (results.size() * 10) * 100);
	}

}
