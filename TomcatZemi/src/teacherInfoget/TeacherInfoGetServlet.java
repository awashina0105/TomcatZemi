package teacherInfoget;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TeacherArrayBean;
import dao.TeacherInfoDisplayDAO;

/**
 * Servlet implementation class TeacherInfoGetServlet
 */
@WebServlet("/TeacherInfoGetServlet")
public class TeacherInfoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherInfoGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TeacherArrayBean tarraybean = new TeacherArrayBean();
		TeacherInfoDisplayDAO tiddao = new TeacherInfoDisplayDAO();
		String send = "エラー画面";

		tarraybean = tiddao.TeacherInfoGet();

		int i = tarraybean.getArrayListSize();

		if (0 == i) {
			send = "エラー画面";
		}else{
			session.setAttribute("teacherarray", tarraybean);
			send = "パスワード初期化の先生一覧画面";
		}
		response.sendRedirect(send);
	}

}
