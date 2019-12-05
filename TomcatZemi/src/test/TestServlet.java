package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.ErrorBean;
import bean.LoginBean;
import bean.TeacherLoginBean;
import dao.GetSaltDAO;
import dao.LoginDAO;
import dao.ResponsibleClassGetDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginBean lbean = new LoginBean();
		TeacherLoginBean tlbean = new TeacherLoginBean();
		ErrorBean errorBean;
		GetSaltDAO gdao = new GetSaltDAO();
		LoginDAO ldao = new LoginDAO();
		ResponsibleClassGetDAO rcgdao = new ResponsibleClassGetDAO();

		String send ="";
		String salt = "";
		String studentId = request.getParameter("uid");
		String studentPass = request.getParameter("pass");
		String studentLname = "";

		lbean = ldao.studentNameGet(studentId);
		studentLname = lbean.getStudentLname();
		salt = gdao.getSalt(studentId);

		if (StringUtils.isEmpty(studentId) || StringUtils.isEmpty(studentPass)) {
			send = "error.jsp";
			errorBean = new ErrorBean();
			errorBean.setErrorMessage("ID、またはパスワードが間違っています。");
			errorBean.setNextUrl("login.html");
			session.setAttribute("errorInfo", errorBean);
		}else{



			if(ldao.login(studentId, studentPass, salt)){
				lbean.setStudentId(studentId);
				lbean.setStudentLname(studentLname);
				session.setAttribute("stuid", lbean);
				send ="menuTest.jsp";

			}else{
				String teacherId = studentId;
				String teacherPass = studentPass;
				salt = gdao.getTeacherSalt(teacherId);
				tlbean = rcgdao.responsibleClassGet(teacherId);
				String classId = tlbean.getClassId();

				if (ldao.teacherLogin(teacherId, teacherPass, salt)) {

					tlbean.setTeacherId(teacherId);
					tlbean.setClassId(classId);
					session.setAttribute("teacherLoginInfo", tlbean);
					send ="menu_teacher_test.html";

				}else{
					send = "error.jsp";
					errorBean = new ErrorBean();
					errorBean.setErrorMessage("ID、またはパスワードが間違っています。");
					errorBean.setNextUrl("login.html");
					session.setAttribute("errorInfo", errorBean);

				}
			}



		}
		response.sendRedirect(send);
	}


}
