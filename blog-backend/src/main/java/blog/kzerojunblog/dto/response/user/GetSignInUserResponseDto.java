package blog.kzerojunblog.dto.response.user;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {

	private String email;
	private String nickname;
	private String profileImage;

	private GetSignInUserResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.email = userEntity.getEmail();
		this.nickname = userEntity.getNickname();
		this.profileImage = userEntity.getProfileImage();
	}

	public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity) {
		GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,
				ResponseMessage.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	}
}
