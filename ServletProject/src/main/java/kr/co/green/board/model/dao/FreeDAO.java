package kr.co.green.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.PageInfo;

public class FreeDAO {
	private PreparedStatement pstmt;

	public ArrayList<BoardDTO> boardList(Connection con, PageInfo pi, String searchText) {                            
		// posts 변수명도 많이 사용됨
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		// 1. 쿼리 작성
// 		MySQL offset 페이징
//		String query = "SELECT fb_idx,"
//				+ "			   fb_title,"
//				+ "			   fb_in_date,"
//				+ "			   fb_views,"
//				+ "			   fb_writer"
//				+ "		FROM free_board"
//				+ "		LIMIT ? OFFSET ?";
		
// 		MySQL cursor 페이징
//		String query = "SELECT fb_idx,"
//				+ "			   fb_title,"
//				+ "			   fb_in_date,"
//				+ "			   fb_views,"
//				+ "			   fb_writer"
//				+ "		FROM free_board"
//				+ "		WHERE fb_idx > ? LIMIT ?";
		
//		String query = "SELECT fb2.*"
//				+ "		FROM (SELECT rownum AS rnum, fb.* "
//				+ "			  FROM (SELECT fb_idx,"
//			+ "						 	   fb_title,"
//			+ "						 	   fb_in_date,"
//			+ "						  	   fb_views,"
//			+ "						 	   fb_writer"
//			+ "				  		FROM free_board"
//			+ "				  		ORDER BY fb_in_date DESC) fb) fb2"
//			+ "			WHERE rnum BETWEEN ? AND ?";
		
		String query = "SELECT fb_idx,"
				+ "			   fb_title,"
				+ "			   fb_in_date,"
				+ "			   fb_views,"
				+ "			   fb_writer"
				+ "		FROM free_board"
				+ "		WHERE fb_delete_date IS NULL"
				+ "		AND fb_title LIKE '%' || ? || '%' "
				+ "		ORDER BY fb_in_date DESC"
				+ "     OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
		
		try {
			// 2. 쿼리 사용할 준비
			pstmt = con.prepareStatement(query);
			
			// 3. 물음표 있으면 값 삽입
			pstmt.setString(1, searchText);
			pstmt.setInt(2, pi.getOffset()); // 0
			pstmt.setInt(3, pi.getBoardLimit()); // 5
			
			// 4. 쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			
//			BoardDTO board = new BoardDTO();
			while(rs.next()) {
				int idx = rs.getInt("FB_IDX");
				String title = rs.getString("FB_TITLE");                            
				String inDate = rs.getString("FB_IN_DATE");
				int views = rs.getInt("FB_VIEWS");
				String writer = rs.getString("FB_WRITER");
				
				BoardDTO board = new BoardDTO();
				
				board.setIdx(idx);
				board.setTitle(title);
				board.setInDate(inDate);
				board.setViews(views);
				board.setWriter(writer);
				
				list.add(board);
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int boardListCount(Connection con, String searchText) {
		String query = "SELECT count(*) AS cnt "
				+ "		FROM free_board"
				+ "		WHERE fb_delete_date IS NULL"
				+ "		AND fb_title LIKE '%' || ? ||'%'";
//						    fb_title LIKE '%가%'
		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchText);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				int result = rs.getInt("CNT");
				return result;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int boardEnroll(Connection con, BoardDTO board) {
		// 1. 쿼리 작성
		String query = "INSERT INTO free_board"
				+ "		VALUES(FB_IDX_SEQ.nextval," // 회원번호
				+ "			   ?,"  // 제목
				+ "			   ?,"  // 내용
				+ "			   SYSDATE," // 작성일
				+ "			   NULL, " // 수정일
				+ "			   NULL, " // 삭제일
				+ "			   0,"  // 조회수
				+ "			   ?," // 작성자
				+ "			   ?," // 파일 경로
				+ "			   ?)"; // 파일 이름
		
		try {
			// 2. 쿼리 사용할 준비
			pstmt = con.prepareStatement(query);
			
			// 3. 물음표에 값 삽입
			// 첫번째 : 제목, 두번째 : 내용, 세번째 : 작성자
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getFilePath());
			pstmt.setString(5, board.getFileName());
			
			// 4. 쿼리 실행
			int result = pstmt.executeUpdate();
			
			// 5. DB 연결 종료
			pstmt.close();
			con.close();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int boardView(Connection con, int idx) {
		String query = "UPDATE free_board"
				+ "		SET fb_views = fb_views+1"
				+ "		WHERE fb_idx = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idx);
			int result = pstmt.executeUpdate();
			
//			pstmt.close();
//			con.close();
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void boardSelect(Connection con, BoardDTO board) {
		// 글번호, 제목, 작성자, 조회수, 작성일, 내용
		String query = "SELECT fb_idx,"
				+ "			   fb_title,"
				+ "			   fb_writer,"
				+ "			   fb_views,"
				+ "			   fb_in_date,"
				+ "			   fb_content,"
				+ "			   file_name"
				+ "		FROM free_board"
				+ "		WHERE fb_idx = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, board.getIdx());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 글번호, 제목, 작성자, 조회수, 작성일, 내용
				int idx = rs.getInt("FB_IDX");
				String title = rs.getString("FB_TITLE");
				String writer = rs.getString("FB_WRITER");
				int views = rs.getInt("FB_VIEWS");
				String inDate = rs.getString("FB_IN_DATE");
				String content = rs.getString("FB_CONTENT");
				String fileName = rs.getString("FILE_NAME");
				
				board.setIdx(idx);
				board.setTitle(title);
				board.setWriter(writer);
				board.setViews(views);
				board.setInDate(inDate);
				board.setContent(content);
				board.setFileName(fileName);
				
			}
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int boardUpdate(Connection con, int idx, String title, String content) {
		String query = "UPDATE free_board"
				+ "		SET fb_title = ?,"
				+ "			fb_content = ?,"
				+ "			fb_update_date = sysdate"
				+ "		WHERE fb_idx = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int boardDelete(Connection con, int idx) {
		String query = "UPDATE free_board"
				+ "		SET fb_delete_date = sysdate"
				+ "		WHERE fb_idx = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, idx);
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}







