#define tp tuple<int,int,int,int>
#include <bits/stdc++.h>
using namespace std;
int N, M, D;
int board[16][16];
bool selected[16] = {false, };
int answer = -1;
vector<pair<int,int>> archer ;
int simulate(void){
    int ret = 0 ;

    vector<pair<int,int>> monster;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( board[i][j] == 1 )
                monster.push_back({i,j});
        }
    }
    vector<bool> erased(monster.size(), false);
    priority_queue<tp, vector<tp>,greater<tp>> PQ[3]; // 3명의 사수로 부터의 거리, 몬스터의 x좌표, 몬스터의 y좌표, monster 인덱스
    for(int i=0; i<monster.size(); i++){
        for(int j=0; j<3; j++){
            int d = abs(monster[i].first - archer[j].first) + abs(monster[i].second - archer[j].second);
            PQ[j].push({d, monster[i].second, monster[i].first, i});
        }
    }

    while( !PQ[0].empty() || !PQ[1].empty() || !PQ[2].empty() ){
        int d[3], x[3], y[3], idx[3];
        for(int i=0; i<3; i++){
            if( PQ[i].empty() ) continue;
            d[i] = get<0>(PQ[i].top());
            x[i] = get<1>(PQ[i].top());
            y[i] = get<2>(PQ[i].top());
            idx[i] = get<3>(PQ[i].top());
            PQ[i].pop();
        }

        for(int i=0; i<3; i++){
            if( idx[i] == -1 || erased[idx[i]] ) continue;
            // d가 D이하일때 제외 됨
            if( d[i] <= D ) {
                erased[idx[i]] = true;
                ret += 1;
            }

            else{
                // Queue에 다시 넣는다.
                // movement 가 반영됨 .
                int ny = y[i] + 1;
                int nd = abs(archer[i].first - y[i] - 1) + abs(archer[i].second - x[i]);
                if( ny >= N || erased[idx[i]]) {
                    continue;
                }

                PQ[i].push({nd, x[i], ny, idx[i]});
            }
        }

    }
    return ret ;
}
void allocate(int rest){
    if( rest == 0 ){
        int tmp = simulate();
        if( answer < tmp ) answer = tmp ;
        return;
    }
    for(int i=0; i<M; i++){
        if( !selected[i] ){
            selected[i] = true;
            archer.push_back({N, i});
            allocate(rest-1);
            archer.pop_back();
            selected[i] = false;
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M >> D;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    allocate(3);
    cout << answer ;
    return 0;
}
