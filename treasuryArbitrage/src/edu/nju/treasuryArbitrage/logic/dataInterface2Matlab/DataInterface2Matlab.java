package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import java.util.ArrayList;

import Arbitrage_Main.*;

import com.mathworks.toolbox.javabuilder.*;
//引入java自带Matlab相关包
//导入程序员自己用matlab输出的jar包

public class DataInterface2Matlab {
		
	public DataInterface2Matlab(){
		
	}
	
	
	/*
	 * result为返回对象数组，各元素分别为x,y,k
	 */
	public Object[] Arbitrage_Main(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,double stop_loss,double stop_profit){
		Object[] result = null;
		Arbitrage_Main m = null;
		MWNumericArray f1 = null,f2 = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
		try {
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
			m = new Arbitrage_Main();  //!!!!important
			result =m.Arbitrage_Main(3,f1,f2,stop_loss, stop_profit);
			//optimization_x,optimization_y,optimization_k
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Arbitrage_MAin Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	/*
	 * 
	 * result为返回对象数组，各元素分别为signal,buyprice,saleprice
	 */
	public Object[] Open(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double x,double y,double k){
		Open open_position = null;
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
		try {
			//Open方法调用示例
			open_position = new Open(); //!!!!important
			result =open_position.open(3,f1,f2,newprice1,newprice2,x,y,k);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Open Exception catched!");
		}
		return result;
	}
	
	/*
	 * 输入：%signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
	 * Lf1，Lf2是前一交易日开盘到现在前一秒的价格数组
	 * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
	 * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
	 * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
	 * 输出： result为返回对象数组，各元素分别为signal,buyprice,saleprice
	 */
	public Object[] Close(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double k,double signal,double stop_loss,double stop_profit){
		Close close_position = null;
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
		try{
			close_position = new Close(); //!!!!important
			result =close_position.close(3,f1,f2,
					newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Close Exception catched!");
		}
		return result;
	}

	
	/*
	 * [signal long_name short_name long short current_state current_trade_return units_time_return]
	 * =judge(name1,name2,f1,f2,new_price1,new_price2,lambda,last_time_state,last_trade_return,spoint,market_condition,sigma_method)
	 * %% 判断当前的价格是否偏离，并发出买入卖出信号
	 * %% 输入：
	 * %  name1，合约1的名称，例如'TF1412';name2，合约2的名称
	 * %  f1历史数据序列1（一维数组），f2历史数据序列2（一维数组），均为列向量
	 * %  new_price1为新价格1（double），new_price2新数据2（double），lambda策略参数（2*1向量），
	 * %  last_time_state当前交易状态（取-1,0,1，分别代表负向套利组合、空仓和正向套利组合）
	 * %  last_trade_return指的是当前持仓到目前为止的累计收益点数；
	 * %  spoint为止损点，例如-0.02
	 * %  market_condition则为反映当前市场状况的一系列参数，其构造为[一手交易费,保证金比例,估计的冲击成本,无风险利率]
	 * %  sigma_method为估计未来波动率的具体方法，分为'garch'和其他，前者精度高但速度慢，后者精度低但速度快数倍
	 * %% 输出：
	 * %  signal按照当前的最新价格，是否应当交易。
	 * %  signal=1，正向套利建仓；signal=-1，反向套利建仓；signal=0，无动作；signal=2，止盈平仓；signal=-2,止损平仓。
	 * %  long_name，做多的合约名称；short_name，做空的合约名称；long，做多合约的参考点数；short，做空合约的参考点数。
	 * %  current_state，在按照当前信号交易完成后，当前的持仓是负向套利组合、空仓还是正向套利组合，分别为-1,0和1，对应于last_time_state。
	 * %  current_trade_return当前持仓在本次交易完成后更新的累计收益点数；unit_time_return，当前交易在单位交易时间的收益点数，若无交易则为0。
	 * 	 */
	public Object[] Judge(String name1,String name2,ArrayList<Double> Lf1,ArrayList<Double> Lf2,
			double new_price1,double new_price2,
			ArrayList<Double> Llambda,int last_time_state, double last_trade_return,double spoint,
			ArrayList<Double> Lmarket_condition,String sigma_method){
		Object[] result = null;
		Judge J = null;
		MWNumericArray f1 = null,f2 = null,lambda = null,market_condition = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        int[] dims3 = {2, 1};  
        lambda = MWNumericArray.newInstance(dims3,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        lambda.set(1, Double.valueOf(Llambda.get(0)));
        lambda.set(2, Double.valueOf(Llambda.get(1)));
        int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
			J = new Judge();  //!!!!important
			
			result = J.judge(8,name1,name2,f1,f2,new_price1,new_price2,lambda,
					last_time_state,last_trade_return,spoint,market_condition,sigma_method);
			//[signal long_name short_name long short current_state current_trade_return unit_time_return]
			/*   8个数据
			 * %% 输出：signal按照当前的最新价格，是否应当交易。
			 * %  signal=1，正向套利建仓；signal=-1，反向套利建仓；signal=0，无动作；signal=2，止盈平仓；signal=-2,止损平仓。
			 * %  long_name，做多的合约名称；short_name，做空的合约名称；long，做多合约的参考点数；short，做空合约的参考点数。
			 * %  current_state，在按照当前信号交易完成后，当前的持仓是负向套利组合、空仓还是正向套利组合，分别为-1,0和1，对应于last_time_state。
			 * %  current_trade_return当前持仓在本次交易完成后更新的累计收益点数；unit_time_return，当前交易在单位交易时间的收益点数，若无交易则为0。
			 *  */
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Judge Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	
	/*
	 * [all_return,trade_count,opt_lambda,prob_of_win,trade,trade_stddev,trade_maxloss sharpe_ratio]
	 * =back_test(f1,f2,spoint,opt_option,market_condition,sigma_method)
	 * %% 回测函数，根据现有的数据回测最优的策略参数和相关收益情况
	 * %% 输入：f1历史数据序列1（列向量），f2历史数据序列2（列向量），spoint止损点
	 * %  opt_choice：优化选项，1为总收益最大化，2为风险调整后的收益最大化，3为胜率最大，4为胜率调整后的收益最大
	 * %  market_condition为反映当前市场状况的一系列参数，其构造为[一手交易费,保证金比例,估计的冲击成本,无风险利率]
	 * %  sigma_method为估计波动率的方法，分为garch和其他，取值为‘Garch’时使用GARCH模型（不区分大小写），其他情况下则使用历史波动率
	 * %% 输出：all_return为回测得到的最大收益，opt_lambda为最优的策略参数
	 * %  prob_of_win为交易盈利的概率，trade为交易明细，结构为[是否持仓 残差序列 价差序列 波动率序列 偏离比率 当前时间收益点数 当前交易收益点数]
	 * %  trade_stddev trade_maxloss sharpe_ratio分别为标准差、最大回撤和策略夏普比率
	 * */
	public Object[] BackTest(ArrayList<Double> Lf1, ArrayList<Double> Lf2,double spoint,
			int opt_option,ArrayList<Double> Lmarket_condition,String sigma_method){
		Object[] result = null;
		BackTest back_test = null;
		MWNumericArray f1 = null,f2 = null,market_condition = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			back_test = new BackTest();  //!!!!important
			result = back_test.back_test(8,f1,f2,spoint,opt_option,market_condition,sigma_method);
			//[all_return,trade_count,opt_lambda,prob_of_win,
			//trade,trade_stddev,trade_maxloss,sharpe_ratio]
			/* %% 输出：all_return为回测得到的最大收益，opt_lambda为最优的策略参数
			 * %  prob_of_win为交易盈利的概率，trade为交易明细，结构为[是否持仓 残差序列 价差序列 波动率序列 偏离比率 当前时间收益点数 当前交易收益点数]
			 * %  trade_stddev trade_maxloss sharpe_ratio分别为标准差、最大回撤和策略夏普比率
			 * */
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("BackTest Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	
	/*
	 * return_ratio=convert2ratio(return_of_trade,market_condition,trade_count)
	 * %% 根据一定的点数，计算收益率
	 * % 输入：收益点数，市场参数
	 * */
	public Object[] Convert2ratio(double return_of_trade,ArrayList<Double> Lmarket_condition,double trade_count){
		Object[] result = null;
		Convert2ratio c2ratio = null;
		MWNumericArray market_condition = null;   
		int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			c2ratio = new Convert2ratio();  //!!!!important
			result = c2ratio.convert2ratio(1, return_of_trade,market_condition,trade_count);
			//return_ratio
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Convert2ratio Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
}
