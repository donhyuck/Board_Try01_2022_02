package com.don.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class gugudan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 클라이언트가 서버에게 데이터를 보내는 법
		// 또한 서버는 입력받은 데이터를 처리하여 응답한다.
		// ? 를 붙여 요청표시, & 를 붙여 여러개 보내기
		// ex) http://localhost:9100/gugudan?dan=3&gop=100

		PrintWriter out = response.getWriter();
		out.println("<h3>구구단</h3>");

		// queryString
		// 고객이 넘긴 dan 값을 사용해야 함.
		// 고객이 넘긴 데이터 == 요청 파라미터
		// 요청 파라미터를 꺼내는 방법 -> request.getPrameter()
		// 무조건 String으로 반환됨-> int로 변환작업필요
		String sdan = request.getParameter("dan");
		int dan = Integer.parseInt(sdan);

		String sgop = request.getParameter("gop");
		int gop = Integer.parseInt(sgop);

		out.println("=== " + dan + "단 ===</br>");
		for (int i = 1; i <= gop; i++) {
			out.println(dan + " * " + i + " = " + dan * i + "</br>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
