package model.vo;

public class Finance {
	String time;	//时间
	double total;	//总资金
	double guarantee;	//已投入保证金
	double idle; 	//空闲资金
	
	public Finance(){
		init();
	}
	
	public void init(){}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}
}
