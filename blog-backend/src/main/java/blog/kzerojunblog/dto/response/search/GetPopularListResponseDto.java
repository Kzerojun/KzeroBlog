package blog.kzerojunblog.dto.response.search;

import blog.kzerojunblog.common.ResponseCode;
import blog.kzerojunblog.common.ResponseMessage;
import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.repository.resultSet.GetCommentListResultSet;
import blog.kzerojunblog.repository.resultSet.GetPopularListResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetPopularListResponseDto extends ResponseDto {

	private List<String> popularWordList;
	private GetPopularListResponseDto(List<GetPopularListResultSet> resultSets) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		List<String> popularWordList = new ArrayList<>();

		for (GetPopularListResultSet resultSet : resultSets) {
			String popularWord = resultSet.getSearchWord();
			popularWordList.add(popularWord);
		}

		this.popularWordList = popularWordList;
	}

	public static ResponseEntity<GetPopularListResponseDto> success(List<GetPopularListResultSet> resultSets) {
		GetPopularListResponseDto result = new GetPopularListResponseDto(resultSets);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
