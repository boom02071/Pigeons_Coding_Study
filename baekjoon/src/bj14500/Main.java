package bj14500;

import java.util.Scanner;

public class Main {
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				arr[i][j] = sc.nextInt();
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j] = true;
				bruteForce(i,j,1,arr[i][j]);
				visited[i][j] = false;
			}
		}
		
		System.out.println(result);
	}
	static void bruteForce(int x,int y,int index, int temp) {
		if(index == 4) {
			result = Math.max(result, temp);
			return;
		}
		
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny])
				continue;
			
			visited[nx][ny]=true;
			bruteForce(nx,ny,index+1,temp+arr[nx][ny]);
			if(index==2)
				bruteForce(x,y,index+1,temp+arr[nx][ny]);
			visited[nx][ny]=false;
		}
	}

}
