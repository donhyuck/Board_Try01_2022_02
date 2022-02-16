package com.don.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestA")
public class TestA extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("테스트A 서블릿 실행");

		// 1) 클라이언트 브라우저에 응답
		// 입력) request : 요청에 관한 다양한 정보와 기능들이 모여있음
		// 출력) response : 응답에 관한 다양한 정보와 기능들이 모여있음

		// 2) 한글출력을 위해서는 인코딩이 필요하다.
		// 데이터를 주고받는 두 주체(컴퓨터간, 프로그램간..) 인코딩 방식이 같아야 한다.
		response.setCharacterEncoding("UTF-8"); // Tomcat에게 UTF-8처리해서 보내라
		response.setContentType("text/plain; charset=UTF-8"); // 브라우저한테 내가 보내는 걸 UTF-8로 처리해라
		System.out.println("인코딩 실행");

		PrintWriter out = response.getWriter();
		out.println("hello~!");
		out.println("my name is");
		out.println("TestA");

		out.println("안녕하세요!");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
