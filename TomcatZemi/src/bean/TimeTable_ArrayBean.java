package bean;

import java.util.ArrayList;

public class TimeTable_ArrayBean {

	private ArrayList<TimeBean> timeArray;

	public TimeTable_ArrayBean(){
		timeArray = new ArrayList<TimeBean>();
	}

	public ArrayList<TimeBean> getTimeArray() {
		return timeArray;
	}

	public void setTimeArray(ArrayList<TimeBean> timeArray) {
		this.timeArray = timeArray;
	}

	public void addTimeArray(TimeBean tbean){
		timeArray.add(tbean);
	}

	public int getArrayListSize(){
		return timeArray.size();
	}

}
