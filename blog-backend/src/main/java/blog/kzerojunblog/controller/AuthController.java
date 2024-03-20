package blog.kzerojunblog.controller;

import blog.kzerojunblog.dto.request.auth.SignInRequestDto;
import blog.kzerojunblog.dto.request.auth.SignUpRequestDto;
import blog.kzerojunblog.dto.response.auth.SignInResponseDto;
import blog.kzerojunblog.dto.response.auth.SignUpResponseDto;
import blog.kzerojunblog.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/sign-up")
	public ResponseEntity<? super SignUpResponseDto> signUp(
			@RequestBody @Valid SignUpRequestDto requestBody) {
		return authService.signUp(requestBody);
	}

	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(
			@RequestBody @Valid SignInRequestDto requestBody) {
		return authService.signIn(requestBody);
	}
}
