package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_brief;
import edu.nju.treasuryArbitrage.model.Arb_detail;


public class LiveData {
	private static LiveData self = new LiveData();
	private ArrayList<Arb_detail> arb_details;
	private ArrayList<ArbGroup> arbGroups = new ArrayList<>();
	
	private ArrayList<Arb_brief> briefsTF1412;
	private ArrayList<Arb_brief> briefsTF1503;
	private ArrayList<Arb_brief> briefsTF1506;
	
	private LiveData(){}
	
	public static LiveData getInstance() {
		return self;
	}
	
	public boolean isReady() {
		if (briefsTF1412 != null &&
				briefsTF1503 != null &&
				briefsTF1506 != null) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Arb_detail> getArb_details() {
		return arb_details;
	}
	
	public double getPresentPrice(String id) {
		for (Arb_detail arb_detail : arb_details) {
			if (arb_detail.getSymbol().equals(id)) {
				return arb_detail.getPresentPrice();
			}
		}
		return -1;
	}
	
	public void setArb_details(ArrayList<Arb_detail> arb_details) {
		this.arb_details = arb_details;
	}

	public ArrayList<ArbGroup> getArbGroups() {
		return arbGroups;
	}

	public void setArbGroups(ArrayList<ArbGroup> arbGroups) {
		this.arbGroups = arbGroups;
	}

	public ArrayList<Arb_brief> getBriefsTF1412() {
//		while (briefsTF1412 == null) {
//			System.out.println("waitting for TF1412...");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return briefsTF1412;
	}

	public void setBriefsTF1412(ArrayList<Arb_brief> briefsTF1412) {
		this.briefsTF1412 = briefsTF1412;
	}

	public ArrayList<Arb_brief> getBriefsTF1503() {
//		while (briefsTF1503 == null) {
//			System.out.println("waitting for TF1503...");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return briefsTF1503;
	}

	public void setBriefsTF1503(ArrayList<Arb_brief> briefsTF1503) {
		this.briefsTF1503 = briefsTF1503;
	}

	public ArrayList<Arb_brief> getBriefsTF1506() {
//		while (briefsTF1506 == null) {
//			System.out.println("waitting for TF1506...");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return briefsTF1506;
	}

	public void setBriefsTF1506(ArrayList<Arb_brief> briefsTF1506) {
		this.briefsTF1506 = briefsTF1506;
	}
	
	
}
