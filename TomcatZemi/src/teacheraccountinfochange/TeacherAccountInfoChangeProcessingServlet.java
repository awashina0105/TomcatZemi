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
import dao.TeacherAccountInfoChangeDAO;

/**
 * Servlet implementation class TeacherAccountInfoChangeProcessingServlet
 */
@WebServlet("/TeacherAccountInfoChangeProcessingServlet")
public class TeacherAccountInfoChangeProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherAccountInfoChangeProcessingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String send = "";
		TeacherAccountInfoChangeDAO taicdao = new TeacherAccountInfoChangeDAO();
		TeacherAccountInfoChangeBean taicbean = (TeacherAccountInfoChangeBean) session.getAttribute("teacherNewAccountInfo");

		String teacherId = taicbean.getTeacherId();
		String teacherNewLname = taicbean.getTeacherLname();
		String teacherNewFname = taicbean.getTeacherFname();
		int role = taicbean.getRole();
		int newRole = role;
		String classNewId = taicbean.getClassId();
		String subjectNewName = taicbean.getSubjectName();

		taicbean = taicdao.SubjectIdSearch(subjectNewName);

		String subjectNewId = taicbean.getSubjectId();

		if (StringUtils.isEmpty(subjectNewId)) {
			send = "エラー画面";

		}else{

			if (taicdao.Teacher_TableAccountInfoChange(teacherId, teacherNewLname, teacherNewFname, newRole)) {

				if (taicdao.Class_TableAccountInfoChange(teacherId, classNewId)) {

					if (taicdao.Lesson_TableAccountInfoChange(teacherId, subjectNewId)) {

						session.removeAttribute("teacherarray");
						session.removeAttribute("teacherinfo");
						session.removeAttribute("teacherAccountInfo");
						session.removeAttribute("teacherNewAccountInfo");
						send = "先生のアカウント情報変更完了画面";

					}else{
						send = "エラー画面";
					}

				}else{
					send = "エラー画面";
				}

			}else{
				send = "エラー画面";

			}
		}
		response.sendRedirect(send);

	}

}
