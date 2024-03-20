package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.request.board.PostCommentRequestDto;
import blog.kzerojunblog.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostCommentResponseDto extends ResponseDto {

	private PostCommentResponseDto() {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

	}

	public static ResponseEntity<PostCommentResponseDto> success() {
		PostCommentResponseDto result = new PostCommentResponseDto();

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistedBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,
				ResponseMessage.NOT_EXISTED_BOARD);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistedUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,
				ResponseMessage.NOT_EXISTED_USER);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	}

}
