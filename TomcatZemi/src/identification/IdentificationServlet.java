package identification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import bean.IdentificationBean;
import dao.GetSaltDAO;
import dao.IdentificationDAO;

/**
 * Servlet implementation class IdentificationServlet
 */
@WebServlet("/IdentificationServlet")
public class IdentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdentificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		String questionId = request.getParameter("questionId");
		String studentMail = request.getParameter("studentMail");
		String answer = request.getParameter("answer");


		IdentificationBean ibean = new IdentificationBean();
		GetSaltDAO gsdao = new GetSaltDAO();
		IdentificationDAO idao = new IdentificationDAO();
		String send = "";

		String salt = gsdao.getSalt(studentId);

		if(StringUtils.isEmpty(ibean.getStudentId()) || StringUtils.isEmpty(ibean.getQuestionId()) || StringUtils.isEmpty(ibean.getStudentMail()) || StringUtils.isEmpty(ibean.getAnswer())){
			send = "未回答エラー画面";


		}else{

			if (idao.identification(studentId, studentMail, answer, salt, questionId)) {
				send = "メール送信へ";

			}else{

				send = "照合エラー画面";
			}

		}
		response.sendRedirect(send);




	}

}
