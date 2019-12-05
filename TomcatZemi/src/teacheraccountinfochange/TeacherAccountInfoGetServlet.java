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
 * Servlet implementation class TeacherAccountInfoGetServlet
 */
@WebServlet("/TeacherAccountInfoGetServlet")
public class TeacherAccountInfoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherAccountInfoGetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TeacherAccountInfoChangeDAO taicdao = new TeacherAccountInfoChangeDAO();
		String send = "";

		String teacherId = request.getParameter("teacherId");

		TeacherAccountInfoChangeBean taicbean = new TeacherAccountInfoChangeBean();

		taicbean = taicdao.Teacher_TableInfoSearch(teacherId);

		String teacherIdCheck = taicbean.getTeacherId();

		if (StringUtils.isEmpty(teacherIdCheck)) {
			send = "エラー画面";

		}else{

			taicbean = taicdao.Class_TableInfoSearch(teacherId);
			String classIdCheck = taicbean.getClassId();

			if (StringUtils.isEmpty(classIdCheck)) {
				send = "エラー画面";

			}else{

				taicbean = taicdao.Lesson_TableInfoSearch(teacherId);
				String subjectIdCheck = taicbean.getSubjectId();

				if (StringUtils.isEmpty(subjectIdCheck)) {
					send = "エラー画面";

				}else{

					String subjectId = taicbean.getSubjectId();
					taicbean = taicdao.SubjectNameSearch(subjectId);
					String subjectNameCheck = taicbean.getSubjectName();

					if (StringUtils.isEmpty(subjectNameCheck)) {
						send = "エラー画面";

					}else{

						session.setAttribute("teacherAccountInfo", taicbean);
						send = "先生のアカウント情報変更画面";
					}
				}
			}
		}

		response.sendRedirect(send);
	}

}
