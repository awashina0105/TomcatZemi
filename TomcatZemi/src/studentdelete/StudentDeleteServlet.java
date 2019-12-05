package studentdelete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.StudentRegistrationBean;
import dao.StudentDeleteDAO;

/**
 * Servlet implementation class StudentdeleteServlet
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentDeleteDAO sddao = new StudentDeleteDAO();
		String send = "";

		String studentId = request.getParameter("studentId");

		StudentRegistrationBean srbean = sddao.StudentInfoSearch(studentId);

		String studentIdCheck = srbean.getStudentId();

		if (StringUtils.isEmpty(studentIdCheck)) {
			send = "エラー画面";

		}else{
			session.setAttribute("studentinfo", srbean);

			send = "アカウント消去確認画面";
		}

		response.sendRedirect(send);
	}

}
