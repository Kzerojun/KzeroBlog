package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IncreaseViewCountResponseDto extends ResponseDto {

	private IncreaseViewCountResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
	}
	public static ResponseEntity<IncreaseViewCountResponseDto> success() {
		IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistedBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,
				ResponseMessage.NOT_EXISTED_BOARD);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
}
