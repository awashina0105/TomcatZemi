package bean;

import java.util.ArrayList;

public class LatestNewsGetArrayBean {

	private ArrayList<LatestNewsGetBean> latestNewsGetArray;

	public LatestNewsGetArrayBean(){
		latestNewsGetArray = new ArrayList<LatestNewsGetBean>();
	}

	public ArrayList<LatestNewsGetBean> getLatestNewsGetArray() {
		return latestNewsGetArray;
	}

	public void setLatestNewsGetArray(ArrayList<LatestNewsGetBean> latestNewsGetArray) {
		this.latestNewsGetArray = latestNewsGetArray;
	}

	public void addLatestNewsGetArray(LatestNewsGetBean lngbean){
		latestNewsGetArray.add(lngbean);
	}

	public int getArrayListSize(){
		return latestNewsGetArray.size();
	}

}
