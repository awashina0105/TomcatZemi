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
 * Servlet implementation class MailDeliverySettingServlet
 */
@WebServlet("/MailDeliverySettingServlet")
public class MailDeliverySettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MailDeliverySettingDAO mdsdao = new MailDeliverySettingDAO();
		MailDeliverySettingBean mdsbean = (MailDeliverySettingBean) session.getAttribute("maildeliveryinfo");
		String send = "";

		LoginBean lbean = (LoginBean) session.getAttribute("stuid");


		String studentId = lbean.getStudentId();

		if (StringUtils.isEmpty(studentId)) {
			send = "エラー画面";

		}else{
			int categoryId1 = mdsbean.getCategoryId1();
			int categoryId2 = mdsbean.getCategoryId2();
			int categoryId3 = mdsbean.getCategoryId3();
			int categoryId4 = mdsbean.getCategoryId4();
			int categoryId5 = mdsbean.getCategoryId5();

			if (mdsdao.mailDeliverySetting(studentId, categoryId1, categoryId2, categoryId3, categoryId4, categoryId5)) {
				session.removeAttribute("maildeliveryinfo");
				send = "変更完了画面";

			}else{
				send = "変更エラー画面";
			}
			response.sendRedirect(send);
		}

	}

}
