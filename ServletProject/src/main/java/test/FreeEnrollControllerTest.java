package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import kr.co.green.board.controller.FreeEnrollController;

public class FreeEnrollControllerTest {

    private FreeEnrollController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        servlet = new FreeEnrollController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getParameter("title")).thenReturn("Test Title");
        when(request.getParameter("content")).thenReturn("Test Content");
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost() throws Exception {
        // 모킹된 HttpSession에서 name을 얻어올 때 "Test Name"을 리턴하도록 설정
        when(session.getAttribute("name")).thenReturn("Test Name");

        // 서비스의 모킹은 생략하고, 핵심 로직만 테스트
        servlet.doPost(request, response);

        // 핵심 로직에 따른 response.sendRedirect 호출 여부 확인
        verify(response).sendRedirect("/freeList.do?cpage=1");
    }
}