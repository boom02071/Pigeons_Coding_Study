package bj14888;
import java.util.Scanner;
public class Main {
	static int n;
	static int[] arr;
	static int[] oper = new int[4];
	static int minResult=1000000000, maxResult=-1000000000; //여기가 중요
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		
		for(int i=0;i<4;i++)
			oper[i]=sc.nextInt();
		
		calculate(arr[0],oper[0],oper[1],oper[2],oper[3],1);
		System.out.println(maxResult);
		System.out.println(minResult);
	}

	static void calculate(int result, int plus, int minus, int multy, int div,int index) {
		if(plus==0 && minus==0 && multy==0 && div==0) {
			if(minResult>result) minResult = result;
			if(maxResult<result) maxResult = result;
			return;
		}
		
		if(plus>0)
			calculate(result+arr[index], plus-1, minus, multy, div, index+1);
		if(minus>0)
			calculate(result-arr[index], plus, minus-1, multy, div, index+1);
		if(multy>0)
			calculate(result*arr[index], plus, minus, multy-1, div, index+1);
		if(div>0)
			calculate(result/arr[index], plus, minus, multy, div-1, index+1);
	}
}
