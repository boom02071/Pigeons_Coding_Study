package bj14890;

import java.util.Scanner;

public class Main {
	static int n,l;
	static int[][] arr;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		l = sc.nextInt();
		
		arr = new int[n][n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) 
				arr[i][j]=sc.nextInt();
			
		
		func();
		System.out.println(result);
	}
	static void func() {
		for(int i=0;i<n;i++) {
			boolean flag = true;
			int cnt=1;
			//좌>우
			for(int j=1;j<n;j++) {
				//오르막길
				if(arr[i][j-1]+1 == arr[i][j] && cnt>=l) {
					cnt = 1;
				}//내려막길
				else if(arr[i][j-1] == arr[i][j]+1 && cnt>=0) {
					cnt = -l+1;
				}else if(arr[i][j-1]==arr[i][j])
					cnt++;
				else {//2이상 차이나는 경우
					flag = false;
					break;
				}
			}
			
			if(flag && cnt>=0) result++;
		
		}
		
		for(int i=0;i<n;i++) {
			//위>아래
			boolean flag = true;
			int cnt = 1;
			for(int j=1;j<n;j++) {
				if(arr[j-1][i]+1 == arr[j][i] && cnt>=l)
					cnt = 1;
				else if(arr[j-1][i] == arr[j][i]+1 && cnt>=0)
					cnt = -l+1;
				else if(arr[j-1][i] == arr[j][i])
					cnt++;
				else {
					flag = false;
					break;
				}
			}
			
			if(flag && cnt>=0) result++;
		}
	}

}
