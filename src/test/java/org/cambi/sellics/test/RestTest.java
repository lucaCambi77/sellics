package org.cambi.sellics.test;

import static org.junit.Assert.assertEquals;

import org.cambi.sellics.application.AppConfiguration;
import org.cambi.sellics.application.Application;
import org.cambi.sellics.constant.Constants;
import org.cambi.sellics.service.SuggestionServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Quick tests to check if rest services are returning 200
 * 
 * @author luca
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, AppConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestTest extends Constants {
	private static final Logger log = LoggerFactory.getLogger(RestTest.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGreeting() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testEstimate() throws Exception {
		ResponseEntity<String> entity = restTemplate
				.getForEntity("http://localhost:" + this.port + "/estimate?keyword=samsung", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());

		log.info(entity.getBody());
	}
}
