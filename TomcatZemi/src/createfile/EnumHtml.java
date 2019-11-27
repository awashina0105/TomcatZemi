package createfile;

public enum EnumHtml {

	STUDENT_HEADER ( //最後に日付が入る
			  "<!DOCTYPE html>"
			+ "<html>"
			+ "<head>"
			+ "<link rel=\"stylesheet\" href=\"menu.css\"/><!-- メニューバー及び画面全体設定のCSS -->"
			+ "<link rel=\"stylesheet\" href=\"menu2.css\"/><!-- メニューバーから下の画面設定 -->"
			+ "<link rel=\"stylesheet\" href=\"table.css\"/>"
			+ "<meta charset=\"UTF-8\">"
			+ "<title>お知らせ画面</title>"
			+ "</head>"
			+ "<body>"
			+ "<hader>"
			+ "<div class=\"inner\">"
			+ "<h1><a href=\"menuTest.html\"><img src=\"img/zanteilogo.png\" alt=\"暫定ロゴ\"</a></h1>"
			+ "</div>"
			+ "</header>"
			+ "<nav id=\"menubar\" class=\"nav-fix-pos\">"
			+ "<ul class=\"inner\">"
			+ "<li class=\"home\"><a href=\"menuTest.html\">ホーム<span>home</span></a></li>"
			+ "<li class=\"schedule\"><a href=\"\">時間割確認<span>schedule</span></a></li>"
			+ "<li class=\"absence\"><a href=\"kessekirenraku.html\">欠席連絡<span>absence</span></a></li>"
			+ "<li class=\"account\"><a href=\"\">アカウント<br>情報変更<span>account</span></a></li>"
			+ "<li class=\"logout\"><a href=\"\">ログアウト<span>logout</span></a></li>"
			+ "</ul>"
			+ "</nav>"
			+ "<div class=\"inner\">"
			+ "<div class=\"mw-body\">"
			+ "<div class=\"mw-body-day\">"
			),

	TITLE ( //最後にタイトルが入る
			  "<div class=\"mw-body-header\">"
			+ "<div class=\"mw-body-title\" >\"),"
			),

	CATEGRY ( //最後にカテゴリーが入る
			"<div class=\"mw-body-category\"> カテゴリー："),

	BODY_START ( //最後に本文が入る
			  "</div>"
			+ "</div>"
			+ "<div class=\"mw-body-line^\"></div>"
			+ "</div>"
			+ "<div class=\"mw-body-content\">"
			+ "<div id=\"main\">"
			),

	BODY_END( //ｈｔｍｌ終了
			  "</div>"
			+ "<div class=\"mw-body-footer\">"
			+ "<div id=\"next\"><a href=\"\" style=\"text-decoration: none;\">◀</a></div>"
			+ "<div id=\"top\"><a href=\"menuTest.html\">ホームに戻る</a></div>"
			+ "<div id=\"prev\"><a href=\"\" style=\"text-decoration: none;\">▶</a></div>"
			+ "</div>"
			+ "</div>"
			+ "</div>"
			+ "</body>"
			+ "</html>"
			);

	private String html;

	private EnumHtml(String html){
		this.html = html;
	}

	public String getHtml(){
		return html;
	}


}
