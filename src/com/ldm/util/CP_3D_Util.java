package com.ldm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.MethodBean_3D;

public class CP_3D_Util {

	private static final Integer[] NUMS_10_All = new Integer[] {0,1,2,3,4,5,6,7,8,9};
	private static final List<String> type1 = new ArrayList<>(Arrays.asList(
			"000","555","112","005","055","224","117","155","336","255","355","229",
			"448","554","667","779","012","024","124","017","036","136","029","236",
			"048","129","067","148","167","248","348","267","079","367","179","467",
			"279","567","379","479","579","679"
			));
	private static final List<String> type2 = new ArrayList<>(Arrays.asList(
			"222","777","003","022","221","115","008","334","227","446","077","339",
			"177","277","377","477","558","577","677","889","015","034","134","027",
			"234","039","046","127","146","139","246","058","158","239","258","358",
			"089","458","189","289","389","489","589","689","789","346"
			));
	private static final List<String> type3 = new ArrayList<>(Arrays.asList(
			"444","999","001","006","113","044","225","144","118","244","344","337",
			"556","449","099","199","668","299","399","499","599","699","799","899",
			"013","025","125","037","018","056","137","156","237","256","049","068",
			"149","356","168","249","456","268","349","368","468","568"
			));
	private static final List<String> type4 = new ArrayList<>(Arrays.asList(
			"111","666","011","004","223","116","009","335","228","066","166","266",
			"366","447","466","566","559","778","023","123","016","035","135","028",
			"235","047","128","147","059","347","247","078","159","178","259","278",
			"359","378","459","478","578","678"
			));
	private static final List<String> type5 = new ArrayList<>(Arrays.asList(
			"333","888","002","114","330","007","331","233","119","445","338","088",
			"188","557","288","388","488","588","669","688","788","226","014","026",
			"045","126","019","145","038","245","057","138","345","157","238","257",
			"069","357","169","457","269","369","469","569"
			));
	
	// 预测3D百位数字
	public static Map<String, Object> getBai(List<CaipiaoBean> list,int current) {

		List<Integer[]> history20 = new ArrayList<Integer[]>();

		MethodBean_3D mb = new MethodBean_3D();
		String msg = "";

//		for (CaipiaoBean cb : list) {
//			String[] tmp = cb.getOpenCode().split(",");
//			Integer[] num = new Integer[3];
//			for (int i = 0; i < tmp.length; i++) {
//				num[i] = Integer.valueOf(tmp[i]);
//			}
//			history20.add(num);
//		}
		for (int a=current;a<list.size();a++) {
			String[] tmp = list.get(a).getOpenCode().split(",");
			Integer[] num = new Integer[3];
			for (int i = 0; i < tmp.length; i++) {
				num[i] = Integer.valueOf(tmp[i]);
			}
			history20.add(num);
		}
		List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
		System.out.println("-------数组大小："+result.size());
		System.out.println("-------数组大小："+result.remove((Integer)(10%10)));
		// 1、 位差和 排百位（最近2期，百、十、个位，分别相减，三个差值相加，和的个位数 排除）
		if(history20.size()>1){
			int a = Math.abs( history20.get(0)[0] - history20.get(1)[0]);
			int b = Math.abs( history20.get(0)[1] - history20.get(1)[1]);
			int c = Math.abs( history20.get(0)[2] - history20.get(1)[2]);
			int d = (a+b+c)%10;
			result.remove((Integer)d);
			System.out.println("3D百位方法一排除：" + d);
			mb.setMeth1(""+d);
			msg+="方法一：位差和 排百位。排除："+d+"。&#10;";
		}
		// 2、上期百位加4 排除
		if(history20.size()>0){
			result.remove((Integer)((history20.get(0)[0] +4)%10));
			System.out.println("3D百位方法二排除：" + (history20.get(0)[0] +4)%10);
			mb.setMeth2((history20.get(0)[0] +4)%10+"");
			msg+="方法二：上期百位加4。排除："+((history20.get(0)[0] +4)%10)+"。&#10;";
		}
		// 3、上期 百位十位相减 排除
		if(history20.size()>0){
			result.remove((Integer) Math.abs(history20.get(0)[1] - history20.get(0)[0]));
			System.out.println("3D百位方法三排除：" + (Integer) Math.abs(history20.get(0)[1] - history20.get(0)[0]));
			mb.setMeth3(Math.abs(history20.get(0)[1] - history20.get(0)[0])+"");
			msg+="方法三：上期 百位十位相减。排除："+Math.abs(history20.get(0)[1] - history20.get(0)[0])+"。&#10;";
		}
		// 4、最近5期首位数字权重值 求和 除以5取余数 排除
		//(权重值 ：号码3、8权重值为1，号码4、9权重值为2，号码0、5权重值为3，号码1、6权重值为4，号码2、7权重值为5) 注：余数为0 算5
		if(history20.size()>4){
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
					mb.setMeth4("3,8");
					msg+="方法四：最近5期百位平均取余。排除：3,8。&#10;";
					break;
				case 2:
					result.remove((Integer)4);
					result.remove((Integer)9);
					System.out.println("3D百位方法四排除：4、9");
					mb.setMeth4("4,9");
					msg+="方法四：最近5期百位平均取余。排除：3,9。&#10;";
					break;
				case 3:
					result.remove((Integer)0);
					result.remove((Integer)5);
					System.out.println("3D百位方法四排除：0、5");
					mb.setMeth4("0,5");
					msg+="方法四：最近5期百位平均取余。排除：0,5。&#10;";
					break;
				case 4:
					result.remove((Integer)1);
					result.remove((Integer)6);
					System.out.println("3D百位方法四排除：1、6");
					mb.setMeth4("1,6");
					msg+="方法四：最近5期百位平均取余。排除：1,6。&#10;";
					break;
				case 5:
					result.remove((Integer)2);
					result.remove((Integer)7);
					System.out.println("3D百位方法四排除：2、7");
					mb.setMeth4("2,7");
					msg+="方法四：最近5期百位平均取余。排除：2,7。&#10;";
					break;
				default:
					break;
				}
			}
		}
		// 5、上一期首位对码号：0、1；2、3；4、5；6、7；8、9。 
		if(history20.size()>0){
			switch(history20.get(0)[0]){
			case 0:
				result.remove(Integer.valueOf(1));
				System.out.println("3D百位方法五排除：" + 1);
				mb.setMeth5("1");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：1。&#10;";
				break;
			case 1:
				result.remove(Integer.valueOf(0));
				System.out.println("3D百位方法五排除：" + 0);
				mb.setMeth5("0");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：0。&#10;";
				break;
			case 2:
				result.remove(Integer.valueOf(3));
				System.out.println("3D百位方法五排除：" + 3);
				mb.setMeth5("3");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：3。&#10;";
				break;
			case 3:
				result.remove(Integer.valueOf(2));
				System.out.println("3D百位方法五排除：" + 2);
				mb.setMeth5("2");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：2。&#10;";
				break;
			case 4:
				result.remove(Integer.valueOf(5));
				System.out.println("3D百位方法五排除：" + 5);
				mb.setMeth5("5");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：5。&#10;";
				break;
			case 5:
				result.remove(Integer.valueOf(4));
				System.out.println("3D百位方法五排除：" + 4);
				mb.setMeth5("4");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：4。&#10;";
				break;
			case 6:
				result.remove(Integer.valueOf(7));
				System.out.println("3D百位方法五排除：" + 7);
				mb.setMeth5("7");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：7。&#10;";
				break;
			case 7:
				result.remove(Integer.valueOf(6));
				System.out.println("3D百位方法五排除：" + 6);
				mb.setMeth5("6");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：6。&#10;";
				break;
			case 8:
				result.remove(Integer.valueOf(9));
				System.out.println("3D百位方法五排除：" + 9);
				mb.setMeth5("9");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：9。&#10;";
				break;
			case 9:
				result.remove(Integer.valueOf(8));
				System.out.println("3D百位方法五排除：" + 8);
				mb.setMeth5("8");
				msg+="方法五：对码号0、1；2、3；4、5；6、7；8、9。排除：8。&#10;";
				break;
			default:
				break;
			}
		}
