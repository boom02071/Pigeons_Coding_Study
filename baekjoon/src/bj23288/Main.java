package bj23288;

import java.awt.Point;
import java.util.Scanner;

public class Main {
	static int n,m,k;
	static int[][] arr;
	static int result;
	static int[] dice = {2,1,5,6,4,3};
	static Point curDice = new Point(0,0);
	static int curDir = 0;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static boolean[][] visited;
	static int move;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				arr[i][j] = sc.nextInt();
		
		for(int i=0;i<k;i++)
			simulation();
		
		System.out.println(result);
	}
	static void simulation() {
		// 주사위 한 칸 이동
		int nx = curDice.x + dx[curDir];
		int ny = curDice.y + dy[curDir];
		
		if(nx<0 || ny<0 || nx>=n || ny>=m) {
			curDir = curDir<=1 ? curDir+2 : curDir-2;
			nx = curDice.x + dx[curDir];
			ny = curDice.y + dy[curDir];
		}
		curDice.x = nx;
		curDice.y = ny;
		
		rotateDice();
		
		//주사위 점수 획득
		dfs(nx,ny,arr[nx][ny]);
		result += (arr[nx][ny] * move);
		
		//초기화
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				visited[i][j] = false;
		move = 0;
		
		//이동 방향 결정
		if(dice[3] > arr[nx][ny]) {
			curDir = curDir!=3 ? curDir+1 : 0;
		}else if(dice[3] < arr[nx][ny]) {
			curDir = curDir!=0 ? curDir-1 : 3;
		}
	}
	static void rotateDice() {
		int[] temp = new int[4];
		
		if(curDir==0){
			temp[0] = dice[1];
			for(int i=1;i<4;i++)
				temp[i] = dice[i+2];
			
			dice[1] = temp[2];
			dice[3] = temp[3];
			dice[4] = temp[1];
			dice[5] = temp[0];
		}
		else if(curDir==1) {
			for(int i=0;i<4;i++)
				temp[i] = dice[i];
			
			dice[0] = temp[3];
			dice[1] = temp[0];
			dice[2] = temp[1];
			dice[3] = temp[2];
		}
		else if(curDir==2){
			temp[0] = dice[1];
			for(int i=1;i<4;i++)
				temp[i] = dice[i+2];
			
			dice[1] = temp[3];
			dice[3] = temp[2];
			dice[4] = temp[0];
			dice[5] = temp[1];
		}else if(curDir==3){
			for(int i=0;i<4;i++)
				temp[i] = dice[i];
			
			dice[0] = temp[1];
			dice[1] = temp[2];
			dice[2] = temp[3];
			dice[3] = temp[0];
		}
	}
	static void dfs(int x,int y,int num) {
		visited[x][y] = true;
		move++;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny])
				continue;
			
			if(arr[nx][ny] == num)
				dfs(nx,ny,num);
		}
	}

}
