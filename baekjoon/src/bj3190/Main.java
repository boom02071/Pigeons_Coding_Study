package bj3190;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,k,l;
	static int[][] arr;
	static int result=1;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static Queue<command> com = new LinkedList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n][n];
		
		k = sc.nextInt();
		for(int i=0;i<k;i++) {
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
			
			arr[x][y]=1;
		}
		
		
		l = sc.nextInt();
		for(int i=0;i<l;i++) {
			int x = sc.nextInt();
			char c = sc.next().charAt(0);
			com.add(new command(x,c));
		}
		
		//simulation
		simulation();
		
		System.out.println(result);
	}
	static void simulation() {
		Deque<Point> snake = new LinkedList<>();
		snake.add(new Point(0,0));
		int curD = 0;
		
		while(true) {
			//뱀의 머리를 다음칸에 위치
			Point curSnake = snake.peekFirst();
			int nx = curSnake.x + dx[curD];
			int ny = curSnake.y + dy[curD];
			
			//이동한 칸이 벽이라면
			if(nx<0 || ny<0 || nx>=n || ny>=n)
				break;
			
			//이동한 칸에 뱀이 있는 자리라면
			int size = snake.size();
			for(int i=0;i<size;i++) {
				if(snake.getFirst().x == nx && snake.getFirst().y==ny) {
					return;
				}
				snake.addLast(snake.pollFirst());
			}
			
			snake.addFirst(new Point(nx,ny));
			//이동한 칸에 사과가 있다면
				//사과가 없어지고 꼬리는 삭제하지 않음
			if(arr[nx][ny]==1) {
				arr[nx][ny]=0;
			}
			//이동한 칸에 사과가 없다면
				//꼬리는 삭제
			else {
				snake.pollLast();
			}
		
			
			//회전 해야 한다면
			if(!com.isEmpty()) {
				if(com.peek().x == result) {
					char curC = com.peek().c;
					if(curC == 'L') {
						if(curD!=0)
							curD--;
						else curD = 3;
					}
					else if(curC == 'D') {
						if(curD!=3)
							curD++;
						else curD = 0;
					}
					com.poll();
				}
			}
			
			result++;
				
		}
	}
	
}
class command{
	int x;
	char c;
	public command(int x2, char c2) {
		// TODO Auto-generated constructor stub
		x = x2;
		c = c2;
	}
}