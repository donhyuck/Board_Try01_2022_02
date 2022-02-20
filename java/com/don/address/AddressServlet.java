package com.don.address;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addr")
public class AddressServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("주소록 서블릿 실행");

		// 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		// 주소록 추가

		// 주소록 조회
		AddrDB db = new AddrDB();
		ArrayList<Addr> addrList = db.selectDatas();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>주소록 메뉴</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h3>주소록 메뉴</h3>");
		
		for (int i = 0; i <= addrList.size(); i++) {

			Addr addr = addrList.get(i);
			
			out.println("	<div>");
			out.println("	번호 : " + addr.getIdx() + " <br />");
			out.println("	이름 : " + addr.getName() + " <br />");
			out.println("	주소 : " + addr.getAddress() + " <br />");
			out.println("	전화 : " + addr.getPhone() + " <br />");
			out.println("	</div>");
			out.println("	<hr>");
		}

		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
