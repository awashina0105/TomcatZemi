package accountinfochange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRegistrationBean;

/**
 * Servlet implementation class AccountInfoChangeCheckServlet
 */
@WebServlet("/AccountInfoChangeCheckServlet")
public class AccountInfoChangeCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfoChangeCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentLname = request.getParameter("studentLname");
		String studentFname = request.getParameter("studentFname");
		String classId = request.getParameter("classId");
		String send = "アカウント変更確認画面";

		StudentRegistrationBean srbean = (StudentRegistrationBean) session.getAttribute("studentinfo");

		String studentId = srbean.getStudentId();

		srbean.setStudentId(studentId);
		srbean.setStudentLname(studentLname);
		srbean.setStudentFname(studentFname);
		srbean.setClassId(classId);
		session.setAttribute("newstudentinfo", srbean);

		response.sendRedirect(send);
	}

}