package edu.nju.treasuryArbitrage.controller.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.nju.treasuryArbitrage.model.Repository;



public class RepositoryService {
	private NetHelper helper;
	private ArrayList<Repository> repo_list;
	
	public RepositoryService(NetHelper helper){
		this.helper = helper;
	}
	
	public ArrayList<Repository> getRepoList(String username){
		
		repo_list = new ArrayList<Repository>();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		helper.setInitPara("repository", params);
		JSONArray ret = helper.getJSONArrayByGet();
		if (ret == null) {
			return new ArrayList<Repository>();
		}
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Repository rep = new Repository();
			
			rep.setRepo_ID(temp.getInt("id"));
			rep.setTime(temp.getLong("time"));
			rep.setCount(temp.getInt("hand"));
			rep.setGuarantee(temp.getDouble("bond"));
			rep.setToBuy(temp.getString("more_contract"));
			rep.setToSell(temp.getString("blank_contract"));
			rep.settoBuy_price(temp.getDouble("more_price"));
			rep.settoSell_price(temp.getDouble("blank_price"));
			
			repo_list.add(rep);
		}
		
		return repo_list;
	}
	
	public void addRepo(){
		
		Repository rep = new Repository();
		repo_list.add(rep);
	}
	
	public boolean Trade(int Repo_ID){
		int index = -1;
		for(Repository rep:repo_list){
			index++;
			if(rep.getRepo_ID()==(Repo_ID)){
				break;
			}
		}
		repo_list.remove(index);
		
		//������������޸���������
		
		return true;
	}	
}
