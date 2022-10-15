package bj21610;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int result;
	static int n,m;
	static int[][] arr;
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static Queue<Point> cloud = new LinkedList<>();
	static boolean[][] visited; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n=sc.nextInt();
		m=sc.nextInt();
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];
		
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<n+1;j++) {
				arr[i][j]= sc.nextInt();
			}
		}
		
		cloud.add(new Point(n,1));
		cloud.add(new Point(n,2));
		cloud.add(new Point(n-1,1));
		cloud.add(new Point(n-1,2));
		
		
		for(int i=0;i<m;i++) {
			simulation(sc.nextInt(), sc.nextInt());
		}
		
		resultSum();
		
		System.out.println(result);
		
	}
	
	static void simulation(int d,int s) {
		Queue<Point> rain = new LinkedList<>();
//		LinkedList<Point> tempCloud = new LinkedList<>();
		
		//이동하기
		int size = cloud.size();
		for(int i=0;i<size;i++) {
			int nx = cloud.peek().x + dx[d]*s;
			int ny = cloud.peek().y + dy[d]*s;
			
			//범위가 넘어간 경우
			while(nx<=0 || nx>n) nx = nx<=0 ? n+nx : (nx>n ? nx-n : nx);
			while(ny<=0 || ny>n) ny = ny<=0 ? n+ny : (ny>n ? ny-n : ny);
			
			//물의 양 증가
			arr[nx][ny]++;
			
			cloud.poll();
			visited[nx][ny] = true;
			rain.add(new Point(nx,ny));
		}
		
		//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 물의 양이 증가
		size = rain.size();
		for(int i=0;i<size;i++) {
			Point cur = rain.poll();
			int bucket = 0;
			for(int j=2;j<=8;j+=2) {
				int nx = cur.x + dx[j];
				int ny = cur.y + dy[j];
				
				if(nx<=0 || ny<=0 || nx>=n+1 || ny>=n+1 || arr[nx][ny]==0) continue;
				
				bucket++;
			}
			
			arr[cur.x][cur.y] += bucket; 
		}
		
		//구름 생성
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
//				boolean flag = false;
				if(arr[i][j]>=2) {
//					for(int k=0;k<tempCloud.size();k++) {
//						if(i==tempCloud.get(k).x && j==tempCloud.get(k).y) {
//							flag = true;
//							break;
//						}
//					}
//					if(flag) continue;
					
					if(visited[i][j]) {
						visited[i][j] = false;
					}else {
						arr[i][j] -= 2;
						cloud.add(new Point(i,j));
					}
				}
			}
		}
		
	}
	
	static void resultSum() {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				result += arr[i][j];
			}
		}
	}

}