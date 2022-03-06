package com.don.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	MemberDB db = new MemberDB();
	ArticleDB adb = new ArticleDB();

	// 공통코드
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		if (func.equals("add.do")) {
			// 회원가입
			doAdd(request, response);

		} else if (func.equals("login.do")) {

			// 로그인 정보받기
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			int idx = db.getMemberIdxByLoginInfo(loginId, loginPw);

			// 로그인 정보에 해당하는 회원번호가 있을 경우
			if (idx != 0) {
				// 로그인 처리를 하면서 회원정보만 넘어가기 때문에 게시글 관련 정보를 받을 수 없다.
					// 게시글 데이터 베이스를 받아본다.
					// ArrayList<Article> articleList = adb.getArticles();
					// forward(request, response, "/Article/list.jsp");
				// 세션을 이용하면 포워딩을 하지 않아도 된다.

				// 로그인 처리
				Member member = db.getMemberByIdx(idx);

					// request는 데이터 유지가 힘들다.
				// 로그인 정보가 유지하기 위해 session을 이용한다.
				HttpSession session = request.getSession();
				session.setAttribute("loginedUserName", member.getNickname());

				// 리다이렉트 전에 쿠키를 넣어서 보낸다.
				// 쿠키 추가
				// 쿠키 기본 패스 => /member/login.do
				Cookie popupCookie = new Cookie("popupYn", "true");

				// 쿠키 옵션
				// 1. path
				popupCookie.setPath("/");

				// 2. 만료 날짜 (sec단위)
				popupCookie.setMaxAge(60 * 10); // 10분가 유지

				// 3. 도메인 (프로그램 개발에 따라 도메인이 다수가 되면, 도메인 별로 쿠키를 다르게 할 수 있다.)
				// popupCookie.setDomain("m.naver.com");

				response.addCookie(popupCookie);

				response.sendRedirect("/article/list");

			} else {
				System.out.println("로그인 실패");
				response.sendRedirect("/article/list");
			}
		}
	}

	// 자원을 가져올 때 사용
	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("showLoginForm.do")) {
			forward(request, response, "/Member/loginForm.jsp");

		} else if (func.equals("logout.do")) {
			
			// 로그아웃 처리
			// session내용을 지운다.
			HttpSession session = request.getSession();
			session.removeAttribute("loginedUserName");

			response.sendRedirect("/article/list");
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
