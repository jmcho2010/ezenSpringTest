package com.example.demo;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

	
	// signup get/post 완성해오기
	// 회원가입 진행이 되도록
	// 참고사항 : questionForm
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {

		return "signup_form";
	}

	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		// == vs equals 차이점?
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		
		try {
			this.userService.create(userCreateForm.getUsername(), 
					  userCreateForm.getPassword1(),
					  userCreateForm.getEmail());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		}

		return "redirect:/list";
	}
}
