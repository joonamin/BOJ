#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    vector<int> v[21];

    int N, K;
    cin >> N >> K;
    for(int i=0; i<N; i++){
        string in;
        cin >> in;
        v[in.size()].push_back(i);
    }
    // 각각의 v[i] 에는 i길이의 문자열이 모두 들어가게 됨
    long long ans = 0;
    for(int i=2; i<=20; i++){
        for(int j=0; j<v[i].size(); j++){
            int rnk = v[i][j];
            int lastGoodFriends = upper_bound(v[i].begin(), v[i].end(), K+rnk) - v[i].begin();
            int sz = lastGoodFriends - j <= 0 ? 1 : lastGoodFriends - j;
            ans += sz-1;
        }
    }
    cout << ans ;
    // O(19 * NlogN)
    return 0;
}
