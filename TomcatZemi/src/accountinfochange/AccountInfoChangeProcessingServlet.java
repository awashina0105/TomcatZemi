package accountinfochange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentNewInfoBean;
import dao.AccountInfoChangeDAO;

/**
 * Servlet implementation class AccountInfoChangeProcessingServlet
 */
@WebServlet("/AccountInfoChangeProcessingServlet")
public class AccountInfoChangeProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfoChangeProcessingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountInfoChangeDAO aicdao = new AccountInfoChangeDAO();
		String send = "エラー画面";
		StudentNewInfoBean snibean = (StudentNewInfoBean) session.getAttribute("newstudentinfo");
		String studentId = snibean.getStudentId();
		String studentNewLname = snibean.getStudentLname();
		String studentNewFname = snibean.getStudentFname();
		String classNewId = snibean.getClassId();

		if (aicdao.AccountInfoChange(studentId, studentNewLname, studentNewFname, classNewId)) {
			session.removeAttribute("newstudentinfo");
			session.removeAttribute("studentinfo");
			session.removeAttribute("studentClassInfoarray");
			session.removeAttribute("studenNameInfotarray");
			session.removeAttribute("studenIdInfo");
			session.removeAttribute("studentarray");
			send = "アカウント情報変更完了画面";
		}else{
			send = "エラー画面";
		}
		response.sendRedirect(send);
	}

}
