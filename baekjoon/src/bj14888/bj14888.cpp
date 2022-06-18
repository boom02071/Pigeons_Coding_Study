#include<iostream>
using namespace std;

int n;
int arr[12];
int oper[12];
int maxResult = -1000000000;
int minResult = 1000000000;

void calculate(int result, int plus, int minus, int multy, int div, int index) {
	if (index == n) {
		if (maxResult < result) maxResult = result;
		if (minResult > result) minResult = result;
		return;
	}
	if (plus > 0)
		calculate(result + arr[index], plus-1, minus, multy, div, index + 1);
	if (minus > 0)
		calculate(result - arr[index], plus, minus - 1, multy, div, index + 1);
	if (multy > 0)
		calculate(result * arr[index], plus, minus, multy - 1, div, index + 1);
	if (div > 0)
		calculate(result / arr[index], plus, minus, multy, div - 1, index + 1);
}
int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) cin >> arr[i];
	for (int i = 0; i < n; i++) cin >> oper[i];

	calculate(arr[0], oper[0], oper[1], oper[2], oper[3], 1);

	cout << maxResult << endl << minResult << endl;

}