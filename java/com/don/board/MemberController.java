package com.don.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	MemberDB db = new MemberDB();

	// 공통코드
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("공통코드 실행");

		// 사용자가 원하는 기능을 uri를 쪼개서 구분한다.
		String uri = request.getRequestURI();

		String[] uriPieces = uri.split("/");

		if (uriPieces.length < 3) {
			System.out.println("잘못된 요청입니다.");
			return;
		}

		String func = uriPieces[2];

		// POST, GET 구분
		String method = request.getMethod();

		request.setAttribute("func", func);

		if (method.equals("POST")) {

			postProcess(request, response);

		} else if (method.equals("GET")) {

			getProcess(request, response);

		}
	}

	// 자원을 처리할때 사용
	private void postProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		// 회원가입
		if (func.equals("add")) {
			doAdd(request, response);

		}
	}

	// 자원을 가져올 때 사용
	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("add")) {
			doAdd(request, response);

		}
	}

	// 회원가입
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");

//		db.insertArticle(loginId, loginPw, nickname);

		// list(request, response);

		// response.sendRedirect("/article/list");

	}

	// 포워드 메서드
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) {

		try {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (ServletException e) {
			System.out.println("포워딩 서블릿 에러발생");
		} catch (IOException e) {
			System.out.println("입출력 중 에러발생");
		} catch (Exception e) {
			System.out.println("포워딩 중 에러발생");
		}
	}

}
