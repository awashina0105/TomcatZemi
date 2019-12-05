package studentdelete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRegistrationBean;
import dao.StudentDeleteDAO;

/**
 * Servlet implementation class StudentdeleteServlet
 */
@WebServlet("/StudentDeleteCheckServlet")
public class StudentDeleteCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDeleteCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentDeleteDAO sddao = new StudentDeleteDAO();
		String send = "";

		StudentRegistrationBean srbean = (StudentRegistrationBean) session.getAttribute("studentinfo");

		String studentId = srbean.getStudentId();

		if (sddao.studentdelete(studentId)) {
			session.removeAttribute("studentClassInfoarray");
			session.removeAttribute("studenNameInfotarray");
			session.removeAttribute("studenIdInfo");

			session.removeAttribute("studentarray");
			session.removeAttribute("studentinfo");
			send = "削除完了画面";
		}else{
			send = "削除エラー画面";
		}
		response.sendRedirect(send);
	}

}
