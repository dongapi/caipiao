package com.ldm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.MethodBean;

public class CP_3D_Util {

	private static final Integer[] NUMS_10_All = new Integer[] {0,1,2,3,4,5,6,7,8,9};

	// 预测3D百位数字
	public static Map<String, Object> getBai(List<CaipiaoBean> list) {

		List<Integer[]> history20 = new ArrayList<Integer[]>();

		MethodBean mb = new MethodBean();

		for (CaipiaoBean cb : list) {
			String[] tmp = cb.getOpenCode().split(",");
			Integer[] num = new Integer[3];
			for (int i = 0; i < tmp.length; i++) {
				num[i] = Integer.valueOf(tmp[i]);
			}
			history20.add(num);
		}
		List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
		
		// 1、 位差和 排百位（最近2期，百、十、个位，分别相减，三个差值相加，和的个位数 排除）
		int a = Math.abs( history20.get(0)[0] - history20.get(1)[0]);
		int b = Math.abs( history20.get(0)[1] - history20.get(1)[1]);
		int c = Math.abs( history20.get(0)[2] - history20.get(1)[2]);
		int d = (a+b+c)%10;
		result.remove((Integer)d);
		System.out.println("3D百位方法一排除：" + d);
		
		// 2、上期百位加4 排除
		result.remove((Integer) (history20.get(0)[0] +4)%10);
		System.out.println("3D百位方法二排除：" + (history20.get(0)[0] +4)%10);
		
		// 3、上期 百位十位相减 排除
		result.remove((Integer) Math.abs(history20.get(0)[1] - history20.get(0)[0]));
		System.out.println("3D百位方法三排除：" + (Integer) Math.abs(history20.get(0)[1] - history20.get(0)[0]));
		
		// 4、最近5期首位数字权重值 求和 除以5取余数 排除
		//(权重值 ：号码3、8权重值为1，号码4、9权重值为2，号码0、5权重值为3，号码1、6权重值为4，号码2、7权重值为5) 注：余数为0 算5
		if (history20.size() > 4) {
			int count = 0;
			for (int k = 0; k < 5; k++) {
				switch (history20.get(k)[0]) {
				case 3:
					count += 1;
					break;
				case 8:
					count += 1;
					break;
				case 4:
					count += 2;
					break;
				case 9:
					count += 2;
					break;
				case 0:
					count += 3;
					break;
				case 5:
					count += 3;
					break;
				case 1:
					count += 4;
					break;
				case 6:
					count += 4;
					break;
				case 2:
					count += 5;
					break;
				case 7:
					count += 5;
					break;
				default:
					break;
				}
			}
			Integer mod = count % 5;
			if (mod == 0)
				mod = 5;
			switch (mod) {
			case 1:
				result.remove((Integer)3);
				result.remove((Integer)8);
				System.out.println("3D百位方法四排除：3、8");
				break;
			case 2:
				result.remove((Integer)4);
				result.remove((Integer)9);
				System.out.println("3D百位方法四排除：4、9");
				break;
			case 3:
				result.remove((Integer)0);
				result.remove((Integer)5);
				System.out.println("3D百位方法四排除：0、5");
				break;
			case 4:
				result.remove((Integer)1);
				result.remove((Integer)6);
				System.out.println("3D百位方法四排除：1、6");
				break;
			case 5:
				result.remove((Integer)2);
				result.remove((Integer)7);
				System.out.println("3D百位方法四排除：2、7");
				break;
			default:
				break;
			}
		}
		// 5、上一期首位对码号：0、1；2、3；4、5；6、7；8、9。 
		switch(history20.get(0)[0]){
		case 0:
			result.remove(Integer.valueOf(1));
			System.out.println("3D百位方法五排除：" + 1);
			break;
		case 1:
			result.remove(Integer.valueOf(0));
			System.out.println("3D百位方法五排除：" + 0);
			break;
		case 2:
			result.remove(Integer.valueOf(3));
			System.out.println("3D百位方法五排除：" + 3);
			break;
		case 3:
			result.remove(Integer.valueOf(2));
			System.out.println("3D百位方法五排除：" + 2);
			break;
		case 4:
			result.remove(Integer.valueOf(5));
			System.out.println("3D百位方法五排除：" + 5);
			break;
		case 5:
			result.remove(Integer.valueOf(4));
			System.out.println("3D百位方法五排除：" + 4);
			break;
		case 6:
			result.remove(Integer.valueOf(7));
			System.out.println("3D百位方法五排除：" + 7);
			break;
		case 7:
			result.remove(Integer.valueOf(6));
			System.out.println("3D百位方法五排除：" + 6);
			break;
		case 8:
			result.remove(Integer.valueOf(9));
			System.out.println("3D百位方法五排除：" + 9);
			break;
		case 9:
			result.remove(Integer.valueOf(8));
			System.out.println("3D百位方法五排除：" + 8);
			break;
		default:
			break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", result);
		map.put("bean", mb);
		return map;
	}
	
	
	
//===========================================
	// 预测3D十位数字
		public static Map<String, Object> getShi(List<CaipiaoBean> list) {

			List<Integer[]> history20 = new ArrayList<Integer[]>();

//			MethodBean mb = new MethodBean();

			for (CaipiaoBean cb : list) {
				String[] tmp = cb.getOpenCode().split(",");
				Integer[] num = new Integer[3];
				for (int i = 0; i < tmp.length; i++) {
					num[i] = Integer.valueOf(tmp[i]);
				}
				history20.add(num);
			}
			List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
			// 1、上期 上上期 十位数相减 排除
			if (history20.size() > 1) {
				result.remove((Integer) Math.abs(history20.get(0)[1] - history20.get(1)[1]));
				System.out.println("3D十位方法一排除：" + Math.abs(history20.get(0)[1] - history20.get(1)[1]));
			}
			// 2、上期百位、个位 相减 排除
			result.remove((Integer) Math.abs(history20.get(0)[2] - history20.get(0)[0]));
			System.out.println("3D十位方法二排除：" + Math.abs(history20.get(0)[2] - history20.get(0)[0]));
			
			// 3、上一期十位对码号：0、1；2、3；4、5；6、7；8、9。 
			switch(history20.get(0)[1]){
			case 0:
				result.remove(Integer.valueOf(1));
				System.out.println("3D十位方法三排除：" + 1);
				break;
			case 1:
				result.remove(Integer.valueOf(0));
				System.out.println("3D十位方法三排除：" + 0);
				break;
			case 2:
				result.remove(Integer.valueOf(3));
				System.out.println("3D十位方法三排除：" + 3);
				break;
			case 3:
				result.remove(Integer.valueOf(2));
				System.out.println("3D十位方法三排除：" + 2);
				break;
			case 4:
				result.remove(Integer.valueOf(5));
				System.out.println("3D十位方法三排除：" + 5);
				break;
			case 5:
				result.remove(Integer.valueOf(4));
				System.out.println("3D十位方法三排除：" + 4);
				break;
			case 6:
				result.remove(Integer.valueOf(7));
				System.out.println("3D十位方法三排除：" + 7);
				break;
			case 7:
				result.remove(Integer.valueOf(6));
				System.out.println("3D十位方法三排除：" + 6);
				break;
			case 8:
				result.remove(Integer.valueOf(9));
				System.out.println("3D十位方法三排除：" + 9);
				break;
			case 9:
				result.remove(Integer.valueOf(8));
				System.out.println("3D十位方法三排除：" + 8);
				break;
			default:
				break;
			}
			
			// 4、上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9
			switch(history20.get(0)[1]){
			case 0:
				result.remove(Integer.valueOf(5));
				System.out.println("3D十位方法四排除：" + 5);
				break;
			case 1:
				result.remove(Integer.valueOf(7));
				System.out.println("3D十位方法四排除：" + 7);
				break;
			case 2:
				result.remove(Integer.valueOf(9));
				System.out.println("3D十位方法四排除：" + 9);
				break;
			case 3:
				result.remove(Integer.valueOf(1));
				System.out.println("3D十位方法四排除：" + 1);
				break;
			case 4:
				result.remove(Integer.valueOf(3));
				System.out.println("3D十位方法四排除：" + 3);
				break;
			case 5:
				result.remove(Integer.valueOf(5));
				System.out.println("3D十位方法四排除：" + 5);
				break;
			case 6:
				result.remove(Integer.valueOf(7));
				System.out.println("3D十位方法四排除：" + 7);
				break;
			case 7:
				result.remove(Integer.valueOf(9));
				System.out.println("3D十位方法四排除：" + 9);
				break;
			case 8:
				result.remove(Integer.valueOf(1));
				System.out.println("3D十位方法四排除：" + 1);
				break;
			case 9:
				result.remove(Integer.valueOf(3));
				System.out.println("3D十位方法四排除：" + 3);
				break;
			default:
				break;
			}
			
			// 5、右一：上一期十位右边的号码（1右边为2，2右边为3，以此类推，9右边是0，循环的）称为右一。
			//向前查找历史数据十位号码为右一的那一期，计算上一期与该期中间间隔的期数（上一期和该期都不算），排除个位（如间隔5期，就排除5;间隔13期，就排除3;间隔0、10、20期，排除10）
			int rightNum = (history20.get(0)[1] + 1) % 10;
			int num4rem = 0;
			String flag = null ;
			for (int j = 1; j < history20.size(); j++) {
				if (history20.get(j)[1] != rightNum) {
					num4rem++;
					continue;
				}else{
					flag = "yes";
					break;
				}
			}
			if("yes".equals(flag)){
				result.remove(Integer.valueOf(num4rem%10));
				System.out.println("3D十位方法五排除：" + num4rem%10);
			}else{
				System.out.println("3D十位方法五没有排除号码,历史数据中没有右一号码");
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", result);
//			map.put("bean", mb);
			return map;
		}
		
		
		
//===========================================
		// 预测3D个位数字
		public static Map<String, Object> getGe(List<CaipiaoBean> list) {

			List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
			
			String openCodeStr = list.get(0).getOpenCode().replaceAll(",", "");
			
			EliminationUtils eu = new EliminationUtils(openCodeStr);
			
			result.remove((Integer)eu.one());
			result.remove((Integer)eu.two());
			result.remove((Integer)eu.three());
			result.remove((Integer)eu.four());
			result.remove((Integer)eu.five());
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", result);
//			map.put("bean", mb);
			return map;
		}
}
