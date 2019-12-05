package passchange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.LoginBean;
import dao.GetSaltDAO;
import dao.PassChangeDAO;

/**
 * Servlet implementation class PassChangeServlet
 */
@WebServlet("/PassChangeServlet")
public class PassChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		LoginBean lbean = (LoginBean) session.getAttribute("stuid");

		String studentId = lbean.getStudentId();

		GetSaltDAO gsdao = new GetSaltDAO();
		PassChangeDAO pcdao = new PassChangeDAO();
		String studentNewPass = request.getParameter("studentNewPass");
		String confirmationPass = request.getParameter("confirmationPass");
		String send = "";

		if(StringUtils.isEmpty(studentNewPass) || StringUtils.isEmpty(confirmationPass)){

			send = "空白エラー画面";

		}else{

			if (studentNewPass.equals(confirmationPass)) {
				String salt = gsdao.getSalt(studentId);

				if (pcdao.passChange(studentId, studentNewPass, salt)) {
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
