package teacheraccountinfochange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.TeacherAccountInfoChangeBean;

/**
 * Servlet implementation class TeacherAccountInfoChangeCheckServlet
 */
@WebServlet("/TeacherAccountInfoChangeCheckServlet")
public class TeacherAccountInfoChangeCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherAccountInfoChangeCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String send = "";
		TeacherAccountInfoChangeBean taicbean = (TeacherAccountInfoChangeBean) session.getAttribute("teacherAccountInfo");
		TeacherAccountInfoChangeBean newTaicbean = new TeacherAccountInfoChangeBean();

		String teacherId = taicbean.getTeacherId();
		String teacherNewLname = request.getParameter("teacherNewLname");
		String teacherNewFname = request.getParameter("teacherNewFname");
		String role = request.getParameter("newRole");
		String classNewId = request.getParameter("classNewId");
		String subjectNewName = request.getParameter("subjectNewName");

		if(StringUtils.isEmpty(teacherId) || StringUtils.isEmpty(teacherNewLname) || StringUtils.isEmpty(teacherNewFname) || StringUtils.isEmpty(classNewId) || StringUtils.isEmpty(subjectNewName) || StringUtils.isEmpty(role)){
			send = "空欄エラー画面";

		}else{
			newTaicbean.setTeacherId(teacherId);
			newTaicbean.setTeacherLname(teacherNewLname);
			newTaicbean.setTeacherFname(teacherNewFname);
			newTaicbean.setRole(Integer.parseInt(role));
			newTaicbean.setClassId(classNewId);;
			newTaicbean.setSubjectName(subjectNewName);
			session.setAttribute("teacherNewAccountInfo", newTaicbean);
			send = "先生のアカウント情報確認画面";
		}
		response.sendRedirect(send);
	}

}
