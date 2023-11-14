package kr.co.green.member.model.service;

import kr.co.green.member.model.dto.MemberDTO;

public interface MemberService {
	// 회원가입
	public int memberEnroll(MemberDTO memberDTO);
	
	// login
	public MemberDTO memberLogin(String id);
	
	// 아이디 중복검사
	public boolean duplicateId(String id);
	
	// 회원 정보 조회
	public void selectMember(MemberDTO memberDTO);
	
	// 회원 정보 수정
	public int memberUpdate(MemberDTO memberDTO, String beforeName);
	
	// 회원 탈퇴
	public int memberDelete(String name);


}







