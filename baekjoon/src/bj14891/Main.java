package bj14891;

import java.util.Scanner;

public class Main {
	static char[][] gear = new char[4][8];
	static int k;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<4;i++) {
			String a = sc.nextLine();
			for(int j=0;j<8;j++)
				gear[i][j] = a.charAt(j);
		}
		k = sc.nextInt();
		
		for(int i=0;i<k;i++) {
			spin(sc.nextInt()-1,sc.nextInt());
		}

		System.out.println(calResult());
	}
	static void spin(int n,int dir) {
		int[] checkRotate = new int[4];
		checkRotate[n] = dir;
		//왼쪽으로 확인
		for(int i=n-1;i>=0;i--) {
			if(gear[i+1][6]!=gear[i][2])
				checkRotate[i] = checkRotate[i+1] * (-1);
			else
				break; //그 뒤로는 회전하지 않기 때문에
		}
		
		//오른쪽으로 확인
		for(int i=n+1;i<=3;i++) {
			if(gear[i-1][2]!=gear[i][6])
				checkRotate[i] = checkRotate[i-1] * (-1);
			else
				break;
		}
		
		for(int i=0;i<4;i++) {
			rotate(i,checkRotate[i]);
		}
	}
	static void rotate(int n,int dir) {
		char[] tempGear = new char[8];
		if(dir==1) {
			for(int i=0;i<7;i++) {
				tempGear[i+1] = gear[n][i];
			}
			tempGear[0] = gear[n][7];
			
		}else if(dir==-1) {	
			for(int i=7;i>0;i--)
				tempGear[i-1] = gear[n][i];
			tempGear[7] = gear[n][0];
		}else return;
		
		for(int i=0;i<8;i++)
			gear[n][i]=tempGear[i];
	}
	static int calResult() {
		int result = 0;
		if(gear[0][0] == '1')
			result += 1;
		if(gear[1][0] == '1')
			result += 2;
		if(gear[2][0] == '1')
			result += 4;
		if(gear[3][0] == '1')
			result += 8;
		return result;
	}
}
