package blog.kzerojunblog.service.implement;

import blog.kzerojunblog.dto.response.ResponseDto;
import blog.kzerojunblog.dto.response.search.GetPopularListResponseDto;
import blog.kzerojunblog.dto.response.search.GetRelationListResponseDto;
import blog.kzerojunblog.repository.SearchLogRepository;
import blog.kzerojunblog.repository.resultSet.GetPopularListResultSet;
import blog.kzerojunblog.repository.resultSet.GetRelationListResultSet;
import blog.kzerojunblog.service.SearchService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

	private final SearchLogRepository searchLogRepository;

	@Override
	public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
		List<GetPopularListResultSet> resultSets = new ArrayList<>();

		try {
			resultSets = searchLogRepository.getPopularList();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetPopularListResponseDto.success(resultSets);
	}

	@Override
	public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

		List<GetRelationListResultSet> resultSets;

		try {
			resultSets = searchLogRepository.getRelationList(searchWord);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetRelationListResponseDto.success(resultSets);

	}

}
