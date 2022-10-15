package bj15683;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int n,m;
	static int[][] arr;
	static int result=10000;
	static int cctvCnt;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static LinkedList<Point> cctv = new LinkedList<Point>();
	static int[] cctvDir;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j]!=0 && arr[i][j]!=6) {
					cctvCnt++;
					cctv.add(new Point(i,j));
				}
			}
		}
		
		cctvDir = new int[cctvCnt];
	
		bruteForce(0);
		System.out.println(result);
	}
	static int cntArea() {
		int cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==0)
					cnt++;
			}
		}
		
		return cnt;
	}
	static void bruteForce(int index) {
		if(index==cctvCnt) {
//			result = Math.min(result, cntArea());
			testCctv();
			
			return;
		}
				
		for(int i=0;i<4;i++) {
			cctvDir[index] = i;
			bruteForce(index+1);
		}
		
	}
	
	static void testCctv() {
		int[][] tempArr = new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				tempArr[i][j]=arr[i][j];
		
		for(int i=0;i<cctvCnt;i++) {
			Point cp = cctv.get(i);
			
			switch(arr[cp.x][cp.y]) {
			case 1:
				testCctv2(cp.x,cp.y,cctvDir[i]);
				break;
			case 2:
				if(cctvDir[i]%2==0) {
					testCctv2(cp.x,cp.y,0);
					testCctv2(cp.x,cp.y,2);
				}else {
					testCctv2(cp.x,cp.y,1);
					testCctv2(cp.x,cp.y,3);
				}	
				break;
			case 3:
				if(cctvDir[i]<3) {
					testCctv2(cp.x,cp.y,cctvDir[i]);
					testCctv2(cp.x,cp.y,cctvDir[i]+1);
				}else {
					testCctv2(cp.x,cp.y,3);
					testCctv2(cp.x,cp.y,0);
				}
				break;
			case 4:
				if(cctvDir[i]<=1) {
					testCctv2(cp.x,cp.y,cctvDir[i]);
					testCctv2(cp.x,cp.y,cctvDir[i]+1);
					testCctv2(cp.x,cp.y,cctvDir[i]+2);
				}else if(cctvDir[i]==2) {
					testCctv2(cp.x,cp.y,cctvDir[i]);
					testCctv2(cp.x,cp.y,cctvDir[i]+1);
					testCctv2(cp.x,cp.y,0);
				}else {
					testCctv2(cp.x,cp.y,cctvDir[i]);
					testCctv2(cp.x,cp.y,0);
					testCctv2(cp.x,cp.y,1);
				}
				break;
			case 5:
				testCctv2(cp.x,cp.y,0);
				testCctv2(cp.x,cp.y,1);
				testCctv2(cp.x,cp.y,2);
				testCctv2(cp.x,cp.y,3);
				break;
			}		
		}
		
		
		result = Math.min(result, cntArea());
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				arr[i][j]=tempArr[i][j];
	}
	
	static void testCctv2(int x,int y,int dir) {
		while(true) {
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			
			if(nx<0 || ny<0 || nx>=n || ny>=m || arr[nx][ny]==6)
				break;
			
			if(arr[nx][ny]==0)
				arr[nx][ny]=-1;
			
			x = nx;
			y = ny;
			
		}
	}

}
