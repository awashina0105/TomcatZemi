package passconfirmation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TeacherLoginBean;
import dao.GetSaltDAO;
import dao.PassConfirmationDAO;

/**
 * Servlet implementation class TeacherPassConfirmationServlet
 */
@WebServlet("/TeacherPassConfirmationServlet")
public class TeacherPassConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPassConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		TeacherLoginBean tlbean = (TeacherLoginBean) session.getAttribute("teacherLoginInfo");
		String teacherPass = request.getParameter("teacherPass");

		GetSaltDAO gsdao = new GetSaltDAO();
		PassConfirmationDAO pcdao = new PassConfirmationDAO();
		String send = "";

		String teacherId = tlbean.getTeacherId();

		String salt = gsdao.getTeacherSalt(teacherId);

		if (pcdao.teacherPassConfirmation(teacherId, teacherPass, salt)) {
			send = "パスワード変更画面";
		}else{
			send = "エラー画面";
		}
		response.sendRedirect(send);
	}


}
