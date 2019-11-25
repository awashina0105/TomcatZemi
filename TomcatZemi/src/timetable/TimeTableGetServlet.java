package timetable;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.TimeBean;
import bean.TimeTable_ArrayBean;
import dao.TimeDAO;

/**
 * Servlet implementation class TimeTableGetServlet
 */
@WebServlet("/TimeTableGetServlet")
public class TimeTableGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeTableGetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TimeBean tbean = new TimeBean();
		TimeDAO tdao = new TimeDAO();
		TimeTable_ArrayBean ttarraybean = new TimeTable_ArrayBean();
		String send = "";

		LoginBean lbean = (LoginBean) session.getAttribute("stuid");
		String studentId = lbean.getStudentId();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		String today1 = sd.format(cal.getTime());
		Date today = Date.valueOf(today1);





		tbean = tdao.user_Info_Search(studentId);
		String classId = tbean.getClassId();
		int majorId = tbean.getMajorId();

		ttarraybean = tdao.TimeTable_InfoGet(majorId, classId, today);

		int flag = ttarraybean.getArrayListSize();
		for(TimeBean ti : ttarraybean.getTimeArray()){
			String lessonId =tbean.getLessonId();
			tdao.subjectTable_InfoGet(lessonId);
			String subjectId = tbean.getSubjectId();
			ttarraybean = tdao.subjectName_InfoGet(subjectId);

			flag--;
		}

		if (ttarraybean.getArrayListSize() != 0) {
			session.setAttribute("timeTableArray", ttarraybean);
			send = "時間割表示画面";

		}else{
			send = "エラー画面";


		}
		response.sendRedirect(send);
	}

}
