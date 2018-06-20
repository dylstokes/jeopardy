package com.ally.jeopardy.api;

import org.springframework.web.client.RestTemplate;

import com.ally.jeopardy.models.Question;

public class CallRestService {
	
	private void callRestService() {
		RestTemplate restTemplate = new RestTemplate();
		Question question = restTemplate.getForObject("http://jservice.io/api/random", Question.class);
		System.out.println(question.getQuestion());
	}

}
