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
public class GetTop3BoardListResponseDto extends ResponseDto {

	private List<BoardListItem> top3List;
	private GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.top3List = BoardListItem.getList(boardListViewEntities);
	}

	public static ResponseEntity<GetTop3BoardListResponseDto> success(
			List<BoardListViewEntity> boardListViewEntities) {
		GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(
				boardListViewEntities);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
