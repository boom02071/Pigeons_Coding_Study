package bj14499;

import java.awt.Point;
import java.util.Scanner;

public class Main {
	static int n, m;
	static Point curDice = new Point();
	static int k;
	static int[][] arr;
	static int[] dice = new int[6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		curDice.x = sc.nextInt();
		curDice.y = sc.nextInt();
		k = sc.nextInt();
		arr = new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0;i<k;i++) {
			int result = rolling(sc.nextInt());
			if(result != -1)
			System.out.println(result);
		}
	}
	static void spinDice(int dir) {
		int temp = 0;
		switch(dir) {
		case 1:
			temp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;
			break;
		case 2:
			temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
			break;
		case 3:
			temp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
			break;
		case 4:
			temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
			break;
		}
	}
	static int rolling(int dir) {
		int dx[] = {0,0,0,-1,1};
		int dy[] = {0,1,-1,0,0};
		
		int nx = curDice.x + dx[dir];
		int ny = curDice.y + dy[dir];
		
		if(nx<0 || ny<0 || nx>=n || ny>=m) return -1;
		spinDice(dir);
		if(arr[nx][ny]==0) {
			arr[nx][ny]=dice[5];
		}else {
			dice[5] = arr[nx][ny];
			arr[nx][ny] = 0;
		}
		
		curDice.x = nx;
		curDice.y = ny;
		
		return dice[0];
	}

}
