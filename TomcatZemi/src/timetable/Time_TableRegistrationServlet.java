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
import bean.ThursdayBean;
import bean.TimeBean;
import bean.TuesdayBean;
import bean.WednesdayBean;
import dao.TimeDAO;

/**
 * Servlet implementation class Time_TableRegistrationServlet
 */
@WebServlet("/Time_TableRegistrationServlet")
public class Time_TableRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Time_TableRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TimeBean tbean = new TimeBean();
		MondayBean mondaybean = new MondayBean();
		TuesdayBean tuesdaybean = new TuesdayBean();
		WednesdayBean wednesdaybean = new WednesdayBean();
		ThursdayBean thursdaybean = new ThursdayBean();
		FridayBean fridaybean = new FridayBean();
		TimeDAO timedao = new TimeDAO();

		List<String> timeTable_list = new ArrayList<String>();

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String senkou = request.getParameter("senkou");
		int yearMonth = Integer.parseInt(year + month);
		String monday[] = request.getParameterValues("monday[]");
		String tuesday[] = request.getParameterValues("tuesday[]");
		String wednesday[] = request.getParameterValues("wednesday[]");
		String thursday[] = request.getParameterValues("thursday[]");
		String friday[] = request.getParameterValues("friday[]");

		mondaybean.setMonday(monday);
		tuesdaybean.setTuesday(tuesday);
		wednesdaybean.setWednesday(wednesday);
		thursdaybean.setThursday(thursday);
		fridaybean.setFriday(friday);



		tbean = timedao.majorIdSearch(senkou);
		tbean.setYearMonth(yearMonth);
		tbean.setYear(year);
		tbean.setMonth(month);
		tbean.setSenkou(senkou);


		session.setAttribute("timeTableInfo", tbean);
		session.setAttribute("mondaySubject", mondaybean);
		session.setAttribute("tuesdaySubject", tuesdaybean);
		session.setAttribute("wednesdaySubject", wednesdaybean);
		session.setAttribute("thursdaySubject", thursdaybean);
		session.setAttribute("fridaySubject", fridaybean);

		response.sendRedirect("時間割登録確認画面");


	}

}
