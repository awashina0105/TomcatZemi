package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dao.GetSaltDAO;
import dao.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginBean lbean = new LoginBean();
		GetSaltDAO gdao = new GetSaltDAO();
		LoginDAO ldao = new LoginDAO();

		String send ="";
		String salt = "";

		lbean.setStudentId(request.getParameter("studentId"));
		salt = gdao.getSalt(lbean.getStudentId());
	    session.setAttribute("stuid", lbean);

	    if(ldao.login(lbean.getStudentId(), request.getParameter("studentPass"), salt)){
	    	send ="ログイン";
	    }else{
	    	send ="エラー";
	    }

	    response.sendRedirect(send);






	}

}
