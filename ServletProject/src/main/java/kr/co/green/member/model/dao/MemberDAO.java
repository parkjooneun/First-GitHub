package kr.co.green.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.green.member.model.dto.MemberDTO;

public class MemberDAO {
	private PreparedStatement pstmt;
	
	// 회원가입
	public int memberEnroll(Connection con, MemberDTO memberDTO) {
		String query = "INSERT INTO member VALUES(?, ?, ?, SYSDATE)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getName());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인
	public MemberDTO memberLogin(Connection con, String id) {
		String query = "SELECT member_id, member_pwd, member_name, member_in_date"                                 
				+ "		FROM member"
				+ "		WHERE member_id = ?";
		
		MemberDTO result = new MemberDTO();
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
//			rs.next();
			while(rs.next()) {
				String resultId = rs.getString("MEMBER_ID");
				String resultPwd = rs.getString("MEMBER_PWD");
				
				String resultName = rs.getString("MEMBER_NAME");
				String resultIndate = rs.getString("MEMBER_IN_DATE");
				
				result.setId(resultId);
				result.setPwd(resultPwd);
				
				result.setName(resultName);
				result.setIndate(resultIndate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean duplicateId(Connection con, String id) {
		String query = "SELECT member_id FROM member"
				+ "		WHERE member_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// 쿼리가 조회된 결과(ResultSet)가 있다면 true
			// 없다면 false
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	public void selectMember(Connection con, MemberDTO memberDTO) {
		String query = "SELECT member_id, "
				+ "			   member_name, "
				+ "			   member_in_date"
				+ "		FROM member"
				+ "		WHERE member_name = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getName());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String indate = rs.getString("MEMBER_IN_DATE");
				
				memberDTO.setId(id);
				memberDTO.setName(name);
				memberDTO.setIndate(indate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int memberUpdate(Connection con, MemberDTO memberDTO, String beforeName) {                    
		System.out.println(memberDTO.toString());
		System.out.println(beforeName);
		
		String query = "UPDATE member "
				+ "		SET member_id = ?,"
				+ "			member_name = ?"
				+ "		WHERE member_name = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getName());
			pstmt.setString(3, beforeName);
			
			return pstmt.executeUpdate();
			
//			int result = pstmt.executeUpdate();
//			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int memberDelete(Connection con, String name) {
		String Qeury = "DELETE FROM member"
				+ "		WHERE member_name = ?";
		
		try {
			pstmt = con.prepareStatement(Qeury);
			pstmt.setString(1, name);
			
			int result = pstmt.executeUpdate();
			return result;
//			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

}






