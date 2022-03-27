#include <bits/stdc++.h>
using namespace std;
int N, W, dp[1001][1001];
pair<int, int> event[1001];
int getDist(pair<int,int> a, pair<int,int> b){
    return abs(a.first - b.first) + abs(a.second - b.second);
}
// 각각의 부분 최적해가 전체의 최적해를 보장하지 않으므로
// 뒷부분 부터 탐색한다음 맞는 조각들을 현재 사건 뒤에 붙힌다는 느낌으로 DFS를 수행
int DFS(int car1, int car2){
    // 마지막으로 경찰차1이 맡은 사건 : car1
    // 마지막으로 경찰차2가 맡은 사건 : car2
    // dp[car1][car2] : 현재 사건으로 부터 마지막 사건까지 처리하였을 때, 최소 거리
    int& ret = dp[car1][car2];
    if( car1 == W || car2 == W ){
        return 0;
    }
    if( ret != -1 )
        return ret;

    pair<int,int> pos1, pos2;
    pos1 = (car1 == 0) ? make_pair(1, 1) : event[car1];
    pos2 = (car2 == 0) ? make_pair(N, N) : event[car2];

    int next = max(car1, car2) + 1;
    int d1 = getDist(pos1, event[next]);
    int d2 = getDist(pos2, event[next]);

    ret = min(DFS(next, car2) + d1, DFS(car1, next) + d2);
    return ret;
}
// 앞 서 수행한 DFS를 통해서 각각의 사건은
// d1 + dp[next][car2] 또는 d2 + dp[car1][next] 로 이분화 된다.
// 현재 사건을 경찰차1 이 처리하는 경우가 전자고 경찰차2가 처리하는 경우가 후자가 된다.
void backTrace(int car1, int car2){
    if( car1 == W || car2 == W )
        return;
    pair<int,int> pos1, pos2;
    pos1 = (car1 == 0) ? make_pair(1, 1) : event[car1];
    pos2 = (car2 == 0) ? make_pair(N, N) : event[car2];
    int next = max(car1, car2) + 1;
    int d1 = getDist(pos1, event[next]);
    int d2 = getDist(pos2, event[next]);

    if( d1 + dp[next][car2] <= d2 + dp[car1][next] ) {
        cout << 1 << '\n';
        backTrace(next, car2);
    }
    else {
        cout << 2 << '\n';
        backTrace(car1, next);
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> W;
    for(int i=1; i<=W; i++)
        cin >> event[i].first >> event[i].second;

    memset(dp, -1, sizeof(dp));
    cout << DFS(0, 0) << '\n';

    backTrace(0, 0);

    return 0;
}