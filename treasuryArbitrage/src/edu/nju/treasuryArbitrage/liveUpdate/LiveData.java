package edu.nju.treasuryArbitrage.liveUpdate;

import java.util.ArrayList;

import vo.Arb_detail;

public class LiveData {
	private LiveData self = new LiveData();
	private ArrayList<Arb_detail> arb_details;
	
	private LiveData(){}
	
	public LiveData getInstance() {
		return self;
	}
	
	public ArrayList<Arb_detail> getArb_details() {
		return arb_details;
	}
	
	public void setArb_details(ArrayList<Arb_detail> arb_details) {
		this.arb_details = arb_details;
	}
}
