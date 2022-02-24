package com.don.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/article/*")
public class ArticleController extends HttpServlet {

	ArticleDB db = new ArticleDB();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("게시글 서블릿 실행");

		// 인코딩 설정
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

		// 게시글 추가
		if (func.equals("add")) {

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String nickname = request.getParameter("nickname");

			db.insertArticle(title, body, nickname);

			// 포워드 => 요청 정보를 재사용. url 안바뀜 / 반복적으로 요청이 될 수 있다.
			// 리다이렉트 => 새로운 요청을 보냄. url 바꿈.
			// list(request, response);

			response.sendRedirect("/article/list");
		}
		// 게시글 목록
		else if (func.equals("list")) {
			list(request, response);

		}
		// 게시글 등록 페이지
		else if (func.equals("showAddForm")) {
			RequestDispatcher rd = request.getRequestDispatcher("/Article/addForm.jsp");
			rd.forward(request, response);

		}
		// 게시글 상세보기
		else if (func.equals("detail")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			Article article = db.getArticleByIdx(idx);
			request.setAttribute("article", article);

			forward(request, response, "/Article/detail.jsp");

		}
		// 게시글 수정
		// 수정기능을 수행하는 부분과
		else if (func.equals("update")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String body = request.getParameter("body");

			db.updateArticle(idx, title, body);
			// 다시 자신의 화면으로 돌아간다.
			response.sendRedirect("/article/detail?idx=" + idx);
		}
		// 서블릿을 통하도록 연결하는 부분이 있다.
		else if (func.equals("showUpdateForm")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			Article article = db.getArticleByIdx(idx);
			request.setAttribute("article", article);

			forward(request, response, "/Article/updateForm.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. DB에 접근해서 데이터를 받는다.
		ArrayList<Article> articleList = db.getArticles();

		// 2. articleList데이터를 넘기기 위해서 request객체에 해당 정보를 저장한다.
		request.setAttribute("articleList", articleList);

		// 서블릿에서 처리한 결과를 jsp로 보내야한다.
		// 3. request객체를 다른 서블릿으로 넘긴다. (포워딩)
		// 서버 루트 경로 --> / (webapp)
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
