package bj12100;

import java.util.Scanner;

public class Main {
	static int n;
	static int[][] board;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		board = new int [n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
			
		backTracking(0);
		 
		System.out.println(result);
		
	}

	static void backTracking(int index) {
		//5번 이동했을 때
		if(index == 5) {
			int temp = 0;
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					temp = Math.max(temp, board[i][j]);
			result = Math.max(temp, result);
			return;
		}
				
		//현재 상태 저장
		int[][] tempArr = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				tempArr[i][j] = board[i][j];
			}
		}
		
		//백트래킹
		for(int i=1;i<=4;i++) {
			//이동시키기
			move(i);
			backTracking(index+1);
			//이전 상태로 복원
			for(int k=0;k<n;k++) {
				for(int j=0;j<n;j++) {
					board[k][j] = tempArr[k][j];
				}
			}
		}
		
	}

	static void move(int dir) {
		if(dir==1) {
			//1. 계산하기
			for(int i=0;i<n-1;i++) {
				for(int j=0;j<n;j++) {
					if(board[i][j]==0) continue;
					for(int k=i+1;k<n;k++) {
						if(board[i][j]==board[k][j]) {
							board[i][j] *= 2;
							board[k][j] = 0;
							break;
						}
						if(board[k][j]!=0) break;
					}
				}
			}
			//2. 빈틈이 없도록 이동하기
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(board[i][j]!=0) continue;
					for(int k=i+1;k<n;k++) {
						if(board[k][j]!=0) {
							board[i][j]=board[k][j];
							board[k][j]=0;
							break;
						}
					}
				}
			}
		}
		else if(dir==2) {
			//1. 계산하기
			for(int i=0;i<n-1;i++) {
				for(int j=0;j<n;j++) {
					if(board[j][i]==0) continue;
					for(int k=i+1;k<n;k++) {
						if(board[j][i]==board[j][k]) {
							board[j][i] *= 2;
							board[j][k] = 0;
							break;
						}
						if(board[j][k]!=0) break;
					}
				}
			}
			//2. 빈틈이 없도록 이동하기
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(board[j][i]!=0) continue;
					for(int k=i+1;k<n;k++) {
						if(board[j][k]!=0) {
							board[j][i]=board[j][k];
							board[j][k]=0;
							break;
						}
					}
				}
			}
		}
		else if(dir==3) {
			//1. 계산하기
			for(int i=n-1;i>0;i--) {
				for(int j=0;j<n;j++) {
					if(board[i][j]==0) continue;
					for(int k=i-1;k>=0;k--) {
						if(board[i][j]==board[k][j]) {
							board[i][j] *= 2;
							board[k][j] = 0;
							break;
						}
						if(board[k][j]!=0) break;
					}
				}
			}
			//2. 빈틈이 없도록 이동하기
			for(int i=n-1;i>0;i--) {
				for(int j=0;j<n;j++) {
					if(board[i][j]!=0) continue;
					for(int k=i-1;k>=0;k--) {
						if(board[k][j]!=0) {
							board[i][j]=board[k][j];
							board[k][j]=0;
							break;
						}
					}
				}
			}
		}
		else if(dir==4) {
			//1. 계산하기
			for(int i=n-1;i>0;i--) {
				for(int j=0;j<n;j++) {
					if(board[j][i]==0) continue;
					for(int k=i-1;k>=0;k--) {
						if(board[j][i]==board[j][k]) {
							board[j][i] *= 2;
							board[j][k] = 0;
							break;
						}
						if(board[j][k]!=0) break;
					}
				}
			}
			//2. 빈틈이 없도록 이동하기
			for(int i=n-1;i>0;i--) {
				for(int j=0;j<n;j++) {
					if(board[j][i]!=0) continue;
					for(int k=i-1;k>=0;k--) {
						if(board[j][k]!=0) {
							board[j][i]=board[j][k];
							board[j][k]=0;
							break;
						}
					}
				}
			}
		}
	}
}
