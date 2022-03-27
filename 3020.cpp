#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    int N, H;
    cin >> N >> H;

    // odd : 최저점, even : 최고점
    vector<int> odd(N/2), even(N/2);
    for(int i=0; i<N; i++){
        if( i & 1 ){
            cin >> odd[i/2];
            odd[i/2] = H - odd[i/2];
        }else{
            cin >> even[i/2];
        }
    }

    sort(odd.begin(), odd.end());
    sort(even.begin(), even.end());

    // [h, h+1) 구간에서 자를 수 있는 장애물의 최솟값
    int oddCnt[H], evenCnt[H], totalCnt[H];
    for(int h=0; h<H; h++){
        // even[i] 가 [h, h+1) 보다 크거나 같은 것의 개수 + odd[i] 가 [h, h+1) 보다 작거나 같은 것의 개수
        evenCnt[h] = N/2 - (upper_bound(even.begin(), even.end(), h-1) - even.begin());
        oddCnt[h] = lower_bound(odd.begin(), odd.end(), h) - odd.begin();

        totalCnt[h] = evenCnt[h] + oddCnt[h];
    }

    int minVal = 1e9, count = 0;
    for(const auto& item : totalCnt)
        minVal = min(minVal, item);
    for(const auto& item : totalCnt)
        count += (item == minVal);

    cout << minVal << ' ' << count ;
    return 0;
}