package luckymoney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
  * @ClassName: CommonUtils
  * @Description: 集合计算公共方法
  * @author zhaowei
  * @date 2016年7月15日 下午1:26:37
 */
public class CommonUtils {

	public static int maxNum(int a,int b ,int c){
		
		return  a>b?a>c?a:c:b>c?b:c;
	}
	
	public static int minNum(int a,int b ,int c){
		
		return  a<b?a<c?a:c:b<c?b:c;
	}
	
	//两个集合求交集
	public LinkedList<Integer> intersection(int[] A, int[] B) {
	if (A == null || B == null || A.length == 0 || B.length == 0) return null;
	LinkedList<Integer> list = new LinkedList<Integer>();
	int pointerA = 0;
	int pointerB = 0;
 	while (pointerA < A.length && pointerB < B.length) {
		if (A[pointerA] < B[pointerB]) pointerA++;
		else if (A[pointerA] > B[pointerB]) pointerB++;
		else {
			list.add(A[pointerA]);
			pointerA++;
			pointerB++;
		}
	}
	return list;
	}
	
	//两个数组求差集
	 public static String[] minus(String[] arr1, String[] arr2) {   
	       LinkedList<String> list = new LinkedList<String>();   
	       LinkedList<String> history = new LinkedList<String>();   
	       String[] longerArr = arr1;   
	       String[] shorterArr = arr2;   
	       //找出较长的数组来减较短的数组   
	       if (arr1.length > arr2.length) {   
	           longerArr = arr2;   
	           shorterArr = arr1;   
	       }   
	       for (String str : longerArr) {   
	           if (!list.contains(str)) {   
	               list.add(str);   
	           }   
	       }   
	       for (String str : shorterArr) {   
	           if (list.contains(str)) {   
	               history.add(str);   
	               list.remove(str);   
	           } else {   
	               if (!history.contains(str)) {   
	                   list.add(str);   
	               }   
	           }   
	       }   
	       String[] result = {};   
	       return list.toArray(result);   
	   }   
	 //交集
	 public static List<String> intersect(String[] arr1, String[] arr2) {   
	       Map<String, Boolean> map = new HashMap<String, Boolean>();   
	       LinkedList<String> list = new LinkedList<String>();   
	       for (String str : arr1) {   
	           if (!map.containsKey(str)) {   
	               map.put(str.toString(), Boolean.FALSE);   
	           }   
	       }   
	       for (String str : arr2) {   
	           if (map.containsKey(str)) {   
	               map.put(str.toString(), Boolean.TRUE);   
	           }   
	       }
	       for (Entry<String, Boolean> e : map.entrySet()) {   
	           if (e.getValue().equals(Boolean.TRUE)) {   
	               list.add(e.getKey());   
	           }   
	       }   
	 
	       return list;   
	   }   
	 //数组合并去重
	 public static String[] Union(String[] arr1,String[] arr2){
		   
	        Set<String> set = new HashSet<String>();  
	        for(int i = 0; i < arr1.length ; i++){  
	            set.add(arr1[i]);  
	        }  
	        for(int i = 0; i < arr2.length ; i++){  
	            set.add(arr2[i]);  
	        }  
	          
	        Iterator<String> i = set.iterator();  
	        String[] arrays = new String[set.size()];  
	        int num=0;  
	        while(i.hasNext()){  
	            String a = (String)i.next();  
	            arrays[num] = a;  
	            num = num + 1;  
	        }  
	        Arrays.sort(arrays);  
	        return arrays;
	    }  
	 
	 public static String[] getDuiMaHao(int a,int b,int c){
		 String[] result1 = getDuiMaHaoByOne(a);
		 String[] result2 = getDuiMaHaoByOne(b);
		 String[] result3 = getDuiMaHaoByOne(c);
		 
		 String[] step1 = Union(result1,result2);
		 String[] step2 = Union(step1,result3);
		 //List<String> temp =intersect( intersect(result1,result2).toArray(),result3);
		 return step2;
	 }
	 
	 public static List<String> association(String[] arr1,String[] arr2){
		 List<String> associations = new ArrayList<>();
		 for(String str : arr1){
			 for(int i=0;i<arr2.length;i++){ 
				 if((i+1)==arr2.length){
					 associations.add(str+arr2[i]+arr2[0]);
				 }else{
					 associations.add(str+arr2[i]+arr2[i+1]);
				 }
				 
			 }
		 }
		 
		 for(String str : arr2){
			 for(int i=0;i<arr1.length;i++){ 
				 if((i+1)==arr1.length){
					 associations.add(str+arr1[i]+arr1[0]);
				 }else{
					 associations.add(str+arr1[i]+arr1[i+1]);
				 }
				 
			 }
		 }
		 return associations;
	 }
	 public static String[] getDuiMaHaoByOne(int a){
		 List<String> result = new ArrayList<String>();
		 if(a == 0||a == 5){
			 result.add("0");
			 result.add("5");
		 }else if(a == 1||a == 6){
			 result.add("1");
			 result.add("6");
		 }else if(a == 2||a == 7){
			 result.add("2");
			 result.add("7");
		 }else if(a == 3||a == 8){
			 result.add("3");
			 result.add("8");
		 }else{
			 result.add("4");
			 result.add("9");
		 }
		 return (String[])result.toArray(new String[result.size()]);
	 }
	 
	 //去除和尾加减1
	 public static List<String> removeSomething(int a,int b, int c ,List<String> arr1){
		 
		 int end = (a + b + c)%10;
		 int end1 = (end + 1)%10;
		 int end2 = (end-1 + 10)%10;
		 List<String> result = new ArrayList<>();
		 for(String str: arr1){
			if(!str.endsWith(String.valueOf(end))||!str.endsWith(String.valueOf(end1))||!str.endsWith(String.valueOf(end2))){
				result.add(str);
			}
		 }
		 
		 return result;
	 }
	 public static void main(String[] args) {
		 String[] allNum = {"0","1","2","3","4","5","6","7","8","9"};
		 String[] result = getDuiMaHao(8,6,9);
		 String[] minus = minus(allNum,result);
		 List<String> last =  association(result , minus);
		 System.out.println("last--" + last.size());
		 List<String> last1 = removeSomething(8,6,9,last);
		 System.out.println("last1--" + last1.size());
		 for(String str: last1){
			 System.out.println(str);
		 }
	}
}
