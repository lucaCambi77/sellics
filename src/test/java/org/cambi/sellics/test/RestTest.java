package org.cambi.sellics.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cambi.sellics.application.AppConfiguration;
import org.cambi.sellics.application.Application;
import org.cambi.sellics.constant.Constants;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Quick tests to check if rest services are returning 200
 * 
 * @author luca
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class, AppConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class RestTest extends Constants {
	private static final Logger log = LoggerFactory.getLogger(RestTest.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void testGreeting() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	@Order(2)
	public void testEstimate() throws Exception {
		ResponseEntity<String> entity = restTemplate
				.getForEntity("http://localhost:" + this.port + "/estimate?keyword=samsung", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());

		log.info(entity.getBody());
	}
}
