package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tweet;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// つぶやきリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Tweet> tweetList = (List<Tweet>)application.getAttribute("tweetList");
		// 取得できなかった場合は、つぶやきリストを新規作成して
		// アプリケーションスコープに保存
		if(tweetList == null) {
			tweetList = new ArrayList<>();
			application.setAttribute("tweetList", tweetList);
		}

		// ログインチェック
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			// ログインしていない場合はTOPページへリダイレクト
			response.sendRedirect("/kokaTweet/");
		} else {
			// ログイン済みの場合はMainページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
