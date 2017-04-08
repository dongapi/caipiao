/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.ldm.util;

import java.awt.List;
import java.util.ArrayList;

/**
  * @ClassName: SixCodeUtil
  * @Description: 根据六码生成两码组合
  * @author wsdoing
  * @date 2016年9月6日 下午12:06:32
  */
public class SixCodeUtil {

	private  static String[] arr0 = new String[]{"000","555","112","005","055","224","117","155","336","255","355","229","448","554","667","779","012","024","124","017","036","136","029","236","048","129","067","148","167","248","348","267","079","367","178","467","279","567","379","479","579","679"};
	
	private  static String[] arr1 = new String[]{"111","666","011","044","223","116","009","335","228","666","166","266","366","447","466","566","559","778","023","123","016","035","135","028","235","047","128","147","059","347","247","078","159","178","259","278","359","378","459","478","578","678"};
	
	private  static String[] arr2 = new String[]{"222","777","003","022","221","115","008","334","227","446","077","339","177","277","377","477","558","577","677","889","015","034","134","027","234","039","046","127","146","139","246","058","158","239","258","358","089","458","189","289","389","489","589","689","789","346"};
	
	private  static String[] arr3 = new String[]{"333","888","002","114","330","007","331","233","119","445","338","088","188","557","288","388","488","588","669","688","788","226","014","026","045","126","019","145","038","245","057","138","345","157","238","257","069","357","169","457","269","369","469","569"};
	
	private  static String[] arr4 = new String[]{"444","999","001","006","113","044","225","144","118","244","344","337","556","449","099","199","668","299","399","499","599","699","799","899","013","025","125","037","018","056","137","156","237","256","049","068","149","356","168","249","456","268","349","368","468","568"};
	
	private static String[] getSixCode(String code){
		for(String str : arr0){
			if(str.equals(code)){
				return new String[]{"4","5","6","9","0","1"};
			}
		}
		for(String str : arr0){
			if(str.equals(code)){
				return new String[]{"4","5","6","9","0","1"};
			}
		}
		for(String str : arr1){
			if(str.equals(code)){
				return new String[]{"2","3","4","7","8","9"};
			}
		}
		for(String str : arr2){
			if(str.equals(code)){
				return new String[]{"0","1","2","5","6","7"};
			}
		}
		for(String str : arr3){
			if(str.equals(code)){
				return new String[]{"3","4","5","8","9","0"};
			}
		}
		for(String str : arr4){
			if(str.equals(code)){
				return new String[]{"1","2","3","6","7","8"};
			}
		}
		return new String[]{};
	}
	
	/**
	  * @author zhaowei
	  * @Description: 生成两码组合
	  * @param @param code
	  * @param @return  
	  * @return String[]  
	  * @throws
	  * @date 2016年9月8日 下午1:37:35
	 */
	public static String[] getTwoCode(String code){
		String[] sixCode = getSixCode(code);
		ArrayList<String> twoCode = new ArrayList<String>();
		if(sixCode!= null&& sixCode.length>0){
			for(int i=0;i<sixCode.length;i++){
				{
					for(int j=i+1;j<sixCode.length;j++){
						twoCode.add(sixCode[i]+sixCode[j]);
					}
				}
			}
		}
		return twoCode.toArray(new String[15]);
	}
	
	public static void main(String[] args) {
		String[] twoCode = getTwoCode("123");
		System.out.println(twoCode.length);
		for(String str : twoCode){
			System.out.println(str);
		}
	}
 }
