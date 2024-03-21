package blog.kzerojunblog.service.implement;

import blog.kzerojunblog.dto.request.auth.SignInRequestDto;
import blog.kzerojunblog.dto.request.auth.SignUpRequestDto;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.dto.response.auth.SignInResponseDto;
import blog.kzerojunblog.dto.response.auth.SignUpResponseDto;
import blog.kzerojunblog.entity.UserEntity;
import blog.kzerojunblog.provider.JwtProvider;
import blog.kzerojunblog.repository.UserRepository;
import blog.kzerojunblog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplement implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;


	@Override
	public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
		try {

			if (isEmailExist(dto.getEmail())) {
				return SignUpResponseDto.duplicateEmail();
			}

			if (isNicknameExist(dto.getNickname())) {
				return SignUpResponseDto.duplicateNickname();
			}

			if (isTelNumberExist(dto.getTelNumber())) {
				return SignUpResponseDto.duplicateTelNumber();
			}

			String encodedPassword = passwordEncoder.encode(dto.getPassword());
			dto.setPassword(encodedPassword);

			UserEntity userEntity = new UserEntity(dto);
			userRepository.save(userEntity);

		} catch (Exception exception) {
			log.error("Error during sign up :", exception);
			return ResponseDto.databaseError();
		}

		return SignUpResponseDto.success();
	}

	private boolean isEmailExist(String email) {
		return userRepository.existsByEmail(email);
	}

	private boolean isNicknameExist(String nickname) {
		return userRepository.existsByNickname(nickname);
	}

	private boolean isTelNumberExist(String telNumber) {
		return userRepository.existsByTelNumber(telNumber);
	}

	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		try {
			UserEntity userEntity = userRepository.findByEmail(dto.getEmail());
			if (userEntity == null) {
				return SignInResponseDto.signInFailed();
			}

			boolean isMatched = passwordEncoder.matches(dto.getPassword(), userEntity.getPassword());

			if (!isMatched) {
				return SignInResponseDto.signInFailed();
			}
			String token = jwtProvider.create(dto.getEmail());
			return ResponseEntity.ok(SignInResponseDto.success(token));

		} catch (Exception exception) {
			log.error("Error during sign in: ", exception);
			return ResponseDto.databaseError();
		}
	}
}
