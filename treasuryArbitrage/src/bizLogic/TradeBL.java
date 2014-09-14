package bizLogic;

import java.util.HashMap;

import org.json.JSONObject;

public class TradeBL {
	
	public TradeBL(){}
	
	public boolean order(String username,String More_contract,String Blank_contract,double more_price,double blank_price,int hand,int guarantee){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("more_contract", More_contract);
		params.put("mlank_contract", Blank_contract);
		params.put("more_price", more_price+"");
		params.put("blank_price", blank_price+"");
		params.put("hand", hand+"");
		params.put("bond", guarantee+"");
		
		NetHelper helper = new NetHelper("order",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean cancleOrder(String username,int record_ID){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("id", record_ID+"");
		
		NetHelper helper = new NetHelper("cancelOrder",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean trade(String username,int Repo_ID,int profit){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("Repo_ID", Repo_ID+"");
		params.put("profit", profit+"");
		
		NetHelper helper = new NetHelper("trade",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
}