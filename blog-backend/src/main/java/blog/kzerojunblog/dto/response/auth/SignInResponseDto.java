package blog.kzerojunblog.dto.response.auth;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

	private String token;
	private int expirationTime;

	private SignInResponseDto(String token) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.token = token;
		this.expirationTime = 3600;
	}

	public static ResponseEntity<SignInResponseDto> success(String token) {
		SignInResponseDto result = new SignInResponseDto(token);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> signInFailed() {
		ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAILED,
				ResponseMessage.SIGN_IN_FAILED);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	}
}
