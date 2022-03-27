#include <bits/stdc++.h>
using namespace std;
vector<long long> h;
long long solve(int l, int r){
    if( l == r )
        return h[l];
    int mid = (l+r)/2;
    long long ret = max(solve(l, mid), solve(mid+1, r));

    // 걸쳐있는 직사각형의 넓이 중 가장 큰 것을 찾는다.
    int low = mid, high = mid + 1;
    long long height = min(h[low], h[high]);
    ret = max(ret, height * 2);
    while( l < low || high < r ){
        // 더 큰 높이를 가진 쪽으로 확장한다.
        if( high < r && (low == l || h[low-1]< h[high+1] )){
            high ++ ;
            height = min(height, h[high]);
        }
        else{
            low --;
            height = min(height, h[low]);
        }
        ret = max(ret, height * (high-low+1));
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    // [l,r] 범위의 직사각형의 넓이 : (r-l+1) * min(h[l,r])
    // 가장 큰 직사각형이 속할 수 있는 범위, 기준점 mid를 기준으로 왼쪽, 오른쪽 걸쳐있거나
    // 1. [left, mid] 2. [mid+1, right] 3. [a,b] 단, a <= mid && mid < right
    while(true){
        int n;
        cin >> n ;
        if( n == 0 )
            break;
        h.resize(n);
        for(int i=0; i<n; i++)
            cin >> h[i];
        long long ans = solve(0, n-1);
        cout << ans << '\n';
    }

    return 0;
}
