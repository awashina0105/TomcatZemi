package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.TeacherLoginBean;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginBean lbean = (LoginBean)session.getAttribute("stuid");
		TeacherLoginBean tlbean = (TeacherLoginBean)session.getAttribute("teacherLoginInfo");
		session.removeAttribute("stuid");
		session.removeAttribute("teacherLoginInfo");

		response.sendRedirect("login.html");
	}
}
