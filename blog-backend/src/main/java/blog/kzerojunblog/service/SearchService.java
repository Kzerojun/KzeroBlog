package blog.kzerojunblog.service;

import blog.kzerojunblog.dto.response.search.GetPopularListResponseDto;
import blog.kzerojunblog.dto.response.search.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

	ResponseEntity<? super GetPopularListResponseDto> getPopularList();
	ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);


}
