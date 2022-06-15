#include<iostream>
#include<queue>
using namespace std;
int n, m;
int r, c, d;
int arr[51][51];
bool visited[51][51];
int dx[] = { -1,0,1,0 };
int dy[] = { 0,1,0,-1 };
int cnt;
queue<pair<int, int>> q;
int way(int d) {
	switch (d) {
	case 0: return 3;
	case 1: return 0;
	case 2: return 1;
	case 3: return 2;
	}

	return 0;
}
void BFS() {
	visited[r][c] = true;
	q.push(make_pair(r, c));
	cnt++;

	int spin = 0;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		int nx = x + dx[way(d)];
		int ny = y + dy[way(d)];

		if (arr[nx][ny] == 0 && !visited[nx][ny]) {//a
			cnt++;
			d = way(d);
			visited[nx][ny] = true;
			q.push(make_pair(nx, ny));
			spin = 0;
			q.pop();
			continue;
		}
		if ((visited[nx][ny] || arr[nx][ny] == 1 || nx < 0 || ny < 0 || nx >= n || ny >= m) && spin < 4) {//b
			d = way(d);
			spin++;
			continue;
		}
		if (spin == 4) {//c
			int temp = way(d);
			temp = way(temp);
			nx = x + dx[temp];
			ny = y + dy[temp];
			if (arr[nx][ny] == 1) return;
			q.push(make_pair(nx, ny));
			spin = 0;
			q.pop();
			continue;
		}
	}
}
int main(void) {
	cin >> n >> m;
	cin >> r >> c >> d;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}
	BFS();
	cout << cnt << endl;
}
