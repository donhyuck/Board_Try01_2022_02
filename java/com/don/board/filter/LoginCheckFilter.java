package com.don.board.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/article/detail", "/article/showAddForm" })
public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 로그인 했는가?
		// 로그아웃 상태일 경우 로그인 페이지로 이동
		// 로그인 상태일 경우 명령처리
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedUserName") == null) {
			res.sendRedirect("/member/showLoginForm.do");
			return;
		}

		chain.doFilter(request, response);
	}
}