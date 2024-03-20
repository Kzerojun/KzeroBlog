package blog.kzerojunblog.service.implement;

import blog.kzerojunblog.dto.request.user.PatchNicknameRequestDto;
import blog.kzerojunblog.dto.request.user.PatchProfileImageRequestDto;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.dto.response.user.GetSignInUserResponseDto;
import blog.kzerojunblog.dto.response.user.GetUserResponseDto;
import blog.kzerojunblog.dto.response.user.PatchNicknameResponseDto;
import blog.kzerojunblog.dto.response.user.PatchProfileImageResponseDto;
import blog.kzerojunblog.entity.UserEntity;
import blog.kzerojunblog.repository.UserRepository;
import blog.kzerojunblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

	private final UserRepository userRepository;

	@Override
	public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
		UserEntity userEntity;

		try {
			userEntity = userRepository.findByEmail(email);
			if (userEntity == null) {
				return GetSignInUserResponseDto.noExistUser();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetSignInUserResponseDto.success(userEntity);
	}

	@Override
	public ResponseEntity<? super GetUserResponseDto> getUser(String email) {
		UserEntity userEntity;
		try {
			userEntity = userRepository.findByEmail(email);
			if (userEntity == null) {
				return GetUserResponseDto.noExistUser();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetUserResponseDto.success(userEntity);
	}

	@Override
	public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
			PatchNicknameRequestDto dto, String email) {
		try {
			UserEntity userEntity = userRepository.findByEmail(email);
			if (userEntity == null) {
				PatchNicknameResponseDto.noExistUser();
			}

			String nickName = dto.getNickname();
			boolean existedNickname = userRepository.existsByNickname(nickName);
			if (existedNickname) {
				PatchNicknameResponseDto.duplicateNickname();
			}

			userEntity.updateNickname(nickName);
			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return PatchNicknameResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
			PatchProfileImageRequestDto dto, String email) {

		try {
			UserEntity userEntity = userRepository.findByEmail(email);
			if (userEntity == null) {
				return PatchProfileImageResponseDto.noExistUser();
			}

			String profileImage = dto.getProfileImage();
			userEntity.updateProfileImage(profileImage);
			userRepository.save(userEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return PatchProfileImageResponseDto.success();
	}
}
