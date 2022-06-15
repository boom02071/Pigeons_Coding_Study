package bj14503;

import java.util.Scanner;

public class bj14503 {
	static int n,m;
	static int r,c,d;
	static int[][] arr;
	static int result;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void bj14503(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][m];
		
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		
		simul(r,c,d);
		
		System.out.println(result);
	}
	
	static void simul(int curX, int curY, int curD) {
		if(arr[curX][curY]==0) result++;
		arr[curX][curY]= -1;
		
		int nd = curD;
		for(int i=0;i<4;i++) {
			nd = nextDir(nd);
			int nx = curX + dx[nd];
			int ny = curY + dy[nd];
			if(arr[nx][ny]==0) {
				simul(nx,ny,nd);
				return;
			}
			
		}
			
		nd = nextDir(nextDir(curD));
		int nx = curX + dx[nd];
		int ny = curY + dy[nd];
		
		
		if(nx<0 || ny<0 || nx>=n || ny>=m || arr[nx][ny]==1) return;
		simul(nx,ny,curD);
		
	}

	static int nextDir(int curD) {
		switch(curD) {
			case 0 : return 3;
			case 1 : return 0;
			case 2 : return 1;
			case 3 : return 2;
		}
		return 0;
	}
}
