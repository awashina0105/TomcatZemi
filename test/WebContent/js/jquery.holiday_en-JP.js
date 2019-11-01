/* 
 * holiday data of en-JP for jquery.holiday.js
 * 
 * Function:
 *		Setting the holiday tooltip data for a region and a language.
 * Install:
 *		<script src="jquery.holiday_en-JP.js"></script>
 * Notice:
 *		this data affects all the holiday tooltips on a html page.
 */
$(function(){
	$.fn.holiday('setRegionData', {
		//Annual Holidays { month : [{ day: day, name: holiday name }] }
		//	day: (day of the month | nth / day of week | day > valid in the year or later)
		'annual':{
			'01': [{'day':"01",'name':"New Year's Day"}, {'day':"2/1",'name':"Coming-of-Age Day"}], // 2nd/Monday
			'02': [{'day':"11",'name':"National Foundation Day"}],
			'03': [{'day':"spring equinox",'name':"Spring Equinox Day"}],
			'04': [{'day':"29",'name':"Showa Day"}],
			'05': [{'day':"03",'name':"Constitution Day"}, {'day':"04",'name':"Greenery Day"}, {'day':"05",'name':"Children's Day"}],
			'07': [{'day':"3/1",'name':"Marine Day"}], // 3rd/Monday
			'08': [{'day':"11>2016",'name':"Mountain Day"}], // in 2016 or later
			'09': [{'day':"3/1",'name':"Respect-for-the-Aged Day"}, {'day':"autumnal equinox",'name':"Autumnal Equinox Day"}],
			'10': [{'day':"2/1",'name':"Sports Day"}], // 2nd/Monday
			'11': [{'day':"03",'name':"Culture Day"}, {'day':"23",'name':"Labor Thanksgiving Day"}],
			'12': [{'day':"23",'name':"Emperor's Birthday"}]
		},
		//Individual Holidays { year month : [ { day: day of the month, name: holiday name } ] }
		'individual':{
			'201509': [{'day':"22",'name':"National Holiday"}],
			'202609': [{'day':"22",'name':"National Holiday"}],
			'203209': [{'day':"21",'name':"National Holiday"}],
			'203709': [{'day':"22",'name':"National Holiday"}]
		},
		//Words for a language
		'words': {
			'substitute':"substitute" // for a substitute holiday
		}
	});
});