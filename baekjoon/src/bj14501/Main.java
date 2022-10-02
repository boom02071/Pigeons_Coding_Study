package bj14501;

import java.util.Scanner;

public class Main {
	static int n;
	static int[][] arr;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n][2];
		
		for(int i=0;i<n;i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		dfs(0,0);
		
		System.out.println(result);
		
	}
	static void dfs(int index,int profit) {
		if(index==n) {
			result = Math.max(result, profit);
			return;
		}else if(index>n) return;
		
		dfs(index+arr[index][0],profit+arr[index][1]);
		dfs(index+1,profit);
	}

}
