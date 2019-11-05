package passconfirmation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dao.GetSaltDAO;
import dao.PassConfirmationDAO;
import sha2.SaltUserPassword;
import sha2.ToSHA2;

/**
 * Servlet implementation class PassChangeServlet
 */
@WebServlet("/PassConfirmationServlet")
public class PassConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		LoginBean lbean = (LoginBean) session.getAttribute("stuid");
		String studentPass = request.getParameter("studentPass");

		GetSaltDAO gsdao = new GetSaltDAO();
		PassConfirmationDAO pcdao = new PassConfirmationDAO();
		String send = "";

		String studentId = lbean.getStudentId();

		String salt = gsdao.getSalt(studentId);

		ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String studentPassBox = SHA.getDigest(studentPass);
		String passHash = sa.getDigest(studentIdBox, studentPassBox, salt);

		if (pcdao.passConfirmation(studentId).equals(passHash)) {
			send = "パスワード変更画面";
		}else{
			send = "エラー画面";
		}
		response.sendRedirect(send);
	}

}
