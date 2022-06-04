package bj14502;
import java.util.Arrays;
import java.util.Scanner;

public class bj14502 {
	static int n,m;
	static int[][] arr;
	static int[][] tempArr;
	static boolean[][] visited;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][m];
		tempArr = new int[n][m];
		visited = new boolean[n][m];
		result = 0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		makeWall(0);
		
		System.out.println(result);
		
	}
	static void dfs(int x,int y) {
		visited[x][y] = true;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny])
				continue;
			
			if(tempArr[nx][ny]==0) {
				tempArr[nx][ny]=2;
				dfs(nx,ny);
			}
		}
		
	}
	static void calcSafeArea() {
		int cnt = 0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(tempArr[i][j]==0)
					cnt++;
			}
		}
		
		if(result<cnt)
			result = cnt;
		
	}
	static void makeWall(int d) {
		//벽이 3개 세웠을 때
		if(d==3) {
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					tempArr[i][j] = arr[i][j];
					
			//바이러스 퍼트려보기
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(tempArr[i][j]==2 && !visited[i][j])
						dfs(i,j);
				}
			}
			
			//안전지역 구하기
			calcSafeArea();
			
			//초기화
			for(int i=0;i<n;i++)
				Arrays.fill(visited[i],false);
			return;
		}
		
		//백트래킹으로 벽 세우기
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==0) {
					arr[i][j]=1;
					makeWall(d+1);
					arr[i][j]=0;
				}
			}
		}
		
	}

}
