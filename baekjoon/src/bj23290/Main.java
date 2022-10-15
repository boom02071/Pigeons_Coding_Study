package bj23290;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int M,S;
	static int[][] arr = new int[5][5];
	static int[][] fishSmell = new int[5][5];
	static LinkedList<fishClass> fish = new LinkedList<>();
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static Point shark = new Point();
	static int result;
	
	
	//dfs에 필요한 변수
	static boolean[][] visited = new boolean[5][5];
	static int fishCnt;
	static int[] moveResult = new int[3];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		S = sc.nextInt();
		
		for(int i=0;i<M;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			fish.add(new fishClass(x,y,d));
		}
	
		shark.x = sc.nextInt();
		shark.y = sc.nextInt();
		
		for(int i=0;i<S;i++) {
			simulation();
			System.out.println(i+" : "+fish.size());
		}
		
		result = fish.size();
		System.out.println(result);
	}
	static void simulation() {
		//1. 물고기를 복제한다.
		LinkedList<fishClass> temp = new LinkedList<fishClass>();

		
		//2. 모든 물고기가 한 칸 이동			
		for(fishClass f : fish) {
			temp.add(new fishClass(f.x,f.y,f.d));
			//이동할 수 있는 칸이 있을 때까지 회전
			for(int i=0;i<8;i++) {
				int dir = f.d-i<1?f.d-i+8:f.d-i;
				int nx = f.x + dx[dir];
				int ny = f.y + dy[dir];
				if(rotateFish(nx,ny)) {
					f.x = nx;
					f.y = ny;
					f.d = dir;
					break;
				}
			}
			
			arr[f.x][f.y]++;
		}
		
		//3. 상어가 연속해서 3칸 이동
		fishCnt = 0;
		for(int i=0;i<3;i++) moveResult[i] = 0;
		for(int i=1;i<=4;i++)
			for(int j=1;j<=4;j++)
				visited[i][j] = false;
		
		int[] move = new int[3];
		dfs(shark.x, shark.y, 0, 0, move);
		
		//3.1 물고기 냠냠 후 냄새 남기기
		for(int i=0;i<3;i++) {
			int nx = shark.x + dx[moveResult[i]];
			int ny = shark.y + dy[moveResult[i]];
			
			if(arr[nx][ny]>0) {
				fishSmell[nx][ny]=3;
				int size = fish.size();
				for(int j=0;j<size;j++) {
					if(fish.get(j).x==nx && fish.get(j).y==ny) {
						fish.remove(j);
						size--;
						j--;
					}
				}
			}
			
			shark.x = nx;
			shark.y = ny;
		}
		
		
		//4. 두 번 전 물고기 냄새 삭제
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				if(fishSmell[i][j]>0)
					fishSmell[i][j]--;
				arr[i][j] = 0;
			}
		}
		
		//5. 복제 마법 ㄱㄱ
		for(fishClass f : temp) {
			fish.add(new fishClass(f.x,f.y,f.d));
		}
	}
	static boolean rotateFish(int nx,int ny) {
		
		if(nx<=0 || ny<=0 || nx>4 || ny>4 
				|| (nx==shark.x && ny==shark.y) 
				|| (fishSmell[nx][ny]>0))
			return false;
		
		return true;
	}
	static void dfs(int x,int y,int index,int cnt,int[] move) { //이동한 경로 저장해 놓을 필요가 있음
		if(index>=3) {
			if(fishCnt<cnt) {
				fishCnt = cnt;
				for(int i=0;i<3;i++) moveResult[i] = move[i];
			}
			else if(fishCnt == cnt) {
				if(compare(move)) //move가 moveResult보다 사전순으로 앞서다.
				{
					fishCnt = cnt;
					for(int i=0;i<3;i++) moveResult[i] = move[i];
				}
			}
			
			return;
		}
		
		visited[x][y]= true;
		
		for(int i=1;i<8;i+=2) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<=0 || ny<=0 || nx>4 || ny>4 || visited[nx][ny])
				continue;
			
			//물고기가 있는 경우
			move[index] = i;
			if(arr[nx][ny]>0) {
				dfs(nx,ny,index+1,cnt+arr[nx][ny],move);
			}
			else
				dfs(nx,ny,index+1,cnt,move);
		}
		
		visited[x][y] = false;
		
	}
	static boolean compare(int[] move) {
		int temp1=0;
		int temp2=0;
		for(int i=0;i<3;i++) {
			switch(moveResult[i]) {
			case 0:
				temp1 = 9;
				break;
			case 1:
				temp1 = 2;
				break;
			case 3:
				temp1 = 1;
				break;
			case 5:
				temp1 = 4;
				break;
			case 7:
				temp1 = 3;
				break;
			}
			
			switch(move[i]) {
			case 0:
				temp2 = 9;
				break;
			case 1:
				temp2 = 2;
				break;
			case 3:
				temp2 = 1;
				break;
			case 5:
				temp2 = 4;
				break;
			case 7:
				temp2 = 3;
				break;
			}
					
			if(temp1<temp2)//사전순으로 temp1이 앞서다
				return false;
			else if(temp1>temp2) return true;
		}
		
		return false;
	}

}
class fishClass{
	int x;
	int y;
	int d;
	public fishClass(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}		
}