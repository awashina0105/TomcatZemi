package passchange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.TeacherLoginBean;
import dao.GetSaltDAO;
import dao.PassChangeDAO;

/**
 * Servlet implementation class PassChangeServlet
 */
@WebServlet("/TeacherPassChangeServlet")
public class TeacherPassChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherPassChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		TeacherLoginBean tlbean = (TeacherLoginBean) session.getAttribute("teacherLoginInfo");

		String teacherId = tlbean.getTeacherId();

		GetSaltDAO gsdao = new GetSaltDAO();
		PassChangeDAO pcdao = new PassChangeDAO();
		String teacherNewPass = request.getParameter("teacherNewPass");
		String confirmationPass = request.getParameter("confirmationPass");
		String send = "";

		if(StringUtils.isEmpty(teacherNewPass) || StringUtils.isEmpty(confirmationPass)){

			send = "空白エラー画面";

		}else{

			if (teacherNewPass.equals(confirmationPass)) {
				String salt = gsdao.getTeacherSalt(teacherId);

				if (pcdao.teacherPassChange(teacherId, teacherNewPass, salt)) {
					send = "変更完了画面";

				}else{
					send = "変更エラー画面";
				}
			}else{
				send = "新パスと確認パスが一致しないエラー画面";
			}




		}
		response.sendRedirect(send);

	}
}
