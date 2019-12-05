package studentsearch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.StudentArrayBean;
import bean.StudentRegistrationBean;
import dao.StudentSearchDAO;

/**
 * Servlet implementation class StudentSearchServlet
 */
@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentRegistrationBean srbean = new StudentRegistrationBean();
		StudentArrayBean arraybean = new StudentArrayBean();
		StudentSearchDAO ssdao = new StudentSearchDAO();
		String send = "エラー画面";

		String name = request.getParameter("name");

		srbean = ssdao.StudentIdSearch(name);

		if (StringUtils.isEmpty(srbean.getStudentId())) {

			arraybean = ssdao.StudentNameSearch(name);

			int arraysize = arraybean.getArrayListSize();

			if (0 == arraysize) {
				arraybean = ssdao.StudentClassSearch(name);
				arraysize = arraybean.getArrayListSize();

				if (0 == arraysize) {
					send = "エラー画面";
				}else{
					session.setAttribute("studentClassInfoarray", arraybean);
					send = "検索結果表示画面";
				}
			}else{
				session.setAttribute("studenNameInfotarray", arraybean);
				send = "検索結果表示画面";
			}
		}else{
			session.setAttribute("studenIdInfo", srbean);
			send = "検索結果表示画面";
		}
		response.sendRedirect(send);

	}

}
