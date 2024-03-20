package blog.kzerojunblog.service;

import blog.kzerojunblog.dto.request.user.PatchNicknameRequestDto;
import blog.kzerojunblog.dto.request.user.PatchProfileImageRequestDto;
import blog.kzerojunblog.dto.response.user.GetSignInUserResponseDto;
import blog.kzerojunblog.dto.response.user.GetUserResponseDto;
import blog.kzerojunblog.dto.response.user.PatchNicknameResponseDto;
import blog.kzerojunblog.dto.response.user.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

	ResponseEntity<? super GetUserResponseDto> getUser(String email);

	ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto,
			String email);

	ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
			PatchProfileImageRequestDto dto, String email);
}
