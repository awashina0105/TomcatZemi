package newsget;

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

import bean.LatestNewsGetArrayBean;
import dao.LatestNewsGetDAO;

/**
 * Servlet implementation class CategoryIdNewsGetServlet
 */
@WebServlet("/CategoryIdNewsGetServlet")
public class CategoryIdNewsGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryIdNewsGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LatestNewsGetArrayBean latestNewsGetArray = new LatestNewsGetArrayBean();
		LatestNewsGetDAO lngdao = new LatestNewsGetDAO();
		String send = "エラー画面";

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String today1 = sd.format(cal.getTime());
		Date today = Date.valueOf(today1);
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

			latestNewsGetArray = lngdao.CategoryIdNewsGet(today, categoryId);


		if (latestNewsGetArray.getArrayListSize() != 0){
			session.setAttribute("categoryNews", latestNewsGetArray);
			send ="カテゴリー別お知らせ表示画面";
		}
		response.sendRedirect(send);
	}

}
