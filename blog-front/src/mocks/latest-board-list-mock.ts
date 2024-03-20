import {BoardListItem} from "../types/interface";

const latestBoardListMock: BoardListItem[] = [
  {
    "boardNumber": 1,
    "title": "제목",
    "content": "콘텐츠",
    "boardTitleImage": 'data:image/jpeg;base64,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',
    "favoriteCount": 0,
    "commentCount": 0,
    "viewCount": 0,
    "writeDatetime": "2024.03.05. 00:54:27",
    "writerNickname": "제로준",
    "writerProfileImage": 'data:image/jpeg;base64,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',
  }, {
    "boardNumber": 1,
    "title": "제목",
    "content": "콘텐츠",
    "boardTitleImage": null,
    "favoriteCount": 0,
    "commentCount": 0,
    "viewCount": 0,
    "writeDatetime": "2024.03.05. 00:54:27",
    "writerNickname": "제로준",
    "writerProfileImage": null,
  }, {
    "boardNumber": 1,
    "title": "제목",
    "content": "콘텐츠",
    "boardTitleImage": null,
    "favoriteCount": 0,
    "commentCount": 0,
    "viewCount": 0,
    "writeDatetime": "2024.03.05. 00:54:27",
    "writerNickname": "제로준",
    "writerProfileImage": null,
  }
];

export default latestBoardListMock;
