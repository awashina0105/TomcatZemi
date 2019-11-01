package studentregistration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.StudentRegistrationBean;
import salt.CreateSalt;

/**
 * Servlet implementation class StudentRegistrationServlet
 */
@WebServlet("/StudentRegistrationServlet")
public class StudentRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		StudentRegistrationBean srbean = new StudentRegistrationBean();
		CreateSalt cSalt = new CreateSalt();


		String studentId = request.getParameter("studentId");
		String studentFname = request.getParameter("studentFname");
		String studentLname = request.getParameter("studentLname");
		String classId = request.getParameter("classId");
		String studentMail = ("初期値");
		String studentPass = ("初期値");
		String questionId = ("初期値");
		String answer = ("初期値");
		String salt = cSalt.createSalt();
		String send = ("確認画面");



		srbean.setStudentId(studentId);
		srbean.setStudentFname(studentFname);
		srbean.setStudentLname(studentLname);
		srbean.setClassId(classId);
		srbean.setStudentMail(studentMail);
		srbean.setStudentPass(studentPass);
		srbean.setQuestionId(questionId);
		srbean.setAnswer(answer);
		srbean.setSalt(salt);


		if(StringUtils.isEmpty(srbean.getStudentId()) || StringUtils.isEmpty(srbean.getStudentFname()) || StringUtils.isEmpty(srbean.getStudentLname()) || StringUtils.isEmpty(srbean.getClassId())){
			session.setAttribute("srbean", srbean);
			send="登録確認画面";

		}else{
			send = "エラー画面";
		}
		response.sendRedirect(send);

	}
}
