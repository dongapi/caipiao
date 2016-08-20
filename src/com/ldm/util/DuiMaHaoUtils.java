package com.ldm.util;

import java.util.List;

/**
  * @ClassName: DuiMaHaoUtils
  * @Description: TODO
  * @author zhaowei
  * @date 2016年7月15日 下午1:24:49
 */
public class DuiMaHaoUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static int hundred = 0;
	
	private static int ten = 0 ;
	
	private static int single = 0;
	
	private static String[] allNum = {"0","1","2","3","4","5","6","7","8","9"};
	
	public DuiMaHaoUtils(String lastCode){
		
		hundred = Integer.parseInt(lastCode.substring(0, 1));
		ten = Integer.parseInt(lastCode.substring(1,2));
		single = Integer.parseInt(lastCode.substring(2,3));
	}
	
	public List<String> getDuiMaHao(){
		 String[] result = CommonUtils.getDuiMaHao(hundred,ten,single);
		 String[] minus = CommonUtils.minus(allNum,result);
		 List<String> last =  CommonUtils.association(result , minus);
		 System.out.println("last--" + last.size());
		 List<String> last1 = CommonUtils.removeSomething(hundred,ten,single,last);
		 System.out.println("last1--" + last1.size());
		 for(String str: last1){
			 System.out.println(str);
		 }
		return null;
		
	}
	
}
