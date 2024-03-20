package blog.kzerojunblog.service;

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
import org.springframework.http.ResponseEntity;

public interface BoardService {


	ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

	ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

	ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

	ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
	ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();

	ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

	ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);
	ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto,
			 Integer boardNumber, String email);

	ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

	ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

	ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);

	ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

	ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber,String email);

}
