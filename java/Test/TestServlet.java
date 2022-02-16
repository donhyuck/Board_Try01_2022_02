package Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	/*
	 * 프로토콜://서버주소:포트번호/패스/자원명 *주의 : TestServlet.java로 접근하면 안된다.
	 * http://localhost:9100(/JSP_try01)/TestServlet 서버설정에서 프로젝트명을 생략했다.
	 * http://localhost:9100/TestServlet
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 콘솔에서 출력
		System.out.println("테스트 서블릿 실행");

		// 사용자에게 보여지는 부분
		// 코드를 이용한 기능 구현이 가능하다.
		for (int i = 1; i <= 3; i++) {
			response.getWriter().append("Welcome TestServlet!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
