package bj21608;

import java.awt.Point;
import java.util.Scanner;

public class Main {
	static int n;
	static int[][] map;
	static int result;
	static int[][] satiScore;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		satiScore = new int[n*n][5];
		
		for(int i=0;i<n*n;i++) 
			for(int j=0;j<5;j++)
				satiScore[i][j]=sc.nextInt();
		
		simulation();
		System.out.println(result);
		
	}
	
	static void simulation() {
		for(int i=0;i<n*n;i++) {
			//한 개씩 꺼내서 가장 만족도가 높은 자리 찾기
			Point maxSeat = findMaxSati(i);
			map[maxSeat.x][maxSeat.y] = satiScore[i][0];
		}
		
		//만족도 결과
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n*n;k++) {
					if(satiScore[k][0]==map[i][j]) {
						int sati = sumSati(i,j,k);
						if(sati==1)
							result += 1;
						else if(sati==2)
							result += 10;
						else if(sati==3)
							result += 100;
						else if(sati==4)
							result += 1000;
					}
				}
			}
		}
	}
	static Point findMaxSati(int index) {
		int score = 0;
		int blank = 0;
		Point maxSeat = new Point(-1,-1);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				//이미 숫자가 위치한다면 쓰루
				if(map[i][j]!=0) continue;
				int tempScore = 0; //만족도 개수
				int tempBlank = 0; //빈자리 개수
				
				//기준점으로 부터 주위 4방위 조사
				for(int k=0;k<4;k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					
					if(nx<0 || ny<0 || nx>=n || ny>=n)
						continue;
					
					//0인경우
					if(map[nx][ny]==0) tempBlank++;
					else {
						//만족도 리스트에 있는지 확인
						for(int l=1;l<5;l++)
							if(map[nx][ny]==satiScore[index][l])
								tempScore++;
					}
				}
				
				//만족도가 가장 높은 곳 저장
				/*
				 * if(score<tempScore) { score = tempScore; maxSeat.x = i; maxSeat.y = j; }
				 * 
				 * if(score==0 && map[i][j]==0 && maxSeat.x!=i && maxSeat.y!=j) { maxSeat.x = i;
				 * maxSeat.y = j; }
				 */
				
				if(score<tempScore) {
					score = tempScore;
					blank = tempBlank;
					maxSeat.x = i;
					maxSeat.y = j;
				}else if(score == tempScore) {
					if(blank<tempBlank) {
						blank = tempBlank;
						maxSeat.x = i;
						maxSeat.y = j;
					}
				}
				
				if(maxSeat.x==-1 && maxSeat.y==-1 && map[i][j]==0) {
					score = tempScore;
					blank = tempBlank;
					maxSeat.x = i;
					maxSeat.y = j;
				}
			}
		}
		
		
		return maxSeat;
	}

	static int sumSati(int x,int y,int index) {
		int sati = 0;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=n || ny>=n)
				continue;
			
			for(int j=1;j<5;j++) {
				if(map[nx][ny]==satiScore[index][j])
					sati++;
			}
		}
		
		return sati;
	}
}
