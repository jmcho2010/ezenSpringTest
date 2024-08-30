package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
	private Integer id;

	private String subject;

	private String content;
	
	private LocalDateTime createDate;
	
	private List<Answer> answerList;
}
