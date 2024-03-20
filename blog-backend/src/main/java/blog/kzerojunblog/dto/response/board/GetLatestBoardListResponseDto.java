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
public class GetLatestBoardListResponseDto extends ResponseDto {

	private List<BoardListItem> latestList;

	private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.latestList = BoardListItem.getList(boardEntities);
	}

	public static ResponseEntity<GetLatestBoardListResponseDto> success(
			List<BoardListViewEntity> boardListViewEntities) {

		GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(
				boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
