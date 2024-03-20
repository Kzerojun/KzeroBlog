package blog.kzerojunblog.service.implement;

import blog.kzerojunblog.dto.request.board.PatchBoardRequestDto;
import blog.kzerojunblog.dto.request.board.PostBoardRequestDto;
import blog.kzerojunblog.dto.request.board.PostCommentRequestDto;
import blog.kzerojunblog.dto.response.ResponseDto;
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
import blog.kzerojunblog.entity.BoardEntity;
import blog.kzerojunblog.entity.BoardListViewEntity;
import blog.kzerojunblog.entity.CommentEntity;
import blog.kzerojunblog.entity.FavoriteEntity;
import blog.kzerojunblog.entity.ImageEntity;
import blog.kzerojunblog.entity.SearchLogEntity;
import blog.kzerojunblog.repository.BoardListViewRepository;
import blog.kzerojunblog.repository.BoardRepository;
import blog.kzerojunblog.repository.CommentRepository;
import blog.kzerojunblog.repository.FavoriteRepository;
import blog.kzerojunblog.repository.ImageRepository;
import blog.kzerojunblog.repository.SearchLogRepository;
import blog.kzerojunblog.repository.UserRepository;
import blog.kzerojunblog.repository.resultSet.GetBoardResultSet;
import blog.kzerojunblog.repository.resultSet.GetCommentListResultSet;
import blog.kzerojunblog.repository.resultSet.GetFavoriteListResultSet;
import blog.kzerojunblog.service.BoardService;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

	private final UserRepository userRepository;
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final ImageRepository imageRepository;
	private final FavoriteRepository favoriteRepository;
	private final BoardListViewRepository boardListViewRepository;
	private final SearchLogRepository searchLogRepository;

	@Override
	public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

		GetBoardResultSet resultSet;
		List<ImageEntity> imageEntities;

		try {
			resultSet = boardRepository.getBoard(boardNumber);
			if (resultSet == null) {
				return GetBoardResponseDto.noExistBoard();
			}
			imageEntities = imageRepository.findByBoardNumber(boardNumber);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetBoardResponseDto.success(resultSet, imageEntities);
	}

	@Override
	public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {
		List<GetFavoriteListResultSet> resultSets;

		try {
			boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
			if (!existedBoard) {
				return GetFavoriteListResponseDto.noExistBoard();
			}
			resultSets = favoriteRepository.getFavoriteList(boardNumber);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetFavoriteListResponseDto.success(resultSets);
	}

	@Override
	public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
		List<GetCommentListResultSet> resultSets;
		try {

			boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
			if (!existedBoard) {
				return GetCommentListResponseDto.noExistBoard();
			}

			resultSets = commentRepository.getCommentList(boardNumber);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetCommentListResponseDto.success(resultSets);
	}

	@Override
	public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
		List<BoardListViewEntity> boardListViewEntities;

		try {
			boardListViewEntities = boardListViewRepository.findByOrderByWriteDatetimeDesc();

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetLatestBoardListResponseDto.success(boardListViewEntities);
	}

	@Override
	public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
		List<BoardListViewEntity> boardListViewEntities;
		try {
			Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sevenDaysAgo = simpleDateFormat.format(beforeWeek);
			boardListViewEntities = boardListViewRepository.findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(
					sevenDaysAgo);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return GetTop3BoardListResponseDto.success(boardListViewEntities);
	}

	@Override
	public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
			String searchWord, String preSearchWord) {

		List<BoardListViewEntity> boardListViewEntities;

		try {
			boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(
					searchWord, searchWord);

			SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
			searchLogRepository.save(searchLogEntity);

			boolean relation = preSearchWord != null;

			if(relation) {
				searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
				searchLogRepository.save(searchLogEntity);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetSearchBoardListResponseDto.success(boardListViewEntities);
	}

	@Override
	public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {

		List<BoardListViewEntity> boardListViewEntities;
		try{
			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return GetUserBoardListResponseDto.noExistUser();
			}

			boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return GetUserBoardListResponseDto.success(boardListViewEntities);

	}

	@Override
	public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto,
			String email) {
		try {
			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return PostBoardResponseDto.notExistUser();
			}

			BoardEntity boardEntity = new BoardEntity(dto, email);
			boardRepository.save(boardEntity);

			int boardNumber = boardEntity.getBoardNumber();
			List<String> boardImageList = dto.getBoardImageList();
			List<ImageEntity> imageEntities = new ArrayList<>();

			for (String image : boardImageList) {
				ImageEntity imageEntity = new ImageEntity(boardNumber, image);
				imageEntities.add(imageEntity);
			}

			imageRepository.saveAll(imageEntities);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PostBoardResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto,
			Integer boardNumber, String email) {

		try {
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if (boardEntity == null) {
				return PostCommentResponseDto.noExistedBoard();
			}

			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return PostCommentResponseDto.noExistedUser();
			}

			CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
			commentRepository.save(commentEntity);

			boardEntity.increaseCommentCount();
			boardRepository.save(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PostCommentResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber,
			String email) {
		try {
			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return PutFavoriteResponseDto.noExistUser();
			}

			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if (boardEntity == null) {
				return PutFavoriteResponseDto.noExistedBoard();
			}

			FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(
					boardNumber, email);

			if (favoriteEntity == null) {
				favoriteEntity = new FavoriteEntity(email, boardNumber);
				favoriteRepository.save(favoriteEntity);
				boardEntity.increaseFavoriteCount();
			} else {
				favoriteRepository.delete(favoriteEntity);
				boardEntity.decreaseFavoriteCount();
			}

			boardRepository.save(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PutFavoriteResponseDto.success();
	}

	@Override
	public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto,
			Integer boardNumber, String email) {
		try {
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if (boardEntity == null) {
				return PatchBoardResponseDto.noExistUser();
			}

			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return PatchBoardResponseDto.noExistUser();
			}

			String writerEmail = boardEntity.getWriterEmail();
			boolean isWriter = writerEmail.equals(email);
			if (!isWriter) {
				return PatchBoardResponseDto.noPermission();
			}

			boardEntity.patchBoard(dto);
			boardRepository.save(boardEntity);

			imageRepository.deleteByBoardNumber(boardNumber);
			List<String> boardImageList = dto.getBoardImageList();
			List<ImageEntity> imageEntities = new ArrayList<>();

			for (String image : boardImageList) {
				ImageEntity imageEntity = new ImageEntity(boardNumber, image);
				imageEntities.add(imageEntity);
			}

			imageRepository.saveAll(imageEntities);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return PatchBoardResponseDto.success();
	}

	@Override
	public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
			Integer boardNumber) {
		try {
			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
			if (boardEntity == null) {
				return IncreaseViewCountResponseDto.noExistedBoard();
			}
			boardEntity.increaseViewCount();
			boardRepository.save(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}
		return IncreaseViewCountResponseDto.success();
	}


	@Override
	public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber,
			String email) {
		try {
			boolean existedUser = userRepository.existsByEmail(email);
			if (!existedUser) {
				return DeleteBoardResponseDto.noExistUser();
			}

			BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);

			if (boardEntity == null) {
				return DeleteBoardResponseDto.noExistUser();
			}

			String writerEmail = boardEntity.getWriterEmail();
			boolean isWriter = writerEmail.equals(email);
			if (!isWriter) {
				return DeleteBoardResponseDto.noPermission();
			}

			imageRepository.deleteByBoardNumber(boardNumber);
			commentRepository.deleteByBoardNumber(boardNumber);
			favoriteRepository.deleteByBoardNumber(boardNumber);
			boardRepository.delete(boardEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.databaseError();
		}

		return DeleteBoardResponseDto.success();
	}
}
