package Test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class jikanwariServlet
 */
@WebServlet("/jikanwariServlet")
public class jikanwariServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public jikanwariServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text[] = request.getParameterValues("hoge[]");
		String text1[] = request.getParameterValues("hoge1[]");
		String text2[] = request.getParameterValues("hoge2[]");
		String text3[] = request.getParameterValues("hoge3[]");
		String text4[] = request.getParameterValues("hoge4[]");

		for(String hoge : text){
			System.out.println(hoge);
		}

		for(String hoge1 : text1){
			System.out.println(hoge1);
		}

		for(String hoge2 : text2){
			System.out.println(hoge2);
		}

		for(String hoge3 : text3){
			System.out.println(hoge3);
		}

		for(String hoge4 : text4){
			System.out.println(hoge4);
		}

	}
}
