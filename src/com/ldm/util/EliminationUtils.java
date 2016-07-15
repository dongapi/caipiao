package luckymoney;

/**
  * @ClassName: EliminationUtils
  * @Description: 个位出号排除法 -- 差两个方法
  * @author zhaowei
  * @date 2016年7月15日 下午1:20:42
 */
public class EliminationUtils {

	private static int hundred = 0;
	
	private static int ten = 0 ;
	
	private static int single = 0;
	
	private static int maxNum = 0;
	
	private static int minNum = 0;
	
	public EliminationUtils(String lastCode){
		
		hundred = Integer.parseInt(lastCode.substring(0, 1));
		ten = Integer.parseInt(lastCode.substring(1,2));
		single = Integer.parseInt(lastCode.substring(2,3));
		maxNum = CommonUtils.maxNum(hundred, ten, single);
		minNum = CommonUtils.minNum(hundred, ten, single);
	}

	public int one(){
		return Math.abs(hundred - single);
	}
	
	public int two(){
		int temp = single + (maxNum - minNum);
		System.out.println(temp + "--");
		return temp%10;
	}
	
	public int three(){
		int temp = (hundred + ten + single)%10 + 3;
		return temp%10;
	}
	
	public int four(){
		int temp = single * 2 + 8 ;
		return temp%10;
	}
	
	public int five(){
		if(single == 1){
			return 1;
		}else if(single == 2){
			return 2;
		}else {
			return 3;
		}
	}
	
	public static void main(String[] args) {

	}
}
