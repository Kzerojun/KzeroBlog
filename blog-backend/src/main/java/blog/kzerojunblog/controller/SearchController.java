package blog.kzerojunblog.controller;

import blog.kzerojunblog.dto.response.search.GetPopularListResponseDto;
import blog.kzerojunblog.dto.response.search.GetRelationListResponseDto;
import blog.kzerojunblog.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/popular-list")
	public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
		return searchService.getPopularList();
	}

	@GetMapping("/{searchWord}/relation-list")
	public ResponseEntity<? super GetRelationListResponseDto> getRelation(
			@PathVariable("searchWord") String searchWord) {
		return searchService.getRelationList(searchWord);
	}
}
