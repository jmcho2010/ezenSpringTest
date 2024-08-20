package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

//	@Autowired
//	private AnswerRepository aRepository;
	
	private final AnswerRepository aRepository;
	
	
	public void create(Question q, String content) {
		
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(q);
		this.aRepository.save(answer);
		
	}
	
	
	
	
	
	
	
	
}
