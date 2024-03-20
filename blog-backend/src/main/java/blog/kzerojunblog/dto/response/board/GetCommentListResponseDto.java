package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.object.CommentListItem;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.repository.resultSet.GetCommentListResultSet;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

	private List<CommentListItem> commentList;
	private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.commentList = CommentListItem.copyList(resultSets);
	}

	public static ResponseEntity<GetCommentListResponseDto> success(
			List<GetCommentListResultSet> resultSets) {
		GetCommentListResponseDto result = new GetCommentListResponseDto(
				resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,
				ResponseMessage.NOT_EXISTED_BOARD);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

}
