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

import model.PostTweetLogic;
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
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値チェック
		if(text != null && text.length() != 0) {
			// アプリケーションスコープに保存されたつぶやきリストを取得
			ServletContext application = this.getServletContext();
			List<Tweet> tweetList = (List<Tweet>) application.getAttribute("tweetList");

			// セッションスコープからユーザ情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			// つぶやきをつぶやきリストに追加
			Tweet tweet = new Tweet(loginUser.getName(), text);
			PostTweetLogic postTweetLogic = new PostTweetLogic();
			postTweetLogic.execute(tweet, tweetList);

			// アプリケーションスコープにつぶやきリストを保存
			application.setAttribute("tweetList", tweetList);
		} else {
			// エラーメッセージをリクエストスコープ に保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません。");
		}

			// メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
	}

}
