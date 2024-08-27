package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionResponsitory qr;

//	public List<Question> getList(){
//		return this.qr.findAll();
//	}
	
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.qr.findAll(pageable);
	}
	
	public Question getQuestion(Integer id) throws PpakchimException {
		// 도전과제
		// 존재하지 않는 데이터를 조회할떄는
		// 데이터를 찾을수 없습니다 라는
		// PpakchimException을 구현해볼것.
		Optional<Question> q1 = this.qr.findById(id);
		if(q1.isPresent()) {
			return q1.get();
		}else {
			throw new PpakchimException("데이터를 찾을수 없습니다");
		}
		
	}

	public void create(String subject, String content) {
		// TODO Auto-generated method stub
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.qr.save(q);
	}
	
	
	
	
	
	
}
