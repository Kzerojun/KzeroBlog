package blog.kzerojunblog.dto.object;

import blog.kzerojunblog.entity.BoardListViewEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {

	private int boardNumber;
	private String title;
	private String content;
	private String boardTitleImage;
	private int favoriteCount;
	private int commentCount;
	private int viewCount;
	private String writeDatetime;
	private String writerNickName;
	private String writerProfileImage;

	public BoardListItem(BoardListViewEntity boardListViewEntity) {
		this.boardNumber = boardListViewEntity.getBoardNumber();
		this.title = boardListViewEntity.getTitle();
		this.content = boardListViewEntity.getContent();
		this.boardTitleImage = boardListViewEntity.getTitleImage();
		this.favoriteCount = boardListViewEntity.getFavoriteCount();
		this.commentCount = boardListViewEntity.getCommentCount();
		this.viewCount = boardListViewEntity.getViewCount();
		this.writeDatetime = boardListViewEntity.getWriteDatetime();
		this.writerNickName = boardListViewEntity.getWriterNickname();
		this.writerProfileImage = boardListViewEntity.getWriterProfileImage();
	}

	public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntityList) {
		List<BoardListItem> list = new ArrayList<>();

		for (BoardListViewEntity boardListViewEntity : boardListViewEntityList) {
			BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
			list.add(boardListItem);
		}
		return list;
	}
}
