package blog.kzerojunblog.service.implement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import blog.kzerojunblog.dto.request.auth.SignInRequestDto;
import blog.kzerojunblog.dto.request.auth.SignUpRequestDto;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.dto.response.auth.SignInResponseDto;
import blog.kzerojunblog.dto.response.auth.SignUpResponseDto;
import blog.kzerojunblog.entity.UserEntity;
import blog.kzerojunblog.provider.JwtProvider;
import blog.kzerojunblog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplementTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtProvider jwtProvider;

	@InjectMocks
	private AuthServiceImplement authServiceImplement;

	private UserEntity validUser;

	private SignUpRequestDto signUpRequestDto;

	@BeforeEach
	public void setUp() {
		signUpRequestDto = createValidSignUpRequestDto();
		validUser = new UserEntity(signUpRequestDto);
	}

	@Test
	public void signUp_Success() {

		when(userRepository.existsByEmail("validEmail")).thenReturn(false);
		when(userRepository.existsByNickname("validNickname")).thenReturn(false);
		when(userRepository.existsByTelNumber("validTelNumber")).thenReturn(false);

		ResponseEntity<? super SignUpResponseDto> response = authServiceImplement.signUp(
				signUpRequestDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Object body = response.getBody();
		assertThat(body).isInstanceOf(SignUpResponseDto.class);
	}

	@Test
	public void signUp_Fail_Duplicate_Email() {

		when(userRepository.existsByEmail("validEmail")).thenReturn(true);

		ResponseEntity<? super SignUpResponseDto> response = authServiceImplement.signUp(
				signUpRequestDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		Object body = response.getBody();
		assertThat(body).isInstanceOf(ResponseDto.class);
	}

	@Test
	public void signUp_Fail_Duplicate_Nickname() {

		when(userRepository.existsByNickname("validNickname")).thenReturn(true);

		ResponseEntity<? super SignUpResponseDto> response = authServiceImplement.signUp(
				signUpRequestDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		Object body = response.getBody();
		assertThat(body).isInstanceOf(ResponseDto.class);
	}

	@Test
	public void signUp_Fail_Duplicate_TelNumber() {

		when(userRepository.existsByTelNumber("validTelNumber")).thenReturn(true);

		ResponseEntity<? super SignUpResponseDto> response = authServiceImplement.signUp(
				signUpRequestDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		Object body = response.getBody();
		assertThat(body).isInstanceOf(ResponseDto.class);
	}

	@Test
	public void signIn_Success() {
		when(userRepository.findByEmail("validEmail")).thenReturn(validUser);
		when(passwordEncoder.matches("validPassword", validUser.getPassword())).thenReturn(true);
		when(jwtProvider.create("validEmail")).thenReturn("validToken");

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail("validEmail");
		requestDto.setPassword("validPassword");

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(
				requestDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		SignInResponseDto responseBody = (SignInResponseDto) response.getBody();
		assertNotNull(responseBody);
		assertEquals("validToken", responseBody.getToken());
	}

	@Test
	public void signIn_Fail_InvalidEmail() {
		when(userRepository.findByEmail("invalidEmail")).thenReturn(null);

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail("invalidEmail");
		requestDto.setPassword("validPassword");

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(
				requestDto);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	@Test
	public void signIn_Fail_InvalidPassword() {
		when(userRepository.findByEmail("validEmail")).thenReturn(validUser);
		when(passwordEncoder.matches("invalidPassword", validUser.getPassword())).thenReturn(false);

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail("validEmail");
		requestDto.setPassword("invalidPassword");

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(
				requestDto);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	private SignUpRequestDto createValidSignUpRequestDto() {
		SignUpRequestDto dto = new SignUpRequestDto();
		dto.setEmail("validEmail");
		dto.setPassword("validPassword");
		dto.setTelNumber("validTelNumber");
		dto.setNickname("validNickname");
		dto.setAgreedPersonal(true);
		dto.setAddress("address");
		dto.setAddressDetail("DETAIL_ADDRESS");
		return dto;
	}
}
