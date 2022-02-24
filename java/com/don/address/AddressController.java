package com.don.address;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addr/*")
public class AddressController extends HttpServlet {

	AddrDB db = new AddrDB();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("주소록 서블릿 실행");

		// 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 사용자가 원하는 기능을 uri를 쪼개서 구분한다.
		String uri = request.getRequestURI();

		String[] uriPieces = uri.split("/");

		if (uriPieces.length < 3) {
			// 에러페이지
			System.out.println("잘못된 요청입니다.");
			return;
		}

		String func = uriPieces[2];

		// 주소록 추가
		if (func.equals("add")) {

			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			db.insertData(name, address, phone);
			list(request, response);
		}

		// 주소록 조회
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
		ArrayList<Addr> addrList = db.selectDatas();

		// 2. addrList데이터를 넘기기 위해서 request객체에 해당 정보를 저장한다.
		request.setAttribute("addrList", addrList);

		// 서블릿에서 처리한 결과를 jsp로 보내야한다.
		// 3. request객체를 다른 서블릿으로 넘긴다. (포워딩)
		// 서버 루트 경로 --> / (webapp)
		RequestDispatcher rd = request.getRequestDispatcher("/Address/list.jsp");
		rd.forward(request, response);
	}
}
