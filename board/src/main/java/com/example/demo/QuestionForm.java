package com.example.demo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter      
@Setter
public class QuestionForm {

	@NotEmpty(message="제목이 없으면 안돼요")
	@Size(max=50)
	private String subject;
	
	@NotEmpty(message="내용이 없으면 안돼요")
	private String content;
	
	
}
