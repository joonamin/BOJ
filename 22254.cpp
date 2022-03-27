#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll N, X;
vector<ll> gifts;
ll findMinTime(int factoryCount){
    // {해당 공정의 사용 시간, 몇번째? 공정}
    priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<>> PQ;
    for(int i=1; i<=factoryCount; i++){
        PQ.push({0, i});
    }
    // 마지막 사용 시간은? -> PQ에서 마지막 작업까지 끝났을 때 max
    for(auto g : gifts){
        auto top = PQ.top(); PQ.pop();
        PQ.push({top.first + g, top.second});
    }
    ll ret = -1e9;
    while(!PQ.empty()) {
        ret = max(ret, PQ.top().first);
        PQ.pop();
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    cin >> N >> X;
    gifts.resize(N);
    for(int i=0; i<N; i++)
        cin >> gifts[i];

    int l = 1, r = N;
    int ans;
    while(l <= r){
        int mid = (l+r)/2;
        // mid 개의 공장으로 주어진 일들을 마무리하는데 걸리는 시간
        // 시간이 더 짧으면 mid를 줄여본다.
        if(findMinTime(mid) <= X){
            ans = mid;
            r = mid -1;
        }else{
            l = mid + 1;
        }

    }
    cout << ans << '\n';
    return 0;
}