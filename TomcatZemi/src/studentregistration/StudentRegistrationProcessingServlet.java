package studentregistration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.ErrorBean;
import bean.StudentRegistrationBean;
import dao.MailDeliverySettingDAO;
import dao.StudentRegistrationDAO;

/**
 * Servlet implementation class StudentRegistrationProcessingServlet
 */
@WebServlet("/StudentRegistrationProcessingServlet")
public class StudentRegistrationProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentRegistrationProcessingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentRegistrationBean srbean = (StudentRegistrationBean) session.getAttribute("studentInfo");
		ErrorBean errorBean;
		StudentRegistrationDAO dao = new StudentRegistrationDAO();
		MailDeliverySettingDAO mdsdao = new MailDeliverySettingDAO();

		String studentId = srbean.getStudentId();
		String studentFname = srbean.getStudentFname();
		String studentLname = srbean.getStudentLname();
		String classId = srbean.getClassId();
		String studentMail = srbean.getStudentMail();
		String studentPass = srbean.getStudentPass();
		String questionId = srbean.getQuestionId();
		String answer = srbean.getAnswer();
		String salt = srbean.getSalt();
		String send = "";

		srbean = dao.studentMajorGet(classId);
		int majorId = srbean.getMajorId();


		if(StringUtils.isEmpty(studentId) || StringUtils.isEmpty(studentFname) || StringUtils.isEmpty(studentLname) || StringUtils.isEmpty(classId)){

			send = "error.jsp";
			errorBean = new ErrorBean();
			errorBean.setErrorMessage("入力された値に空値があります。");
			errorBean.setNextUrl("account_entry.html");
			session.setAttribute("errorInfo", errorBean);

		}else{

			if (dao.studentRegistration(studentId, studentPass, studentFname, studentLname, studentMail, classId, majorId, questionId, answer, salt)) {

				int categoryId1 = 1;
				int categoryId2 = 3;
				int categoryId3 = 4;
				int categoryId4 = 5;
				int categoryId5 = 6;

				if (mdsdao.mailDeliverySettingStart(studentId, categoryId1, categoryId2, categoryId3, categoryId4, categoryId5)) {
					session.removeAttribute("studentInfo");
					send = "account_comp-of-enrty.html";

				}else{
					send = "error.jsp";
					errorBean = new ErrorBean();
					errorBean.setErrorMessage("自動メール配信設定時にエラーが発生しました。");
					errorBean.setNextUrl("AkauntTourokuTest.jsp");
					session.setAttribute("errorInfo", errorBean);
				}



			}else{
				send = "error.jsp";
				errorBean = new ErrorBean();
				errorBean.setErrorMessage("生徒情報の登録時にエラーが発生しました。");
				errorBean.setNextUrl("account_entry.html");
				session.setAttribute("errorInfo", errorBean);
			}
			response.sendRedirect(send);




		}
	}

}
