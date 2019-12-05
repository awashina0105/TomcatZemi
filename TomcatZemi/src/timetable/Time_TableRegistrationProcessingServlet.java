package timetable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FridayBean;
import bean.MondayBean;
import bean.TeacherLoginBean;
import bean.ThursdayBean;
import bean.TimeBean;
import bean.TuesdayBean;
import bean.WednesdayBean;
import dao.TimeDAO;

/**
 * Servlet implementation class Time_TableRegistrationProcessingServlet
 */
@WebServlet("/Time_TableRegistrationProcessingServlet")
public class Time_TableRegistrationProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Time_TableRegistrationProcessingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TimeDAO timedao = new TimeDAO();

		List<String> timeTable_list = new ArrayList<String>();

		TimeBean tbean = (TimeBean) session.getAttribute("timeTableInfo");
		MondayBean mondaybean = (MondayBean) session.getAttribute("mondaySubject");
		MondayBean mondayClassbean = (MondayBean) session.getAttribute("mondayClassRoom");
		TuesdayBean tuesdaybean = (TuesdayBean) session.getAttribute("tuesdaySubject");
		TuesdayBean tuesdayClassbean = (TuesdayBean) session.getAttribute("tuesdayClassRoom");
		WednesdayBean wednesdaybean = (WednesdayBean) session.getAttribute("wednesdaySubject");
		WednesdayBean wednesdayClassbean = (WednesdayBean) session.getAttribute("wednesdayClassRoom");
		ThursdayBean thursdaybean = (ThursdayBean) session.getAttribute("thursdaySubject");
		ThursdayBean thursdayClassbean = (ThursdayBean) session.getAttribute("thursdayClassRoom");
		FridayBean fridaybean = (FridayBean) session.getAttribute("fridaySubject");
		FridayBean fridayClassbean = (FridayBean) session.getAttribute("fridayClassRoom");

		String monday[] = mondaybean.getMonday();
		String tuesday[] = tuesdaybean.getTuesday();
		String wednesday[] = wednesdaybean.getWednesday();
		String thursday[] = thursdaybean.getThursday();
		String friday[] = fridaybean.getFriday();
		String mondayClassRoom[] = mondayClassbean.getMondayClassRoom();
		String tuesdayClassRoom[] = tuesdayClassbean.getTuesdayClassRoom();
		String wednesdayClassRoom[] = wednesdayClassbean.getWednesdayClassRoom();
		String thursdayClassRoom[] = thursdayClassbean.getThursdayClassRoom();
		String fridayClassRoom[] = fridayClassbean.getFridayClassRoom();
		int majorId = tbean.getMajorId();
		int yearMonth = tbean.getYearMonth();
		TeacherLoginBean tlbean = (TeacherLoginBean) session.getAttribute("teacherLoginInfo");
		String classId = tlbean.getClassId();
		String room = "";

		String dayTime = "";
		String subjectId = "";
		String send = "時間割登録完了画面";
		String comparison = "エラー画面";  //比較用
		String lessonId = "";

		for (int i = 0; i < monday.length; i++) {
			dayTime = "Monday" + "_" + (i + 1);
			subjectId = monday[i];
			room = mondayClassRoom[i];
			tbean = timedao.lessonIdSearch(subjectId);
			lessonId = tbean.getLessonId();

			if (timedao.TimeTable_Registration(majorId, yearMonth, dayTime, classId, room, lessonId)) {
				break;

			}else{
				send = "エラー画面";
			}
        }

		for (int i = 0; i < tuesday.length; i++) {
			dayTime = "Tuesday" + "_" + (i + 1);
			subjectId = tuesday[i];
			room = tuesdayClassRoom[i];
			timedao.lessonIdSearch(subjectId);
			lessonId = tbean.getLessonId();

			if (timedao.TimeTable_Registration(majorId, yearMonth, dayTime, classId, room, lessonId)) {
				break;

			}else{
				send = "エラー画面";
			}
        }

		for (int i = 0; i < wednesday.length; i++) {
			dayTime = "Wednesday" + "_" + (i + 1);
			subjectId = wednesday[i];
			room = wednesdayClassRoom[i];
			timedao.lessonIdSearch(subjectId);
			lessonId = tbean.getLessonId();

			if (timedao.TimeTable_Registration(majorId, yearMonth, dayTime, classId, room, lessonId)) {
				break;

			}else{
				send = "エラー画面";
			}
        }

		for (int i = 0; i < thursday.length; i++) {
			dayTime = "Thursday" + "_" + (i + 1);
			subjectId = thursday[i];
			room =thursdayClassRoom[i];
			timedao.lessonIdSearch(subjectId);
			lessonId = tbean.getLessonId();

			if (timedao.TimeTable_Registration(majorId, yearMonth, dayTime, classId, room, lessonId)) {
				break;

			}else{
				send = "エラー画面";
			}
        }

		for (int i = 0; i < friday.length; i++) {
			dayTime = "Friday" + "_" + (i + 1);
			subjectId = friday[i];
			room = fridayClassRoom[i];
			timedao.lessonIdSearch(subjectId);
			lessonId = tbean.getLessonId();

			if (timedao.TimeTable_Registration(majorId, yearMonth, dayTime, classId, room, lessonId)) {
				break;

			}else{
				send = "エラー画面";
			}






			if (comparison.equals(send)) { //完了画面だったらセッションを削除。
				break;

			}else{
				session.removeAttribute("timeTableInfo");
				session.removeAttribute("mondaySubject");
				session.removeAttribute("tuesdaySubject");
				session.removeAttribute("wednesdaySubject");
				session.removeAttribute("thursdaySubject");
				session.removeAttribute("fridaySubject");
				session.removeAttribute("mondayClassRoom");
				session.removeAttribute("tuesdayClassRoom");
				session.removeAttribute("wednesdayClassRoom");
				session.removeAttribute("thursdayClassRoom");
				session.removeAttribute("fridayClassRoom");
				break;
			}


        }
		response.sendRedirect(send);
	}

}
