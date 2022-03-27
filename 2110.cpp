#include <bits/stdc++.h>
using namespace std;
int N,C;
vector<int> arr;
int installRouter( int max_dist ){
    // 최대 거리가 max_dist 가 되게 라우터를 깔 때 깔 수 있는 라우터의 수
    int router_cnt = 1;
    int prev = arr[0];

    for(int i=1; i<N; i++){
        if( max_dist <= arr[i] - prev ) {
            router_cnt += 1;
            prev = arr[i];
        }
    }
    return router_cnt;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> C;

    arr.resize(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    sort( arr.begin(), arr.end() );

    int st = 1, end = arr[ arr.size() - 1 ] - arr[0];
    int ans = -1;
    while( st <= end ){
        int mid = (st+end)/2;
        int router_cnt = installRouter(mid);
        if( router_cnt < C )
            end = mid - 1;
        else{
            ans = mid;
            st = mid + 1;
        }
    }
    cout << ans;

    return 0;
}