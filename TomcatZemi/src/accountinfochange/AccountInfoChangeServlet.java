package accountinfochange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.StudentInfoBean;
import dao.StudentDeleteDAO;

/**
 * Servlet implementation class AccountInfoChangeServlet
 */
@WebServlet("/AccountInfoChangeServlet")
public class AccountInfoChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfoChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentDeleteDAO sddao = new StudentDeleteDAO();
		String send = "";

		String studentId = request.getParameter("studentId");

		StudentInfoBean sibean = sddao.StudentInfoSearch(studentId);

		String studentIdCheck = sibean.getStudentId();

		if (StringUtils.isEmpty(studentIdCheck)) {
			send = "エラー画面";

		}else{
			session.setAttribute("studentinfo", sibean);

			send = "アカウント情報変更画面";
		}

		response.sendRedirect(send);
	}

}