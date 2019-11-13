package passreset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TeacherInfoBean;
import dao.GetSaltDAO;
import dao.PassResetDAO;

/**
 * Servlet implementation class TeacherPassResetCheckServlet
 */
@WebServlet("/TeacherPassResetCheckServlet")
public class TeacherPassResetCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPassResetCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TeacherInfoBean tibean = (TeacherInfoBean) session.getAttribute("teacherinfo");
		PassResetDAO prdao = new PassResetDAO();
		GetSaltDAO gsdao = new GetSaltDAO();
		String send = "エラー画面";

		String teacherId = tibean.getTeacherId();
		String salt = gsdao.getTeacherSalt(teacherId);

		if (prdao.passReset(teacherId, salt)) {
			session.removeAttribute("teacherinfo");
			send = "パスワード初期化完了画面";
		}else{
			send = "エラー画面";
		}

		response.sendRedirect(send);


	}

}
