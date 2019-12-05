package secretquestionreset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRegistrationBean;
import dao.SecretQuestionResetDAO;

/**
 * Servlet implementation class StudentdeleteServlet
 */
@WebServlet("/SecretQuestionResetCheckServlet")
public class SecretQuestionResetCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecretQuestionResetCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SecretQuestionResetDAO sqrdao = new SecretQuestionResetDAO();
		String questionNumber = "1";
		String send = "";

		StudentRegistrationBean srbean = (StudentRegistrationBean) session.getAttribute("studentinfo");

		String studentId = srbean.getStudentId();

		if (sqrdao.SecretQuestionReset(studentId, questionNumber)) {
			session.removeAttribute("studentClassInfoarray");
			session.removeAttribute("studenNameInfotarray");
			session.removeAttribute("studenIdInfo");

			session.removeAttribute("studentarray");
			session.removeAttribute("studentinfo");
			send = "秘密の質問初期化完了画面";
		}else{
			send = "初期化エラー画面";
		}
		response.sendRedirect(send);
	}

}
