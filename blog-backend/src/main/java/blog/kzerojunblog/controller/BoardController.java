package blog.kzerojunblog.controller;

import blog.kzerojunblog.dto.request.board.PatchBoardRequestDto;
import blog.kzerojunblog.dto.request.board.PostBoardRequestDto;
import blog.kzerojunblog.dto.request.board.PostCommentRequestDto;
import blog.kzerojunblog.dto.response.board.DeleteBoardResponseDto;
import blog.kzerojunblog.dto.response.board.GetBoardResponseDto;
import blog.kzerojunblog.dto.response.board.GetCommentListResponseDto;
import blog.kzerojunblog.dto.response.board.GetFavoriteListResponseDto;
import blog.kzerojunblog.dto.response.board.GetLatestBoardListResponseDto;
import blog.kzerojunblog.dto.response.board.GetSearchBoardListResponseDto;
import blog.kzerojunblog.dto.response.board.GetTop3BoardListResponseDto;
import blog.kzerojunblog.dto.response.board.GetUserBoardListResponseDto;
import blog.kzerojunblog.dto.response.board.IncreaseViewCountResponseDto;
import blog.kzerojunblog.dto.response.board.PatchBoardResponseDto;
import blog.kzerojunblog.dto.response.board.PostBoardResponseDto;
import blog.kzerojunblog.dto.response.board.PostCommentResponseDto;
import blog.kzerojunblog.dto.response.board.PutFavoriteResponseDto;
import blog.kzerojunblog.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/{boardNumber}")
	public ResponseEntity<? super GetBoardResponseDto> getBoard(
			@PathVariable("boardNumber") Integer boardNumber) {
		return boardService.getBoard(boardNumber);
	}

	@GetMapping("/{boardNumber}/favorite-list")
	public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
			@PathVariable("boardNumber") Integer boardNumber) {
		return boardService.getFavoriteList(boardNumber);
	}

	@GetMapping("/{boardNumber}/comment-list")
	public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
			@PathVariable("boardNumber") Integer boardNumber) {
		return boardService.getCommentList(boardNumber);
	}

	@GetMapping("/{boardNumber}/increase-view-count")
	public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
			@PathVariable("boardNumber") Integer boardNumber) {
		return boardService.increaseViewCount(boardNumber);
	}

	@GetMapping("/latest-list")
	public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
		return boardService.getLatestBoardList();
	}

	@GetMapping("/top-3")
	public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
		return boardService.getTop3BoardList();
	}

	@GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
	public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
			@PathVariable("searchWord") String searchWord,
			@PathVariable(value = "preSearchWord", required = false) String preSearchWord) {
		return boardService.getSearchBoardList(searchWord, preSearchWord);
	}

	@GetMapping("/user-board-list/{email}")
	public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(
			@PathVariable("email") String email) {
		return boardService.getUserBoardList(email);
	}

	@PostMapping("")
	public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid
	PostBoardRequestDto requestBody, @AuthenticationPrincipal String email) {
		return boardService.postBoard(requestBody, email);
	}

	@PostMapping("/{boardNumber}/comment")
	public ResponseEntity<? super PostCommentResponseDto> postComment(
			@RequestBody @Valid PostCommentRequestDto requestBody,
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) {
		return boardService.postComment(requestBody, boardNumber, email);
	}

	@PutMapping("/{boardNumber}/favorite")
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) {
		return boardService.putFavorite(boardNumber, email);
	}

	@PatchMapping("/{boardNumber}")
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(@RequestBody @Valid
	PatchBoardRequestDto requestBody, @PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) {
		return boardService.patchBoard(requestBody, boardNumber, email);
	}

	@DeleteMapping("/{boardNumber}")
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
			@PathVariable("boardNumber") Integer boardNumber,
			@AuthenticationPrincipal String email) {
		return boardService.deleteBoard(boardNumber, email);
	}
}



