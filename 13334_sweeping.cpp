#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int n, d;
vector<pair<int,int>> v;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> n;
    v.resize(n);

    // pq 에는 항상 현재 선분에 포함되어 있는 원소들을 유지한다.
    // 또한 PQ에는 동일한 원소가 다시 들어가지 않는다.
    // 그렇게 될 경우, 조금의 adjusing 을 통하여 nlogn 복잡도로 구현가능하다.

    for(int i=0; i<n; i++){
        cin >> v[i].first >> v[i].second;
        if( v[i].first > v[i].second )
            swap(v[i].first, v[i].second);
    }

    cin >> d;
    int ans = 0;
    sort(v.begin(), v.end(), [](const auto& a, const auto& b)->bool {
       if( a.second == b.second )
           return a.first < b.first;
       return a.second < b.second;
    });

    priority_queue<int,vector<int>,greater<>> PQ;
    for(int i=0; i<n; i++){
        if( v[i].second - v[i].first > d ) continue;
        PQ.push(v[i].first);
        // 현재 라인은 [v[i].second-d, v[i].second]
        while(!PQ.empty() && PQ.top() < v[i].second - d) PQ.pop();
        ans = max<int>(ans, PQ.size());
    }


    cout << ans ;

    return 0;
}
