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
			list(request, response);
		}

		// 게시글 목록
		else if (func.equals("list")) {
			list(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. DB에 접근해서 데이터를 받는다.
		ArrayList<Article> articleList = db.getArticleList();

		// 2. articleList데이터를 넘기기 위해서 request객체에 해당 정보를 저장한다.
		request.setAttribute("articleList", articleList);

		// 서블릿에서 처리한 결과를 jsp로 보내야한다.
		// 3. request객체를 다른 서블릿으로 넘긴다. (포워딩)
		// 서버 루트 경로 --> / (webapp)
		RequestDispatcher rd = request.getRequestDispatcher("/Article/list.jsp");
		rd.forward(request, response);

	}

}
