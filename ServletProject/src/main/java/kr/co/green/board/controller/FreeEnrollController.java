package kr.co.green.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.service.BoardServiceImpl;

@WebServlet("/freeEnroll.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,  // 1MB
		maxFileSize = 1024 * 1024 * 5,  // 5MB
		maxRequestSize = 1024 * 1024 * 5 * 5 // 25MB
)
public class FreeEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeEnrollController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 1. 내용, 제목 받기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. 세션에 있는 name 꺼내기
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		// 파일 업로드
		Collection<Part> parts = request.getParts();
		String uploadDirectory = "C:\\prj2\\ServletProject\\src\\main\\webapp\\resources\\uploads";
		
		// 파일 업로드하려는 디렉토리 없으면 생성
		File filePath = new File(uploadDirectory);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		String fileName = null;
		
		for(Part part : parts) {
			fileName = getFileName(part);
			
			if(fileName != null) {
				part.write(filePath + File.separator + fileName);
			}
		}
		
		// 3. 서비스 호출
		BoardServiceImpl boardService = new BoardServiceImpl();      
		
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(name);
		
		board.setFileName(fileName);
		board.setFilePath(uploadDirectory);
		
		int result = boardService.boardEnroll(board);
		
		// 4. 성공 유무에 따라 처리
		if(result > 0) {
			response.sendRedirect("/freeList.do?cpage=1");
		} 
	}
	
	// 파일 이름 가지고 오는 메소드
	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		
		// 세미콜론을 기준으로 토큰을 나눠 배열로 저장
		String[] tokens = contentDisposition.split(";");
		
		// 토큰을 하나씩 꺼내면서 토큰의 이름이 filename으로
		// 시작하는걸 찾을때 까지 반복
		for(String token : tokens) {
			// 토큰의 이름이 filename으로 시작하는지 확인
			if(token.trim().startsWith("filename")) {
				
				// 파일의 이름이 "filename=" 다음에 나오기 때문에
				// "filename=" 의 다음 문자부터 끝까지 추출
				return token.substring(token.indexOf('=')+2, token.length()-1);
			}
		}
		return null;
	}
	
	
}








