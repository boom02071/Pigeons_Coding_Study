package bj14889;

import java.util.Scanner;
public class Main {
	static int n;
	static int[][] arr;
	static boolean[] team;
	static int result=1000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		arr = new int[n][n];
		team = new boolean[n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				arr[i][j]=sc.nextInt();
		
		func(1,0);
		System.out.println(result);
	}
	static void func(int index,int cnt) {
		//팀을 나눈 경우
		if(cnt==n/2) {
			int start=0;
			int link=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(team[i]&&team[j]) start += arr[i][j];
					else if(!team[i]&&!team[j])link += arr[i][j];
				}
			}
			
			result = Math.min(result, Math.abs(start-link));
			return;
		}
		
		if(index==n)
			return;
		
		
		
		//팀 나누기
		for(int i=index;i<n;i++) {
			team[i]=true;
			func(i+1,cnt+1);
			team[i]=false;
		}
	}

}
