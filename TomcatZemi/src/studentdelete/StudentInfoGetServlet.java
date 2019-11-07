package studentdelete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentArrayBean;
import dao.StudentInfoGetDAO;

/**
 * Servlet implementation class StudentInfoGetServlet
 */
@WebServlet("/StudentInfoGetServlet")
public class StudentInfoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentInfoGetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentArrayBean arraybean = new StudentArrayBean();
		StudentInfoGetDAO sigdao = new StudentInfoGetDAO();
		String send ="エラー画面";

		arraybean = sigdao.studentInfoGet();

		int arraysize = arraybean.getArrayListSize();

		if (0 == arraysize) {
			send ="エラー画面";

		}else{

			session.setAttribute("studentarray", arraybean);
			send ="生徒削除の一覧画面";
		}
		response.sendRedirect(send);

	}

}
