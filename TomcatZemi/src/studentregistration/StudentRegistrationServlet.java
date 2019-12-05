package studentregistration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.ErrorBean;
import bean.StudentRegistrationBean;
import dao.StudentRegistrationDAO;
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
		StudentRegistrationDAO dao = new StudentRegistrationDAO();
		ErrorBean errorBean;
		CreateSalt cSalt = new CreateSalt();


		String studentId = request.getParameter("uid");
		String studentFname = request.getParameter("sfname");
		String studentLname = request.getParameter("slname");
		String classId = request.getParameter("classId");
		String studentMail = ("test");
		String studentPass = ("1234");
		String questionId = ("q1");
		String answer = ("1");
		String salt = cSalt.createSalt();
		String send = "error.jsp";



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
			send = "error.jsp";
			errorBean = new ErrorBean();
			errorBean.setErrorMessage("入力された値に空値があります。");
			errorBean.setNextUrl("account_entry.html");
			session.setAttribute("errorInfo", errorBean);


		}else{

			if (!dao.findId(srbean.getStudentId())) {

			session.setAttribute("studentInfo", srbean);
			send="AkauntTourokuTest.jsp";

			}else{

				send = "error.jsp";
				errorBean = new ErrorBean();
				errorBean.setErrorMessage("同じIDのユーザが既に登録されています。");
				errorBean.setNextUrl("account_entry.html");
				session.setAttribute("errorInfo", errorBean);

			}
		}
		response.sendRedirect(send);

	}
}
