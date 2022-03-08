package com.don.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/article/*")
public class ArticleController extends HttpServlet {

	ArticleDB db = new ArticleDB();
	ReplyDB rdb = new ReplyDB();

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

		if (func.equals("add")) {
			// 게시글 추가
			doAdd(request, response);

		} else if (func.equals("update")) {

			// 수정기능을 수행하는 부분
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String body = request.getParameter("body");

			db.updateArticle(idx, title, body);
			// 다시 자신의 화면으로 돌아간다.
			response.sendRedirect("/article/detail?idx=" + idx);

		} else if (func.equals("delete")) {

			// 게시글 삭제
			int idx = Integer.parseInt(request.getParameter("idx"));
			db.deleteArticle(idx);

			response.sendRedirect("/article/list");

		} else if (func.equals("addReply")) {

			// 댓글 작성
			// 사용자에게 넘겨받는 데이터는 댓글내용이지만
			// 댓글을 구성하는 데이터는 해당 게시글 번호, 작성자 등이 필요하다.
			int articleIdx = Integer.parseInt(request.getParameter("articleIdx"));
			String body = request.getParameter("body");
			String nickname = request.getParameter("nickname");
			int memberIdx = Integer.parseInt(request.getParameter("memberIdx"));

			rdb.insertReply(articleIdx, memberIdx, body, nickname);

			response.sendRedirect("/article/detail?idx=" + articleIdx);

		} else if (func.equals("doUpdateReply")) {

			// 댓글 수정
			int idx = Integer.parseInt(request.getParameter("idx"));
			String body = request.getParameter("body");
			int articleIdx = Integer.parseInt(request.getParameter("articleIdx"));

			rdb.updateReply(idx, body);
			response.sendRedirect("/article/detail?idx=" + articleIdx);

		} else if (func.equals("doDeleteReply")) {

			// 댓글 삭제
			int idx = Integer.parseInt(request.getParameter("idx"));
			int articleIdx = Integer.parseInt(request.getParameter("articleIdx"));

			rdb.deleteReply(idx);
			response.sendRedirect("/article/detail?idx=" + articleIdx);
		}
	}

	// 자원을 가져올 때 사용
	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("add")) {
			doAdd(request, response);

		} else if (func.equals("list")) {
			list(request, response);

		} else if (func.equals("showAddForm")) {

			HttpSession session = request.getSession();
			String loginedUserName = (String) session.getAttribute("loginedUserName");

			// 게시글 등록 페이지
			RequestDispatcher rd = request.getRequestDispatcher("/Article/addForm.jsp");
			rd.forward(request, response);

		} else if (func.equals("detail")) {

			// 게시글 상세보기
			int idx = Integer.parseInt(request.getParameter("idx"));
			Article article = db.getArticleByIdx(idx);

			// 댓글 목록 보기
			ArrayList<Reply> replies = rdb.getRepliesByArticleIdx(idx);

			request.setAttribute("article", article);
			request.setAttribute("replies", replies);

			forward(request, response, "/Article/detail.jsp");

		} else if (func.equals("showUpdateForm")) {

			// 게시글 수정
			// 서블릿을 통하도록 연결하는 부분이 있다.
			int idx = Integer.parseInt(request.getParameter("idx"));
			Article article = db.getArticleByIdx(idx);
			request.setAttribute("article", article);

			forward(request, response, "/Article/updateForm.jsp");

		} else if (func.equals("showReplyForm")) {

			// 댓글 수정
			int idx = Integer.parseInt(request.getParameter("idx"));
			Reply reply = rdb.getReplyByIdx(idx);
			request.setAttribute("reply", reply);

			forward(request, response, "/Article/replyForm.jsp");
		}
	}

	// 게시글 추가
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String nickname = request.getParameter("nickname");

		db.insertArticle(title, body, nickname);

		// 포워드 => 요청 정보를 재사용. url 안바뀜 / 반복적으로 요청이 될 수 있다.
		// 리다이렉트 => 새로운 요청을 보냄. url 바꿈.
		list(request, response);

		// response.sendRedirect("/article/list");

	}

	// 게시글 목록
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. DB에 접근해서 데이터를 받는다.
		ArrayList<Article> articleList = db.getArticles();

		// 2. articleList데이터를 넘기기 위해서 request객체에 해당 정보를 저장한다.
		request.setAttribute("articleList", articleList);

		// 서블릿에서 처리한 결과를 jsp로 보내야한다.
		// 3. request객체를 다른 서블릿으로 넘긴다. (포워딩)
		// 서버 루트 경로 --> / (webapp)

		// 팝업띄우기
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("popupYn")) {
					request.setAttribute("popupYn", c.getValue());
				}
			}
		}

		forward(request, response, "/Article/list.jsp");

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