//		mb.setMsg(msg);
		Map<String, Object> map = new HashMap<>();
		map.put("list", result);
		map.put("msg", msg);
		return map;
	}
	
	
	
//===========================================
	// 预测3D十位数字
		public static Map<String, Object> getShi(List<CaipiaoBean> list,int current) {

			List<Integer[]> history20 = new ArrayList<Integer[]>();

			MethodBean_3D mb = new MethodBean_3D();
			String msg = "";

			for (int a=current;a<list.size();a++) {
				String[] tmp = list.get(current).getOpenCode().split(",");
				Integer[] num = new Integer[3];
				for (int i = 0; i < tmp.length; i++) {
					num[i] = Integer.valueOf(tmp[i]);
				}
				history20.add(num);
			}
//			for (CaipiaoBean cb : list) {
//				String[] tmp = cb.getOpenCode().split(",");
//				Integer[] num = new Integer[3];
//				for (int i = 0; i < tmp.length; i++) {
//					num[i] = Integer.valueOf(tmp[i]);
//				}
//				history20.add(num);
//			}
			List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
			// 1、上期 上上期 十位数相减 排除
			if (history20.size() > 1) {
				result.remove((Integer) Math.abs(history20.get(0)[1] - history20.get(1)[1]));
				System.out.println("3D十位方法一排除：" + Math.abs(history20.get(0)[1] - history20.get(1)[1]));
				mb.setMeth1(Math.abs(history20.get(0)[1] - history20.get(1)[1])+"");
				msg+="方法一：上期 上上期 十位数相减。排除："+Math.abs(history20.get(0)[1] - history20.get(1)[1])+"。&#10;";
			}
			// 2、上期百位、个位 相减 排除
			if (history20.size() > 0) {
				result.remove((Integer) Math.abs(history20.get(0)[2] - history20.get(0)[0]));
				System.out.println("3D十位方法二排除：" + Math.abs(history20.get(0)[2] - history20.get(0)[0]));
				mb.setMeth2(Math.abs(history20.get(0)[2] - history20.get(0)[0])+"");
				msg+="方法二：上期百位、个位 相减。排除："+Math.abs(history20.get(0)[2] - history20.get(0)[0])+"。&#10;";
			}
			// 3、上一期十位对码号：0、1；2、3；4、5；6、7；8、9。 
			if (history20.size() > 0) {
				switch(history20.get(0)[1]){
				case 0:
					result.remove(Integer.valueOf(1));
					System.out.println("3D十位方法三排除：" + 1);
					mb.setMeth3("1");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：1。&#10;";
					break;
				case 1:
					result.remove(Integer.valueOf(0));
					System.out.println("3D十位方法三排除：" + 0);
					mb.setMeth3("0");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：0。&#10;";
					break;
				case 2:
					result.remove(Integer.valueOf(3));
					System.out.println("3D十位方法三排除：" + 3);
					mb.setMeth3("3");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：3。&#10;";
					break;
				case 3:
					result.remove(Integer.valueOf(2));
					System.out.println("3D十位方法三排除：" + 2);
					mb.setMeth3("2");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：2。&#10;";
					break;
				case 4:
					result.remove(Integer.valueOf(5));
					System.out.println("3D十位方法三排除：" + 5);
					mb.setMeth3("5");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：5。&#10;";
					break;
				case 5:
					result.remove(Integer.valueOf(4));
					System.out.println("3D十位方法三排除：" + 4);
					mb.setMeth3("4");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：4。&#10;";
					break;
				case 6:
					result.remove(Integer.valueOf(7));
					System.out.println("3D十位方法三排除：" + 7);
					mb.setMeth3("7");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：7。&#10;";
					break;
				case 7:
					result.remove(Integer.valueOf(6));
					System.out.println("3D十位方法三排除：" + 6);
					mb.setMeth3("6");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：6。&#10;";
					break;
				case 8:
					result.remove(Integer.valueOf(9));
					System.out.println("3D十位方法三排除：" + 9);
					mb.setMeth3("9");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：9。&#10;";
					break;
				case 9:
					result.remove(Integer.valueOf(8));
					System.out.println("3D十位方法三排除：" + 8);
					mb.setMeth3("8");
					msg+="方法三：对码号0、1；2、3；4、5；6、7；8、9。排除：8。&#10;";
					break;
				default:
					break;
				}
			}
			
			// 4、上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9
			if (history20.size() > 0) {
				switch(history20.get(0)[1]){
				case 0:
					result.remove(Integer.valueOf(5));
					System.out.println("3D十位方法四排除：" + 5);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：5。&#10;";
					break;
				case 1:
					result.remove(Integer.valueOf(7));
					System.out.println("3D十位方法四排除：" + 7);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：7。&#10;";
					break;
				case 2:
					result.remove(Integer.valueOf(9));
					System.out.println("3D十位方法四排除：" + 9);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：9。&#10;";
					break;
				case 3:
					result.remove(Integer.valueOf(1));
					System.out.println("3D十位方法四排除：" + 1);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：1。&#10;";
					break;
				case 4:
					result.remove(Integer.valueOf(3));
					System.out.println("3D十位方法四排除：" + 3);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：3。&#10;";
					break;
				case 5:
					result.remove(Integer.valueOf(5));
					System.out.println("3D十位方法四排除：" + 5);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：5。&#10;";
					break;
				case 6:
					result.remove(Integer.valueOf(7));
					System.out.println("3D十位方法四排除：" + 7);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：7。&#10;";
					break;
				case 7:
					result.remove(Integer.valueOf(9));
					System.out.println("3D十位方法四排除：" + 9);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：9。&#10;";
					break;
				case 8:
					result.remove(Integer.valueOf(1));
					System.out.println("3D十位方法四排除：" + 1);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：1。&#10;";
					break;
				case 9:
					result.remove(Integer.valueOf(3));
					System.out.println("3D十位方法四排除：" + 3);
					mb.setMeth4("");
					msg+="方法四：上期十位：3或8 排除1；4或9 排除3；5或0 排除5；1或6 排除7；2或7 排除9。排除：3。&#10;";
					break;
				default:
					break;
				}
			}
			
			// 5、右一：上一期十位右边的号码（1右边为2，2右边为3，以此类推，9右边是0，循环的）称为右一。
			//向前查找历史数据十位号码为右一的那一期，计算上一期与该期中间间隔的期数（上一期和该期都不算），排除个位（如间隔5期，就排除5;间隔13期，就排除3;间隔0、10、20期，排除10）
			if (history20.size() > 0) {
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
					mb.setMeth5(num4rem%10+"");
					msg+="方法五：右一。排除："+(num4rem%10)+"。&#10;";
				}else{
					System.out.println("3D十位方法五没有排除号码,历史数据中没有右一号码");
					mb.setMeth5("");
					msg+="方法五：右一。排除：没有排除号码,历史数据中没有右一号码。&#10;";
				}
			}
			
