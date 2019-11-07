package maildeliverysetting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.LoginBean;
import bean.MailDeliverySettingBean;
import dao.MailDeliverySettingDAO;

/**
 * Servlet implementation class MailDeliveryInfoGetServlet
 */
@WebServlet("/MailDeliveryInfoGetServlet")
public class MailDeliveryInfoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailDeliveryInfoGetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MailDeliverySettingDAO mdsdao = new MailDeliverySettingDAO();
		MailDeliverySettingBean mdsbean = new MailDeliverySettingBean();
		String send = "";

		LoginBean lbean = (LoginBean) session.getAttribute("stuid");

		String studentId = lbean.getStudentId();

		if (StringUtils.isEmpty(studentId)) {
			send = "エラー画面";

		}else{
			mdsbean = mdsdao.mailDeliveryInfoGet(studentId);

			session.setAttribute("maildeliveryinfo", mdsbean);

			send = "自動メール配信設定画面";
		}



		response.sendRedirect(send);

	}

}
