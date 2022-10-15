package bj21609;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n, m;
	static int[][] arr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result;
	static int[][] visited;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		arr = new int[n][n];
		visited = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		while(simulation());
		

		System.out.println(result);

	}

	static boolean simulation() {
		// 1. 크기가 가장 큰 블록 그룹을 찾는다.
		int[] maxBlock = { 0, 0, 0, 0, 0 }; // index, 최대 크기, x, y위치 , 무지개블록수
		int blockIndex = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] > 0 && arr[i][j]<=m && visited[i][j] == 0) {
					// 블록의 개수가 2보다 커야한다.
					Point bfsResult = bfs(i, j, blockIndex);
					int tempBlock = bfsResult.x;
					int rainbow = bfsResult.y;
					
					if (tempBlock < 2) {
						blockIndex++;
						continue;
					}
					if (maxBlock[1] < tempBlock) {
						maxBlock[0] = blockIndex;
						maxBlock[1] = tempBlock;
						maxBlock[2] = i;
						maxBlock[3] = j;
						maxBlock[4] = rainbow;
					}else if(maxBlock[1] == tempBlock) {
						if(maxBlock[4] < rainbow) {
							maxBlock[0] = blockIndex;
							maxBlock[1] = tempBlock;
							maxBlock[2] = i;
							maxBlock[3] = j;
							maxBlock[4] = rainbow;
						}else if(maxBlock[4] == rainbow) {
							if(maxBlock[2]<i) {
								maxBlock[0] = blockIndex;
								maxBlock[1] = tempBlock;
								maxBlock[2] = i;
								maxBlock[3] = j;
								maxBlock[4] = rainbow;
							}else if(maxBlock[2] == i) {
								if(maxBlock[3]<j) {
									maxBlock[0] = blockIndex;
									maxBlock[1] = tempBlock;
									maxBlock[2] = i;
									maxBlock[3] = j;
									maxBlock[4] = rainbow;
								}
							}
						}
					}

					blockIndex++;
				}

			}
		}

		if(maxBlock[1]==0) return false;
			
		// 2. 1에서 찾은 블록 그룹 제거
		result += Math.pow(maxBlock[1], 2);
		cleanVisited();
		bfs(maxBlock[2], maxBlock[3], maxBlock[0]);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == maxBlock[0]) {
					arr[i][j] = 999;
				}
			}
		}

		// 3. 중력 작용
		gravity();

		// 4. 90도 반시계 방향으로 회전
		int[][] tempArr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tempArr[n - 1 - j][i] = arr[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = tempArr[i][j];
				visited[i][j] = 0;
			}
		}

		gravity();
		 		
		return true;
	}

	static Point bfs(int x, int y, int blockIndex) {
		Queue<Point> que = new LinkedList<Point>();
		que.add(new Point(x, y));
		visited[x][y] = blockIndex;
		int blockSize = 1;
		int rainbow = 0;

		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (visited[nx][ny] == blockIndex)
					continue;
				if (arr[nx][ny] == arr[x][y] || arr[nx][ny] == 0) {
					que.add(new Point(nx, ny));
					visited[nx][ny] = blockIndex;
					blockSize++;
					if(arr[nx][ny]==0) rainbow++;
				}
			}

		}

		return new Point(blockSize,rainbow);
	}
	
	static void gravity() {
		for (int i = n - 1; i >= 1; i--) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 999) {
					for (int k = i - 1; k >= 0; k--) {
						if (arr[k][j] == -1)
							break;
						if (arr[k][j] != 999) {
							arr[i][j] = arr[k][j];
							arr[k][j] = 999;
							break;
						}
					}
				}
			}
		}
	}
	
	static void cleanVisited() {
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				visited[i][j] = 0;
	}

}
