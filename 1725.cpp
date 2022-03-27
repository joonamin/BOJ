#include <bits/stdc++.h>
using namespace std;
int N;
vector<int> h;
int solve(const int lB, const int rB){
    if( lB >= rB ) {
        return h[lB];
    }
    int mB = (lB+rB)/2;
    // mB를 기준으로 [l,mB], [mB+1,r] 구간으로 분할하여
    // 각각의 구간에서 최대 넓이의 히스토그램을 구함
    int ret = max( solve(lB, mB), solve(mB+1, rB) );

    // 가장 큰 히스토그램이 중앙에 걸쳐서 존재할 경우도 고려
    int l = mB, r = mB+1;
    // 초기 상태
    int min_h = min( h[l], h[r] );
    ret = max(ret, 2 * min_h );
    while( lB < l || r < rB ){
        // 왼쪽으로 가야하는 경우
        if( lB < l && ( r == rB || h[l-1] > h[r+1]) ){
            min_h = min(min_h, h[l-1]);
            l -= 1;
        }
        else{
            min_h = min(min_h, h[r+1]);
            r += 1;
        }
        ret = max( ret, (r-l+1) * min_h ) ;
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N ;
    h.resize(N);
    for(int i=0; i<N; i++)
        cin >> h[i];

    int ans = solve(0, N-1);
    cout << ans;
    return 0;
}
