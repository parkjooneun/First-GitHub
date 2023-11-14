package kr.co.green.board.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.service.BoardServiceImpl;

@WebServlet("/freeDetail.do")
public class FreeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. idx 받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. 조회수 늘리기(UPDATE)
		BoardServiceImpl boardService = new BoardServiceImpl();
		int result = boardService.boardView(idx);
		
		if(result > 0) {
			// 3. idx로 게시판 조회(SELECT)
			BoardDTO board = new BoardDTO();
			board.setIdx(idx);
			
			boardService.boardSelect(board);
			
			// boardViews에 있는 close 삭제
			// boardSelect에 close 추가
			
			if(!Objects.isNull(board.getIdx())) {
				
				// 4. freeDetail.jsp로 포워딩
				
				// 파일 이름 데이터 바인딩
				request.setAttribute("board", board);
				RequestDispatcher view = request.getRequestDispatcher("/views/board/free/freeDetail.jsp");
				view.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
