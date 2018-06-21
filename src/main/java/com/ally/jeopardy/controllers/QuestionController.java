package com.ally.jeopardy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ally.jeopardy.api.CallRestService;
import com.ally.jeopardy.models.Question;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionController {
	
	private Question[] questions;
	private int points = 0;
	
	@GetMapping("")
	public ModelAndView jeopardyView() {
		CallRestService crs = new CallRestService();
		questions = crs.callRestService();
		while(questions[0].getQuestion().compareToIgnoreCase("") == 0)
			questions = crs.callRestService();
		if(questions[0].getValue() == 0)
			questions[0].setValue(200);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		
		mv.addObject("question", questions[0].getQuestion());
		mv.addObject("answer", questions[0].getAnswer());
		mv.addObject("points",points);
		mv.addObject("pointvalue",questions[0].getValue());
		
		System.out.println("Answer: "+questions[0].getAnswer());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView jeopardyPost(@RequestParam(value="answer") String userAnswer) {
		String answer = questions[0].getAnswer();
		if(userAnswer.compareToIgnoreCase(answer) == 0)
			points+=questions[0].getValue();
		else
			points-=questions[0].getValue();
		return jeopardyView();
	}
}
