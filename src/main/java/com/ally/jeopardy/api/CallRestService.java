package com.ally.jeopardy.api;

import org.springframework.web.client.RestTemplate;

import com.ally.jeopardy.models.Question;

public class CallRestService {
	
	public Question[] callRestService() {
		RestTemplate restTemplate = new RestTemplate();
		Question[] question = restTemplate.getForObject("http://jservice.io/api/random", Question[].class);
		return question;
	}

}
