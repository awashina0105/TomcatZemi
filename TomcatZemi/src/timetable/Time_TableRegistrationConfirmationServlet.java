package timetable;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FridayBean;
import bean.MondayBean;
import bean.ThursdayBean;
import bean.TuesdayBean;
import bean.WednesdayBean;

/**
 * Servlet implementation class Time_TableRegistrationServlet
 */
@WebServlet("/Time_TableRegistrationConfirmationServlet")
public class Time_TableRegistrationConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Time_TableRegistrationConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MondayBean mondaybean = new MondayBean();
		TuesdayBean tuesdaybean = new TuesdayBean();
		WednesdayBean wednesdaybean = new WednesdayBean();
		ThursdayBean thursdaybean = new ThursdayBean();
		FridayBean fridaybean = new FridayBean();

		String mondayClassRoom[] = request.getParameterValues("mondayClass[]");
		String tuesdayClassRoom[] = request.getParameterValues("tuesdayClass[]");
		String wednesdayClassRoom[] = request.getParameterValues("wednesdayClass[]");
		String thursdayClassRoom[] = request.getParameterValues("thursdayClass[]");
		String fridayClassRoom[] = request.getParameterValues("fridayClass[]");

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
