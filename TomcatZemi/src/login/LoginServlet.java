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
import dao.GetSaltDAO;
import dao.LoginDAO;
import dao.ResponsibleClassGetDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginBean lbean = new LoginBean();
		TeacherLoginBean tlbean = new TeacherLoginBean();
		GetSaltDAO gdao = new GetSaltDAO();
		LoginDAO ldao = new LoginDAO();
		ResponsibleClassGetDAO rcgdao = new ResponsibleClassGetDAO();

		String send ="";
		String salt = "";
		String studentId = request.getParameter("studentId");
		String studentPass = request.getParameter("studentPass");
		String studentLname = "";

		salt = gdao.getSalt(studentId);
		lbean = ldao.studentNameGet(studentId);
		studentLname = lbean.getStudentLname();

		if(ldao.login(studentId, studentPass, salt)){
			lbean.setStudentId(studentId);
			lbean.setStudentLname(studentLname);
			session.setAttribute("stuid", lbean);
			send ="ログイン";

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
				send ="ログイン";

			}else{
				send ="エラー";
			}


		}
		response.sendRedirect(send);
	}

}
