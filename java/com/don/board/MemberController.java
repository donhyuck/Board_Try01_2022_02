package com.don.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	MemberDB db = new MemberDB();
	ArticleDB adb = new ArticleDB();

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
		if (func.equals("add.do")) {
			doAdd(request, response);

		} else if (func.equals("login.do")) {

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			int idx = db.getMemberIdxByLoginInfo(loginId, loginPw);

			if (idx != 0) {
				// 로그인 처리
				Member member = db.getMemberByIdx(idx);

				// 로그인 처리를 하면서 회원정보만 넘어가기 때문에 게시글 관련 정보를 받을 수 없다.
				// 게시글 데이터 베이스를 받아본다.
				ArrayList<Article> articleList = adb.getArticles();

				request.setAttribute("loginedUserName", member.getNickname());
				request.setAttribute("articleList", articleList);
				forward(request, response, "/Article/list.jsp");

			} else {
				System.out.println("로그인 실패");
			}
		}
	}

	// 자원을 가져올 때 사용
	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("showLoginForm.do")) {
			forward(request, response, "/Member/loginForm.jsp");

		}
	}

	// 회원가입
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");

		db.insertMember(loginId, loginPw, nickname);

		// 가입 이후 게시글 목록으로
		response.sendRedirect("/article/list");

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
