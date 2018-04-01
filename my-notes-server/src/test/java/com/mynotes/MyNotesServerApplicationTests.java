package com.mynotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mynotes.model.Note;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyNotesServerApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();	
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void createTest() {
		//TODO: created integration tests for all methods of the REST controller
	}	
}
