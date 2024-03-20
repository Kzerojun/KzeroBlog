package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import lombok.Getter;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {

	private PutFavoriteResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

	}

	public static ResponseEntity<PutFavoriteResponseDto> success() {
		PutFavoriteResponseDto result = new PutFavoriteResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistedBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,
				ResponseMessage.NOT_EXISTED_BOARD);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,
				ResponseMessage.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	}


}
