package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.object.BoardListItem;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.entity.BoardListViewEntity;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {

	private List<BoardListItem> searchList;

	private GetSearchBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.searchList = BoardListItem.getList(boardListViewEntities);
	}

	public static ResponseEntity<GetSearchBoardListResponseDto> success(
			List<BoardListViewEntity> boardListViewEntities) {
		GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(
				boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
