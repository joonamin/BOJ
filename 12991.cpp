#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll N, K;
vector<ll> A, B;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> K;
    A.resize(N), B.resize(N);
    for(auto &item : A)
        cin >> item;
    for(auto &item : B)
        cin >> item;

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());

    ll l = A[0] * B[0], r = A[N-1] * B[N-1];
    ll ans;
    while(l <= r){
        ll m = (l + r) / 2;
        // m 보다 작거나 같은 원소의 개수 카운트
        // f(m) : m보다 작거나 같은 원소가 k개 이상인가? (true/false)
        int cnt = 0;
        for(int i=0; i<N; i++){
            cnt += upper_bound(B.begin(), B.end(), m / A[i]) - B.begin();
        }
        if(cnt >= K){
            ans = m;
            r = m - 1;
        }else{
            l = m + 1;
        }
    }
    cout << ans ;
    return 0;
}
