package blog.kzerojunblog.service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import blog.kzerojunblog.dto.request.auth.SignInRequestDto;
import blog.kzerojunblog.dto.response.auth.SignInResponseDto;
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
	private String validEmail = "user@example.com";
	private String validPassword = "password";
	private String invalidEmail = "wrong@example.com";
	private String invalidPassword = "wrongpassword";
	private String validToken = "validToken";

	@BeforeEach
	public void setUp() {
		validUser = new UserEntity(validEmail, validPassword, "nickname", "123-4567", "address", "detail", "image", true);
	}

	@Test
	public void signIn_Success() {
		when(userRepository.findByEmail(validEmail)).thenReturn(validUser);
		when(passwordEncoder.matches(validPassword, validUser.getPassword())).thenReturn(true);
		when(jwtProvider.create(validEmail)).thenReturn(validToken);

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail(validEmail);
		requestDto.setPassword(validPassword);

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(requestDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		SignInResponseDto responseBody = (SignInResponseDto) response.getBody();
		assertNotNull(responseBody);
		assertEquals(validToken, responseBody.getToken());
	}

	@Test
	public void signIn_Fail_InvalidEmail() {
		when(userRepository.findByEmail(invalidEmail)).thenReturn(null);

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail(invalidEmail);
		requestDto.setPassword(validPassword);

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(requestDto);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	@Test
	public void signIn_Fail_InvalidPassword() {
		when(userRepository.findByEmail(validEmail)).thenReturn(validUser);
		when(passwordEncoder.matches(invalidPassword, validUser.getPassword())).thenReturn(false);

		SignInRequestDto requestDto = new SignInRequestDto();
		requestDto.setEmail(validEmail);
		requestDto.setPassword(invalidPassword);

		ResponseEntity<? super SignInResponseDto> response = authServiceImplement.signIn(requestDto);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
}
