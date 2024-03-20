package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.object.BoardListItem;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.entity.BoardListViewEntity;
import lombok.Getter;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {

	private List<BoardListItem> userBoardList;

	private GetUserBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.userBoardList = BoardListItem.getList(boardListViewEntities);
	}

	public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
		GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(
				boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistUser() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,
				ResponseMessage.NOT_EXISTED_USER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
}
