package blog.kzerojunblog.service;

import blog.kzerojunblog.dto.request.auth.SignInRequestDto;
import blog.kzerojunblog.dto.request.auth.SignUpRequestDto;
import blog.kzerojunblog.dto.response.auth.SignInResponseDto;
import blog.kzerojunblog.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

	ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

	ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
