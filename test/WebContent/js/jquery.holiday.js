/*
 * jquery.holiday.js
 * jQuery UI datepicker 用日本の祝祭日ツールチップ表示
 *
 * 機能
 * 		datepicker で表示されるダイアログに祝祭日の表示を付加します。
 * 備考
 *		西暦2007年以降から2015年現在までの祝祭日に対応
 *		１つのWebページ内は同じ祝祭日の表示になります。
 *		（ページ内の複数のdatepickerごとに表示内容は切り替えられません。）
 * 使い方
 *		<link rel="stylesheet" href="jquery.holiday.css">
 *		<script src="jquery.holiday.js"></script>
 *
 *		$('input.xxx').datepicker().holiday();
 *
 * 地域・言語の対応
 *		他の地域や言語のために表示データを差し替えることができます。
 *		サンプル：
 *		<script src="jquery.holiday_en-JP.js"></script>
 *
 * 更新履歴
 *	2016-10-22	holiday("init")の重複実行の抑止。動的なinput要素の生成に対応。
 */

(function($) {
	var settings = {};
	var css = {};
	var holidays = {};
	var prepared = {};

	var methods = {
		//初期処理
		init : function( options ) {
			//datepicker ロードチェック
			if (!$.fn['datepicker']) {
				return this;
			}

			//オプション設定
			settings = $.extend( {
				//曜日、祝祭日のCSSスタイルクラス
				'css': [
					{'day':0, 'class':'day-sunday'}, //日曜日
					{'day':6, 'class':'day-saturday'}, //土曜日
					{'day':'holiday', 'class':'day-holiday'}, //祝祭日
					{'day':'anniversary', 'class':'day-anniversary'} //記念日
				],
				//追加する記念日 [{date:年月日, name:名称, class:CSSクラス}]
				'anniversary': []
			}, options);

			//スタイルの登録
			$.each(settings.css, function(key, val) {
				if (typeof val['class']!="undefined") {
					css[val['day']] = val['class'];
				}
			});

			//記念日の追加
			var attr;
			$.each (settings.anniversary, function(key, val){
				attr = {};
				attr['name'] = val['name'];
				if (typeof val['class']!="undefined") {
					attr['class'] = val['class'];
				} else if (typeof css['anniversary']!="undefined") {
					attr['class'] = css['anniversary'];
				}
				holidays[val['date']] = attr;
			});

			return this.each(function() {
				if (typeof $(this).datepicker!="function") {
					return true;
				}
				//初期化済みチェック
				if ($(this).data("holiday")) {
					return true;
				}
				//初期化データ設定
				$(this).data("holiday", {init: true});

				//祝祭日と日付スタイルの設定
				$(this).datepicker("option", "beforeShowDay", function(day) {
					var $self = $.fn.holiday;
					var attr = $self("attr", day);
					var dow = day.getDay();
					var title = "";
					if (typeof attr['name']!="undefined") {
						title = attr['name'];
					}
					var style = "";
					if (title && typeof attr['class']!="undefined") {
						style = attr['class']; //祝祭日、記念日のスタイル
						return [false, style, title]; // <-- 追加
					} else if (dow === 0 || dow === 6) { // <--追加
					  style = css[dow]; // <--追加
					  return [false, style, title]; // <--追加
					} else if (typeof css[dow]!="undefined") {
						style = css[dow]; //曜日のスタイル
					}

					return [true, style, title];
				});
			});

		},

		//終了処理
		destroy : function( ) {
			return this.each(function() {
				if (typeof $(this).datepicker!="function") {
					return true;
				}
				//祝祭日と日付スタイルの除去
				$(this).datepicker("option", "beforeShowDay", null);
				//初期化済みデータ消去
				$(this).removeData("holiday");
			});
		},

		//祝祭日の属性の取得
		attr : function( day ) {
			//日付が文字の場合はDate型に変換する。
			if (typeof day=="string") {
				var match = day.match(/^(20\d{2})(\d{2})(\d{2})$/);
				if (match) {
					match.shift();
					day = new Date(match.join("/"));
				} else {
					day = new Date(day);
				}
			}
			//祝祭日の計算
			var Y = day.getFullYear();
			var M = padzero(day.getMonth()+1);
			if (typeof prepared[Y+M]=="undefined") {
				prepare(Y, M);
			}
			//祝祭日のチェック
			var YMD = day.getFullYear()+padzero(day.getMonth()+1)+padzero(day.getDate());
			if (typeof holidays[YMD]!="undefined") {
				return holidays[YMD];
			} else {
				return {};
			}
		},

		//祝祭日名の取得
		name : function( day ) {
			var attr = $.fn.holiday('attr', day);
			if (typeof attr['name']=="undefined") {
				return "";
			} else {
				return attr['name'];
			}
		},

		//祝祭日一覧
		list : function() {
			return holidays;
		},

		//祝祭日データ設定
		setRegionData : function(obj) {
			if (typeof obj=="object") {
				if (typeof obj['annual']=="object") {
					annual = obj['annual'];
				}
				if (typeof obj['individual']=="object") {
					individual = obj['individual'];
				}
				if (typeof obj['words']=="object") {
					words = obj['words'];
				}
			}
		}
	};

	$.fn.holiday = function( method ) {
		if ( methods[method] ) {
			return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return methods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.holiday' );
		}
	};

	//ゼロサプレス
	var padzero = function(val) {
		return ('0'+val).slice(-2);
	}

	//祝祭日計算
	var prepare = function(Y, M) {
		//作成済みチェック
		if (typeof prepared[Y+M]!="undefined") {
			return true;
		}

		//年間の祝祭日の計算
		var attr, substitutes = {};
		if (typeof annual[M]!="undefined") {
			var d, dayyear;
			$.each(annual[M], function(key, val){
				d = null;
				if (isFinite(val['day'])) {
					//日付固定
					d = val['day'];
				} else if (val['day']=="spring equinox") {
					//春分の日
					d = parseInt(20.69115 + (Y - 2000) * 0.2421904 - parseInt((Y - 2000) / 4));
				} else if (val['day']=="autumnal equinox") {
					//秋分の日
					d = parseInt(23.09 + (Y - 2000) * 0.2421904 - parseInt((Y - 2000) / 4));
				} else if (val['day'].match(/\//)) {
					//ハッピーマンデー
					d = padzero(nthday(Y, M, val['day']));
				} else if (val.day.match(/\>/)) {
					//特定年以降
					dayyear = val['day'].split('>');
					if (Y>=dayyear[1]) {
						d = padzero(dayyear[0]);
					}
				} else if (val.day.match(/\</)){
					  //特定年以前
					  dayyear = val['day'].split('<');
					  if (Y<=dayyear[1]) {
					    d = padzero(dayyear[0]);
					  }
				}

				if (d==null) {
					return true; //continue;
				}

				//祝祭日をリストに設定
				attr = {};
				attr['name'] = val['name'];
				if (typeof val['class']!="undefined") {
					attr['class'] = val['class'];
				} else if (typeof css['holiday']!="undefined") {
					attr['class'] = css['holiday'];
				} else {
					attr['class'] = "";
				}

				holidays[Y + M + d] = attr;

				//振替休日のチェック
				d = new Date(Y, M-1, d);
				if (d.getDay()==0) {
					d = padzero(d.getDate() + 1);
					attr['name'] = words['substitute']+"("+val['name']+")";
					substitutes[Y +"/"+ M +"/"+ d] = attr;
				}
			});

			//振替休日の補正
			$.each (substitutes, function(key, val) {
				key = new Date(key);
				d = key.getFullYear()+padzero(key.getMonth()+1)+padzero(key.getDate());
				while (typeof holidays[d]!="undefined") {
					key.setDate(key.getDate() + 1);
					d = key.getFullYear()+padzero(key.getMonth()+1)+padzero(key.getDate());
				}
				holidays[d] = val;
			});
		}

		//個別の祝祭日の追加
		if (typeof individual[Y+M]!="undefined") {
			$.each(individual[Y+M], function(key, val) {
				attr = {};
				attr['name'] = val['name'];
				if (typeof val['class']!="undefined") {
					attr['class'] = val['class'];
				} else if (typeof css['holiday']!="undefined") {
					attr['class'] = css['holiday'];
				} else {
					attr['class'] = "";
				}
				holidays[Y+M+val.day] = attr;
			});
		};

		prepared[Y+M] = true;
		return true;
	}

	//月の第ｎ番目のｍ曜日
	var nthday = function(y, m, val) {
		var dayOfWeek = val.split('/');
		var firstDay = new Date(y, m - 1, 1);
		var adjust = dayOfWeek[1] - firstDay.getDay();
		if (adjust < 0)
			adjust += 7;
		//第ｎ番目のｍ曜日
		return 1 + adjust + (dayOfWeek[0] -1 ) * 7;
	}

	//年間の祝祭日データ
	var annual = {
		'01': [{'day':'01','name':'元旦'}, {'day':'2/1','name':'成人の日'}],
		'02': [{'day':'11','name':'建国記念日'}],
		'03': [{'day':'spring equinox','name':'春分の日'}],
		'04': [{'day':'29','name':'昭和の日'}],
		'05': [{'day':'03','name':'憲法記念日'}, {'day':'04','name':'みどりの日'}, {'day':'05','name':'こどもの日'}],
		'07': [{'day':'3/1','name':'海の日'}],
		'08': [{'day':'11>2016','name':'山の日'}], //2016年以降
		'09': [{'day':'3/1','name':'敬老の日'}, {'day':'autumnal equinox','name':'秋分の日'}],
		'10': [{'day':'2/1','name':'体育の日'}],
		'11': [{'day':'03','name':'文化の日'}, {'day':'23','name':'勤労感謝の日'}],
		'12': [{'day':'23<2018','name':'天皇誕生日'}]
	};
	//個別の祝祭日データ
	var individual = {
		'201509': [{'day':'22','name':'国民の休日'}],
		'201905': [{'day':'01','name':'新天皇即位日'}],
		'202609': [{'day':'22','name':'国民の休日'}], //2015年時点の推定
		'203209': [{'day':'21','name':'国民の休日'}], //2015年時点の推定
		'203709': [{'day':'22','name':'国民の休日'}] //2015年時点の推定
	};
	//言語対応
	var words = {
		'substitute': '振替休日'
	};

})(jQuery);
