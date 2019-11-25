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
public class Time_TableRegistration2_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Time_TableRegistration2_Servlet() {
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

		String mondayClassRoom[] = request.getParameterValues("monday[]");
		String tuesdayClassRoom[] = request.getParameterValues("tuesday[]");
		String wednesdayClassRoom[] = request.getParameterValues("wednesday[]");
		String thursdayClassRoom[] = request.getParameterValues("thursday[]");
		String fridayClassRoom[] = request.getParameterValues("friday[]");

		mondaybean.setMondayClassRoom(mondayClassRoom);
		tuesdaybean.setTuesdayClassRoom(tuesdayClassRoom);
		wednesdaybean.setWednesdayClassRoom(wednesdayClassRoom);
		thursdaybean.setThursdayClassRoom(thursdayClassRoom);
		fridaybean.setFridayClassRoom(fridayClassRoom);



		session.setAttribute("mondayClassRoom", mondaybean);
		session.setAttribute("tuesdayClassRoom", tuesdaybean);
		session.setAttribute("wednesdayClassRoom", wednesdaybean);
		session.setAttribute("thursdayClassRoom", thursdaybean);
		session.setAttribute("fridayClassRoom", fridaybean);

		response.sendRedirect("時間割登録2確認画面");


	}

}
