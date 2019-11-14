package studentdelete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDeleteDAO;

/**
 * Servlet implementation class StudentdeleteServlet
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDeleteDAO sddao = new StudentDeleteDAO();
		String send = "";

		String studentId = request.getParameter("studentId");

		if (sddao.studentdelete(studentId)) {

			send = "削除完了画面";
		}else{
			send = "削除エラー画面";
		}
		response.sendRedirect(send);
	}

}