//			mb.setMsg(msg);
			Map<String, Object> map = new HashMap<>();
			map.put("list", result);
			map.put("msg", msg);
			return map;
		}
		
		
		
//===========================================
		// 预测3D个位数字
		public static Map<String, Object> getGe(List<CaipiaoBean> list,int current) {

			List<Integer> result = new ArrayList<>(Arrays.asList(NUMS_10_All));
			
			String openCodeStr = list.get(current).getOpenCode().replace(",", "");
			
			EliminationUtils eu = new EliminationUtils(openCodeStr);
			
			MethodBean_3D mb = new MethodBean_3D();
			String msg = "";
			
			result.remove((Integer)eu.one());
			msg+="方法一。排除："+eu.one()+"。&#10;";
			result.remove((Integer)eu.two());
			msg+="方法二。排除："+eu.two()+"。&#10;";
			result.remove((Integer)eu.three());
			msg+="方法三。排除："+eu.three()+"。&#10;";
			result.remove((Integer)eu.four());
			msg+="方法四。排除："+eu.four()+"。&#10;";
			result.remove((Integer)eu.five());
			msg+="方法五。排除："+eu.five()+"。&#10;";
			
			mb.setMsg(msg);
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", result);
			map.put("msg", msg);
			return map;
		}
		//大底: 047 158 269 370 481 592 603 714 825 936 047
		public static Map<String, String> daDi(List<CaipiaoBean> list) {
			
			List<String> daDi = new ArrayList<>();
			daDi.add("047");
			daDi.add("158");
			daDi.add("269");
			daDi.add("370");
			daDi.add("481");
			daDi.add("592");
			daDi.add("603");
			daDi.add("714");
			daDi.add("825");
			daDi.add("936");
			daDi.add("047");
			
			String[] openCodes = list.get(0).getOpenCode().split(",");
			String baiWei = null;
			String shiWei = null;
			String geWei = null;
			for(int i=0;i<daDi.size();i++){
				if(daDi.get(i).startsWith(openCodes[0])){
					baiWei=daDi.get(i)+daDi.get(i+1);
					break;
				}
			}
			for(int i=0;i<daDi.size();i++){
				if(daDi.get(i).startsWith(openCodes[1])){
					shiWei=daDi.get(i)+daDi.get(i+1);
					break;
				}
			}
			for(int i=0;i<daDi.size();i++){
				if(daDi.get(i).startsWith(openCodes[2])){
					geWei=daDi.get(i)+daDi.get(i+1);
					break;
				}
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("baiWei", baiWei);
			map.put("shiWei", shiWei);
			map.put("geWei", geWei);
			
			return map;
		}
		
		//3D单选推荐号码
		@SuppressWarnings("unchecked")
		public static List<CaipiaoBean> get3Ddx(List<CaipiaoBean> list) {
			for(int i=0;i<list.size();i++){
				Map<String, Object> baiResult = CP_3D_Util.getBai(list,i) ;
				Map<String, Object> shiResult = CP_3D_Util.getShi(list,i) ;
				Map<String, Object> geResult = CP_3D_Util.getGe(list,i) ;
				list.get(i).setBaiResult(baiResult);
				list.get(i).setShiResult(shiResult);
				list.get(i).setGeResult(geResult);
				
				List<Integer> baiWei = (List<Integer>) baiResult.get("list");
				List<Integer> shiWei = (List<Integer>) shiResult.get("list");
				List<Integer> geWei = (List<Integer>) geResult.get("list");
				//组合数
				int count = baiWei.size()*shiWei.size()*geWei.size();
				//所有组合
				List<String> tjhmList = new ArrayList<>();
				for(Integer b : baiWei){
					for(Integer j :shiWei){
						for(Integer k : geWei){
							tjhmList.add(""+b+j+k);
						}
					}
				}
				
				//詹威方法 开始
				Map<String, Object> zw = zwff(list,i,tjhmList);
				list.get(i).setZwhm(zw.get("zwhm").toString().replace(" ", ""));
//				list.get(i).setHbhm(zw.get("hbhm").toString());//放在最下面了
				String hbhm = zw.get("hbhm").toString().replace(" ", "");
				list.get(i).setHbCount(zw.get("hbCount").toString());
				//詹威方法 结束
				
				//推荐号码正确 单选变红 组选变蓝
				String tjhm = tjhmList.toString().replace(" ", "");
				if(i!=0){
					String[] openCodes1 = list.get(i-1).getOpenCode().split(",");
					if(tjhm.contains(openCodes1[0]+openCodes1[1]+openCodes1[2])){
						tjhm = tjhm.replace(openCodes1[0]+openCodes1[1]+openCodes1[2], "<font color='red' style='font-weight:bold;'>"+openCodes1[0]+openCodes1[1]+openCodes1[2]+"</font>");
					}
					if(hbhm.contains(openCodes1[0]+openCodes1[1]+openCodes1[2])){
						hbhm = hbhm.replace(openCodes1[0]+openCodes1[1]+openCodes1[2], "<font color='red' style='font-weight:bold;'>"+openCodes1[0]+openCodes1[1]+openCodes1[2]+"</font>");
					}
					if(hbhm.contains(openCodes1[0]+openCodes1[2]+openCodes1[1])){
						hbhm = hbhm.replace(openCodes1[0]+openCodes1[2]+openCodes1[1], "<font color='blue' style='font-weight:bold;'>"+openCodes1[0]+openCodes1[2]+openCodes1[1]+"</font>");
					}
					if(hbhm.contains(openCodes1[1]+openCodes1[0]+openCodes1[2])){
						hbhm = hbhm.replace(openCodes1[1]+openCodes1[0]+openCodes1[2], "<font color='blue' style='font-weight:bold;'>"+openCodes1[1]+openCodes1[0]+openCodes1[2]+"</font>");
					}
					if(hbhm.contains(openCodes1[1]+openCodes1[2]+openCodes1[0])){
						hbhm = hbhm.replace(openCodes1[1]+openCodes1[2]+openCodes1[0], "<font color='blue' style='font-weight:bold;'>"+openCodes1[1]+openCodes1[2]+openCodes1[0]+"</font>");
					}
					if(hbhm.contains(openCodes1[2]+openCodes1[1]+openCodes1[0])){
						hbhm = hbhm.replace(openCodes1[2]+openCodes1[1]+openCodes1[0], "<font color='blue' style='font-weight:bold;'>"+openCodes1[2]+openCodes1[1]+openCodes1[0]+"</font>");
					}
					if(hbhm.contains(openCodes1[2]+openCodes1[0]+openCodes1[1])){
						hbhm = hbhm.replace(openCodes1[2]+openCodes1[0]+openCodes1[1], "<font color='blue' style='font-weight:bold;'>"+openCodes1[2]+openCodes1[0]+openCodes1[1]+"</font>");
					}
				}
				list.get(i).setDxtjhm(tjhm);
				list.get(i).setDxCount(count);
				list.get(i).setHbhm(hbhm);
			}
			return list;
		}
		
		
		//3D组选推荐号码
		public static List<CaipiaoBean> get3Dzx(List<CaipiaoBean> list) {
			for(int i=0;i<list.size();i++){
				String[] openCodes = list.get(i).getOpenCode().split(",");
				String lm = matchCode(openCodes);
				String pclm =pclm(list,i) ;
				list.get(i).setPclm(pclm);
				
				Integer[] temp = {Integer.valueOf(openCodes[0]), Integer.valueOf(openCodes[1]), Integer.valueOf(openCodes[2])};
				Arrays.sort(temp);
				int bqkd = temp[2] - temp[0] ;//本期跨度
				String dmhpch = dmhpch(openCodes[0]+openCodes[1]+openCodes[2]);//对码号排除号
				List<String> tjhmList = tjhm(lm,pclm,bqkd,dmhpch);
				list.get(i).setTjhmCount(tjhmList.size());
				String tjhm = tjhmList.toString();
				
				//两码序列变红
				if(i!=0){
					String[] openCodes1 = list.get(i-1).getOpenCode().split(",");
					for(String t:openCodes1){
						if(lm.contains(t) && !lm.contains("<font color='red'>"+t+"</font>")){
							lm = lm.replace(t, "<font color='red'>"+t+"</font>");
						}
					}
				}
				list.get(i).setLiangMa(lm);
				
				//推荐号码变红
				if(i!=0){
					String[] openCodes1 = list.get(i-1).getOpenCode().split(",");
					if(tjhm.contains(openCodes1[0]+openCodes1[1]+openCodes1[2])){
						tjhm = tjhm.replace(openCodes1[0]+openCodes1[1]+openCodes1[2], "<font color='red'>"+openCodes1[0]+openCodes1[1]+openCodes1[2]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[0]+openCodes1[1]+openCodes1[2]+"</font>-" +tjhm;
					}else if(tjhm.contains(openCodes1[0]+openCodes1[2]+openCodes1[1])){
						tjhm = tjhm.replace(openCodes1[0]+openCodes1[2]+openCodes1[1], "<font color='red'>"+openCodes1[0]+openCodes1[2]+openCodes1[1]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[0]+openCodes1[2]+openCodes1[1]+"</font>-" +tjhm;
					}else if(tjhm.contains(openCodes1[1]+openCodes1[0]+openCodes1[2])){
						tjhm = tjhm.replace(openCodes1[1]+openCodes1[0]+openCodes1[2], "<font color='red'>"+openCodes1[1]+openCodes1[0]+openCodes1[2]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[1]+openCodes1[0]+openCodes1[2]+"</font>-" +tjhm;
					}else if(tjhm.contains(openCodes1[1]+openCodes1[2]+openCodes1[0])){
						tjhm = tjhm.replace(openCodes1[1]+openCodes1[2]+openCodes1[0], "<font color='red'>"+openCodes1[1]+openCodes1[2]+openCodes1[0]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[1]+openCodes1[2]+openCodes1[0]+"</font>-" +tjhm;
					}else if(tjhm.contains(openCodes1[2]+openCodes1[0]+openCodes1[1])){
						tjhm = tjhm.replace(openCodes1[2]+openCodes1[0]+openCodes1[1], "<font color='red'>"+openCodes1[2]+openCodes1[0]+openCodes1[1]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[2]+openCodes1[0]+openCodes1[1]+"</font>-" +tjhm;
					}else if(tjhm.contains(openCodes1[2]+openCodes1[1]+openCodes1[0])){
						tjhm = tjhm.replace(openCodes1[2]+openCodes1[1]+openCodes1[0], "<font color='red'>"+openCodes1[2]+openCodes1[1]+openCodes1[0]+"</font>");
						tjhm ="<font color='red'>"+openCodes1[2]+openCodes1[1]+openCodes1[0]+"</font>-" +tjhm;
					}
				}
				list.get(i).setTjhm(tjhm);
			}
			return list;
		}
		
		//根据开奖号码 找到对应的两码序列
		private static String matchCode(String[] openCodes){
			String LmCode = null;
			List<String> tmp = new ArrayList<>();
			tmp.add(openCodes[0]+openCodes[1]+openCodes[2]);
			tmp.add(openCodes[0]+openCodes[2]+openCodes[1]);
			tmp.add(openCodes[1]+openCodes[0]+openCodes[2]);
			tmp.add(openCodes[1]+openCodes[2]+openCodes[0]);
			tmp.add(openCodes[2]+openCodes[1]+openCodes[0]);
			tmp.add(openCodes[2]+openCodes[0]+openCodes[1]);
			for(String num: tmp){
				if(type1.contains(num)){
					LmCode = "0123789/2345678";
					break;
				}else if(type2.contains(num)){
					LmCode = "0123489/3456789";
					break;
				}else if(type3.contains(num)){
					LmCode = "0123459/0456789";
					break;
				}else if(type4.contains(num)){
					LmCode = "0123456/0156789";
					break;
				}else if(type5.contains(num)){
					LmCode = "1234567/0126789";
					break;
				}
			}
			return LmCode;
		}
		
		//排除两码
		private static String pclm(List<CaipiaoBean> list,int current) {
			String pclm = "";
			String[] openCodes1 = list.get(current).getOpenCode().split(",");
			if(current != list.size()-1){
				//1、上下两期，百十个位分别相减，组成2组或3组两码，排除；
//				String[] openCodes2 = list.get(current+1).getOpenCode().split(",");
//				String a = Math.abs(Integer.valueOf(openCodes1[0]) - Integer.valueOf(openCodes2[0]))+"";
//				String b = Math.abs(Integer.valueOf(openCodes1[1]) - Integer.valueOf(openCodes2[1]))+"";
//				String c = Math.abs(Integer.valueOf(openCodes1[2]) - Integer.valueOf(openCodes2[2]))+"";
//				if(a.equals(b)){
//					pclm += a+b+","+a+c;
//				}else if(a.equals(c)){
//					pclm += a+b+","+a+c;
//				}else if(b.equals(c)){
//					pclm += a+b+","+b+c;
//				}else{
//					pclm += a+b+","+a+c+","+b+c;
//				}
				
				//5、短斜码 前一期的十位与本期的个位 组成两码 排除
				String[] openCodes2 = list.get(current+1).getOpenCode().split(",");
				pclm += openCodes2[1]+openCodes1[2];
			}
			//2、百十个位求合，排除两码，不足两位的补零；
			if(null != pclm && pclm.trim().length()>0){
				pclm += ",";
			}
			String a = Integer.valueOf(openCodes1[0])+Integer.valueOf(openCodes1[1])+Integer.valueOf(openCodes1[2]) + "";
			if(null != a && a.trim().length() == 1){
				a = "0"+a;
			}
			pclm += a;
			
			//3、遗漏期矩阵 排除两码
//			if(null != pclm && pclm.trim().length()>0){
//				pclm += ",";
//			}
//			String jzpclm = jzpclm(list,current);
//			pclm += jzpclm;
			
			//4、往回数九期 往回数八期 取位差 组排除两码
//			if(null != pclm && pclm.trim().length()>0){
//				pclm += ",";
//			}
//			if(list.size()-1 -current >7 ){
//				//百十个位分别相减，组成2组或3组两码，排除；
//				String[] openCodes3 = list.get(current+8).getOpenCode().split(",");
//				String[] openCodes4 = list.get(current+7).getOpenCode().split(",");
//				String a1 = Math.abs(Integer.valueOf(openCodes3[0]) - Integer.valueOf(openCodes4[0]))+"";
//				String b1 = Math.abs(Integer.valueOf(openCodes3[1]) - Integer.valueOf(openCodes4[1]))+"";
//				String c1 = Math.abs(Integer.valueOf(openCodes3[2]) - Integer.valueOf(openCodes4[2]))+"";
//				if(a1.equals(b1)){
//					pclm += a1+b1+","+a1+c1;
//				}else if(a1.equals(c1)){
//					pclm += a1+b1+","+a1+c1;
//				}else if(b1.equals(c1)){
//					pclm += a1+b1+","+b1+c1;
//				}else{
//					pclm += a1+b1+","+a1+c1+","+b1+c1;
//				}
//			}
			
			return pclm;
		}
		
		
		//推荐号码
		private static List<String> tjhm(String lm,String pclm,int bqkd,String dmhpch) {
			List<String> hmzh = new ArrayList<>();//号码组合
			List<String> pclmList = new ArrayList<>();//排除号码
//			List<String> tjhm_tmp = new ArrayList<>();//两码组合
			
			if(null != lm && lm.trim().length()>0){
				String[] xl = lm.split("/");
				String xl1=xl[0];
				String xl2=xl[1];
				String[] hm1 = xl1.split("");
				String[] hm2 = xl2.split("");
				
				for(int i=1;i<hm1.length;i++){
					for(int j=i+1;j<hm1.length;j++){
						for(int k=j+1;k<hm1.length;k++){
							hmzh.add(hm1[i]+hm1[j]+hm1[k]);
						}
					}
				}
				for(int i=1;i<hm2.length;i++){
					for(int j=i+1;j<hm2.length;j++){
						for(int k=j+1;k<hm2.length;k++){
							hmzh.add(hm2[i]+hm2[j]+hm2[k]);
						}
					}
				}
				
				
				
//				for(int i=1;i<hm1.length;i++){
//					for(int j=i+1;j<hm1.length;j++){
//						lm1.add(hm1[i]+hm1[j]);
//					}
//				}
//				for(int i=1;i<hm2.length;i++){
//					for(int j=i+1;j<hm2.length;j++){
//						lm2.add(hm2[i]+hm2[j]);
//					}
//				}
//				for(int i=1;i<hm1.length;i++){
//					for(int j=0;j<lm2.size();j++){
//						tjhm_tmp.add(hm1[i]+lm2.get(j));
//					}
//				}
//				for(int i=1;i<hm2.length;i++){
//					for(int j=0;j<lm1.size();j++){
//						tjhm_tmp.add(hm2[i]+lm1.get(j));
//					}
//				}
				
			}
			
			
			List<String> tjhm = hmqc(hmzh);
			if(null != pclm && pclm.trim().length()>1){
				String[] pclmArr = pclm.split(",");
				pclmList = Arrays.asList(pclmArr);
			}
			Iterator<String> it = tjhm.iterator(); 
			while (it.hasNext()) {
				String hm = (String) it.next();
				//1、和值5-22点
				int sum = Integer.valueOf(hm.substring(0,1))+Integer.valueOf(hm.substring(1,2))+Integer.valueOf(hm.substring(2));
				if(sum<5 || sum>22){
					it.remove();
					continue;
				}
				//2、跨度0/1/8/9 四种跨度排除
//				Integer[] temp = { Integer.valueOf(hm.substring(0,1)), Integer.valueOf(hm.substring(1,2)), Integer.valueOf(hm.substring(2)) };
//				Arrays.sort(temp);
//				int kd = temp[2] - temp[0] ;
//				if(kd==0 || kd==1 || kd==8 || kd==9){
//					it.remove();
//					continue;
//				}
				
				//4、奖号对码号排除   对码号： 05 16 27 38 49
				if(hm.contains(dmhpch)){
					it.remove();
					continue;
				}
				
				//5、本期奖号的跨度 下一期排除     sqkd是本期跨度
				Integer[] temp = { Integer.valueOf(hm.substring(0,1)), Integer.valueOf(hm.substring(1,2)), Integer.valueOf(hm.substring(2)) };
				Arrays.sort(temp);
				int kd = temp[2] - temp[0] ;
				if(kd == bqkd){
					it.remove();
					continue;
				}
				
				
				//3、根据“排除两码”，排除号码组合
				if(pclmList.size()>0){
					for(String a :pclmList){
						if(a.trim().length()==2){
							if(!a.substring(0,1).equals(a.substring(1))){
								if(hm.contains(a.substring(0,1)) && hm.contains(a.substring(1))){
									it.remove();
									break;
								}
							}else{
								int count =0;
								int index = hm.indexOf(a.substring(0,1));
								if(index>0 && index!=hm.length()-1){
									count++;
									if(hm.substring(index).indexOf(a.substring(0,1))>0){
										count++;
									}
								}
								if(count==2){
									it.remove();
									break;
								}
							}
						}
					}
				}
			}
			return tjhm;
		}
		
		//遗漏期矩阵 排除两码 (遗漏期大于9，取个位数)
		private static String jzpclm(List<CaipiaoBean> list,int current) {
			String pclm = "";
			String[] openCodes = list.get(current).getOpenCode().split(",");
			//新建长度为10的数组 作为矩阵序列
			String[] jzxl = new String[10];
			
			jzxl[Integer.valueOf(openCodes[0])] = openCodes[0];
			jzxl[Integer.valueOf(openCodes[1])] = openCodes[1];
			jzxl[Integer.valueOf(openCodes[2])] = openCodes[2];
			
			for(int i=0;i<10;i++){
				if(null==jzxl[i]){
					int count =0;
					for(int j=current+1;j<list.size();j++ ){
						if(null != list.get(j).getOpenCode()){
							if(!list.get(j).getOpenCode().contains(i+"")){
								count++;
							}else{
								break;
							}
						}
					}
					if(count == 0){
						count = list.size()-current+1;
					}
					if(count > 9){
						count = count % 10;
					}
					jzxl[i]=count+"";
				}
			}
			
			pclm = jzxl[0]+jzxl[9]+","+jzxl[1]+jzxl[8]+","+jzxl[2]+jzxl[7]+","+jzxl[3]+jzxl[6]+","+jzxl[4]+jzxl[5];
			System.out.println("期数："+list.get(current).getExpect());
			System.out.println("矩阵："+Arrays.asList(jzxl));
			return pclm;
		}
		
		//号码去重复
		private static List<String> hmqc(List<String> hmzh) {
			List<String> hmzh4r = new ArrayList<>();//返回的list
			for(String a :hmzh){
				String[] tmp = a.split("");
				String str1 = tmp[1]+tmp[2]+tmp[3];
				String str2 = tmp[1]+tmp[3]+tmp[2];
				String str3 = tmp[2]+tmp[1]+tmp[3];
				String str4 = tmp[2]+tmp[3]+tmp[1];
				String str5 = tmp[3]+tmp[1]+tmp[2];
				String str6 = tmp[3]+tmp[2]+tmp[1];
				if(!hmzh4r.contains(str1) && !hmzh4r.contains(str2) && !hmzh4r.contains(str3) 
						&& !hmzh4r.contains(str4) && !hmzh4r.contains(str5) && !hmzh4r.contains(str6)){
					hmzh4r.add(a);
				}
			}
			return hmzh4r;
		}
		
		//获取对码号排除号
		private static String dmhpch(String hm) {
			int baiDmh = dmh(hm.substring(0,1));
			int shiDmh = dmh(hm.substring(1,2));
			int geDmh = dmh(hm.substring(2));
			return ((baiDmh+shiDmh+geDmh)%10)+"";
		}
		
		//获取对码号
		private static int dmh(String hm) {
			int dmh = 0;
			switch(hm){
			case "0":
				dmh=5;
				break;
			case "5":
				dmh=0;
				break;
			case "1":
				dmh=6;
				break;
			case "6":
				dmh=1;
				break;
			case "2":
				dmh=7;
				break;
			case "7":
				dmh=2;
				break;
			case "3":
				dmh=8;
				break;
			case "8":
				dmh=3;
				break;
			case "4":
				dmh=9;
				break;
			case "9":
				dmh=4;
				break;
			}
			return dmh;
		}
		
		//詹威方法
		public static Map<String, Object> zwff(List<CaipiaoBean> list,int current,List<String> tjhmList) {
			String code = list.get(current).getOpenCode();
			List<String> list1 = new ArrayList<>();
			list1.add("0");
			list1.add("1");
			list1.add("2");
			list1.add("3");
			list1.add("4");
			list1.add("5");
			list1.add("6");
			list1.add("7");
			list1.add("8");
			list1.add("9");
			List<String> list2 = new ArrayList<>();
			String[] oc = code.split(",");
			if(null != code){
				for(String str:oc){
					if(list2.contains(str.trim())){
						continue;
					}
					switch (str.trim()) {
					case "0":
						list1.remove("0");
						list1.remove("5");
						list2.add("0");
						list2.add("5");
						break;
					case "5":
						list1.remove("0");
						list1.remove("5");
						list2.add("0");
						list2.add("5");
						break;
					case "1":
						list1.remove("1");
						list1.remove("6");
						list2.add("1");
						list2.add("6");
						break;
					case "6":
						list1.remove("1");
						list1.remove("6");
						list2.add("1");
						list2.add("6");
						break;
					case "2":
						list1.remove("2");
						list1.remove("7");
						list2.add("2");
						list2.add("7");
						break;
					case "7":
						list1.remove("2");
						list1.remove("7");
						list2.add("2");
						list2.add("7");
						break;
					case "3":
						list1.remove("3");
						list1.remove("8");
						list2.add("3");
						list2.add("8");
						break;
					case "8":
						list1.remove("3");
						list1.remove("8");
						list2.add("3");
						list2.add("8");
						break;
					case "4":
						list1.remove("4");
						list1.remove("9");
						list2.add("4");
						list2.add("9");
						break;
					case "9":
						list1.remove("4");
						list1.remove("9");
						list2.add("4");
						list2.add("9");
						break;
					}
				}
			}
			List<String> zwhm = new ArrayList<>();
			List<String> hbhm = new ArrayList<>();
			if(oc.length ==3 && oc[0].equals(oc[1]) && oc[0].equals(oc[2])){//豹子号
				for(int i=0;i<10;i++){
					zwhm.add(list2.get(0)+list2.get(1)+i);
				}
				for(String cc:tjhmList){
					if(cc.contains(list2.get(0)) && cc.contains(list2.get(1))){
						hbhm.add(cc);
					}
				}
			}else{
				if(list1.size()>list2.size()){
					for(String aa : list2){
						for(int i=0;i<list1.size();i++){
							for(int j=i+1;j<list1.size();j++){
								zwhm.add(list1.get(i)+list1.get(j)+aa);
							}
						}
					}
					for(String cc:tjhmList){
						int conNum6 = 0;
						int conNum4 = 0;
						for(String bb:list1){
							if(cc.contains(bb)){
								conNum6++;
							}
						}
						for(String dd:list2){
							if(cc.contains(dd)){
								conNum4++;
							}
						}
						if(conNum6==2 && conNum4==1){
							hbhm.add(cc);
						}
					}
					
				}else if(list2.size()>list1.size()){
					for(String aa : list1){
						for(int i=0;i<list2.size();i++){
							for(int j=i+1;j<list2.size();j++){
								zwhm.add(list2.get(i)+list2.get(j)+aa);
							}
						}
					}
					for(String cc:tjhmList){
						int conNum6 = 0;
						int conNum4 = 0;
						for(String bb:list2){
							if(cc.contains(bb)){
								conNum6++;
							}
						}
						for(String dd:list1){
							if(cc.contains(dd)){
								conNum4++;
							}
						}
						if(conNum6==2 && conNum4==1){
							hbhm.add(cc);
						}
					}
				}
			}
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("zwhm", zwhm.toString());
			map.put("hbhm", hbhm.toString());
			map.put("hbCount", hbhm.size());
			return map;
		}
		
		public static void main(String[] args) {
			
			System.out.println();
		}
}
