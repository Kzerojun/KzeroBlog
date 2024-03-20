package blog.kzerojunblog.dto.response.auth;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {

	private SignUpResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
	}

	public static ResponseEntity<SignUpResponseDto> success() {
		SignUpResponseDto result = new SignUpResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> duplicateEmail() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL,
				ResponseMessage.DUPLICATE_EMAIL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	public static ResponseEntity<ResponseDto> duplicateNickname() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_NICKNAME,
				ResponseMessage.DUPLICATED_NICKNAME);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	public static ResponseEntity<ResponseDto> duplicateTelNumber() {
		ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_TEL_NUMBER,
				ResponseMessage.DUPLICATED_TEL_NUMBER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

}
