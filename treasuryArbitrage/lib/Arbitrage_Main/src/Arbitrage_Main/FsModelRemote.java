/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Wed Oct 29 08:49:43 2014
 * Arguments: "-B" "macro_default" "-W" "java:Arbitrage_Main,FsModel" "-T" "link:lib" 
 * "-d" "E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" 
 * "class{FsModel:E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main.m,E:\\nju\\2014花旗杯\\fxy\\close.m,E:\\nju\\2014花旗杯\\fxy\\open.m}" 
 * "-a" "E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\arbitrage_return.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\bias_ratio.m" "-a" "E:\\nju\\2014花旗杯\\fxy\\close.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\max_occupying_fund.m" "-a" 
 * "E:\\nju\\2014花旗杯\\fxy\\open.m" 
 */

package Arbitrage_Main;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>FsModelRemote</code> class provides a Java RMI-compliant interface to the 
 * M-functions from the files:
 * <pre>
 *  E:\\nju\\2014花旗杯\\fxy\\Arbitrage_Main.m
 *  E:\\nju\\2014花旗杯\\fxy\\close.m
 *  E:\\nju\\2014花旗杯\\fxy\\open.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>FsModelRemote</code> 
 * instance when it is no longer needed to ensure that native resources allocated by this 
 * class are properly freed, and the server-side proxy is unexported.  (Failure to call 
 * dispose may result in server-side threads not being properly shut down, which often 
 * appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of Arbitrage_Main.FsModel.
 */
public interface FsModelRemote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>Arbitrage_Main</code> 
     * M-function with 4 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * No usage documentation is available for this function.  (To fix this, the function 
     * author should insert a help comment at the beginning of their M code.  See the 
     * MATLAB documentation for more details.)
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the M function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.jmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] Arbitrage_Main(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>close</code> M-function with 
     * 10 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
     * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
     * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
     * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the M function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.jmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] close(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>open</code> M-function with 
     * 7 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * M-documentation as provided by the author of the M function:
     * <pre>
     * %l_signal当前交易状态（状态，取-2，-1,0,1,2）
     * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
     * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
     * %损平仓。
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the M function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.jmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] open(int nargout, Object... rhs) throws RemoteException;
  
    /** Frees native resources associated with the remote server object */
    void dispose() throws RemoteException;
}
