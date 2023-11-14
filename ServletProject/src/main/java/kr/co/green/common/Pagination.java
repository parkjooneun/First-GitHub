package kr.co.green.common;

public class Pagination {
	
	public static PageInfo getPageInfo(int listCount,
									   int currentPage,
									   int pageLimit,
									   int boardLimit) {
		// 전체 페이지 구하기         			
		// listCount : 101, boardLimit : 5
		// 나눳을 때 정확히 나눠지지 않음(int 기준)
		// doublue로 형변환해서 소수점까지 가지고 올 수 있도록
		// Math.ceil : 소수점 가지고 왔을 때 소수점을 올림 처리
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);        
		
		// 시작 페이지 구하기
		// 상황) 페이지 10개씩 보여준다, 사용자가 16페이지를 보고있다
		// currentPage = 16, pageLimit = 10
		
		// (currentPage-1)
		// (16-1) = 15
		
		// (currentPage-1) / pageLimit
		// 15 / 10 = 1
		
		// (currentPage-1) / pageLimit * pageLimit
		// 1 * 10 = 10
		
		// (currentPage-1) / pageLimit * pageLimit + 1
		// 10 + 1 = 11
		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		
		// 끝 페이지 구하기
		// startPage : 11,  pageLimit : 10
		int endPage = startPage + pageLimit - 1;
		
		// 끝 페이지가 전체 페이지보다 클 때
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		
		// 한페이지에 보여줄 게시글 수 : 10
		// 현재 페이지 1
		// 1~10까지의 게시글을 가져와야 함
		
		// 현재 페이지 2
		// 1 * 5 = 5
		int offset = (currentPage - 1) * boardLimit;
		
//		if(currentPage == 1) {
//			boardLimit = offset+boardLimit;
//		} else {
//			boardLimit = offset+(boardLimit-1);
//		}
		
		PageInfo pi = new PageInfo(listCount,
								   currentPage,
								   pageLimit,
								   boardLimit,
								   maxPage,
								   startPage,
								   endPage,
								   offset);
		
		return pi;
		
	}
	

}





