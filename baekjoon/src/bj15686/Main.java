package bj15686;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int n, m;
	static int[][] arr;
	static List<Point> home = new ArrayList<Point>();
	static List<Point> chicken = new ArrayList<Point>();
	static int[] selChicken;
	static int result=999999999;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n][n];
		selChicken = new int[m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]==1)
					home.add(new Point(i,j));
				if(arr[i][j]==2)
					chicken.add(new Point(i,j));
					
			}
		}
		
		selectM(0,0);
		System.out.println(result);

	}
	static void selectM(int index,int cnt) {
		if(cnt==m) {
			int chickDist = 0;
			for(int i=0;i<home.size();i++) {
				int minDist = 999999999;
				for(int j=0;j<m;j++) {
					int disX = Math.abs(home.get(i).x-chicken.get(selChicken[j]).x);
					int disY = Math.abs(home.get(i).y-chicken.get(selChicken[j]).y);
					minDist = Math.min(minDist, disX+disY);
				}
				chickDist += minDist;
			}
			result = Math.min(result, chickDist);
			return;
		}
		if(index==chicken.size())
			return;
		
		selChicken[cnt] = index;
		selectM(index+1,cnt+1);
		selectM(index+1,cnt);
	}

}
