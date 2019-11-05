package studentregistration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRegistrationBean;
import dao.StudentRegistrationDAO;

/**
 * Servlet implementation class StudentRegistrationProcessingServlet
 */
@WebServlet("/StudentRegistrationProcessingServlet")
public class StudentRegistrationProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegistrationProcessingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentRegistrationBean srbean = (StudentRegistrationBean) session.getAttribute("studentInfo");
		StudentRegistrationDAO dao = new StudentRegistrationDAO();

		String studentId = srbean.getStudentId();
		String studentFname = srbean.getStudentFname();
		String studentLname = srbean.getStudentLname();
		String classId = srbean.getClassId();
		String studentMail = srbean.getStudentMail();
		String studentPass = srbean.getStudentPass();
		String questionId = srbean.getQuestionId();
		String answer = srbean.getAnswer();
		String salt = srbean.getSalt();
		String send = "";

		if (dao.studentRegistration(studentId, studentPass, studentFname, studentLname, studentMail, classId, questionId, answer, salt)) {
			session.removeAttribute("studentInfo");
			send = "登録完了画面";

		}else{
			send = "エラー画面";
		}
		response.sendRedirect(send);



	}

}
