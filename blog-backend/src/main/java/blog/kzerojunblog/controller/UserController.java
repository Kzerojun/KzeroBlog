package blog.kzerojunblog.controller;

import blog.kzerojunblog.dto.request.user.PatchNicknameRequestDto;
import blog.kzerojunblog.dto.request.user.PatchProfileImageRequestDto;
import blog.kzerojunblog.dto.response.user.GetSignInUserResponseDto;
import blog.kzerojunblog.dto.response.user.GetUserResponseDto;
import blog.kzerojunblog.dto.response.user.PatchNicknameResponseDto;
import blog.kzerojunblog.dto.response.user.PatchProfileImageResponseDto;
import blog.kzerojunblog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
			@AuthenticationPrincipal String email) {
		return userService.getSignInUser(email);
	}

	@GetMapping("/{email}")
	public ResponseEntity<? super GetUserResponseDto> getUser(@PathVariable("email") String email) {
		return userService.getUser(email);
	}

	@PatchMapping("/nickname")
	public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(@RequestBody @Valid
	PatchNicknameRequestDto requestBody, @AuthenticationPrincipal String email) {
		return userService.patchNickname(requestBody, email);
	}

	@PatchMapping("/profile-image")
	public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
			@RequestBody @Valid
			PatchProfileImageRequestDto requestBody, @AuthenticationPrincipal String email
	) {
		log.info("이미지 변경");
		return userService.patchProfileImage(requestBody, email);
	}
}

