package passreset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.TeacherAccountInfoChangeBean;
import dao.PassResetDAO;

/**
 * Servlet implementation class TeacherPassResetServlet
 */
@WebServlet("/TeacherPassResetServlet")
public class TeacherPassResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPassResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TeacherAccountInfoChangeBean tibean = new TeacherAccountInfoChangeBean();
		PassResetDAO prdao = new PassResetDAO();
		String send = "エラー画面";

		String teacherId = request.getParameter("teacherId");

		tibean = prdao.TeacherIdSearch(teacherId);

		String teacherIdCheck = tibean.getTeacherId();

		if (StringUtils.isEmpty(teacherIdCheck)) {
			send = "エラー画面";

		}else{
			session.setAttribute("teacherinfo", tibean);

			send = "初期化確認画面";
		}


		response.sendRedirect(send);

	}

}
