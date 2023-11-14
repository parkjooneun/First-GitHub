package kr.co.green.board.model.service;

import java.util.ArrayList;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.PageInfo;

public interface BoardService {
	// 게시글 목록 조회
	public ArrayList<BoardDTO> boardList(PageInfo pi, String searchText);
	
	// 전체 게시글 수
	public int boardListCount(String searchText);
	
	// 글 등록
	public int boardEnroll(BoardDTO board);

	// 조회수 +1
	public int boardView(int idx);
	
	// 상세보기
	public void boardSelect(BoardDTO board);
	
	// 글수정
	public int boardUpdate(int idx, String title, String content);
	
	// 글삭제
	public int boardDelete(int idx);

}



