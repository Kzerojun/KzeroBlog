package blog.kzerojunblog.dto.response.board;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.object.FavoriteListItem;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.repository.resultSet.GetFavoriteListResultSet;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {

	private List<FavoriteListItem> favoriteList;

	private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.favoriteList = FavoriteListItem.copyList(resultSets);
	}

	public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets) {
		GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public static ResponseEntity<ResponseDto> noExistBoard() {
		ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,
				ResponseMessage.NOT_EXISTED_BOARD);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
}
