#include <bits/stdc++.h>
using namespace std;

long long findIdx(long long size, long long value){
    // Idx는 1부터 시작
    long long idx = 1;
    for(int i=1; i<=size; i++){
        idx += min( size, value/i );
    }
    return idx - 1; // 자기 자신 제외
}

int main(void){
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);
	// algorithm

	long long N, K;
	cin >> N >> K;

	long long left = 1, right = N*N;

	long long ans = 1e9;
	// f(x) : x이상의 수가 나오는 첫 번째 인덱스(최소 인덱스), 단조함수 만족
	while( left <= right ){
	    long long mid = (left+ right)/2;
	    long long firstIdx = findIdx(N, mid);

	    if( firstIdx == K ) {
            ans = mid;
            break;
        }
	    if( firstIdx > K ){
	        right = mid - 1;
	    }
        else
            left = mid + 1;

	}
    cout << ans;
    return 0;
}
