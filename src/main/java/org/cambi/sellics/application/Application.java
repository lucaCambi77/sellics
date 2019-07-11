package org.cambi.sellics.application;

import java.net.URISyntaxException;

import org.cambi.sellics.constant.Constants;
import org.cambi.sellics.service.ISuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * SpringBootApplication with RestController and /estimate path for calculation
 * of score from keyword
 * 
 * @author luca
 *
 */
@SpringBootApplication
@RestController
@Slf4j
public class Application extends Constants {

	@Autowired
	private ISuggestionService suggestionService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/")
	public String home() {
		return "Hello World";
	}

	@GetMapping(path = "/estimate")
	public String estimate(@RequestParam("keyword") String keyword) throws JsonProcessingException, URISyntaxException {
		log.info("Requesting new score for keyword " + keyword + " ...");

		return objectMapper.writeValueAsString(suggestionService.getScore(keyword));

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
