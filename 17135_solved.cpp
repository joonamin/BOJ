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
    int playboard[16][16] ;
    memcpy(playboard, board, sizeof(playboard));

    while(true){
        // 몬스터의 좌표 값들을 받아온다.
        vector<pair<int,int>> monster;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                if(playboard[i][j] == 1){
                    monster.push_back({i,j});
                }
            }
        }
        if( monster.empty() ) break;
        // 궁수들과의거리 계산
        // 궁수와 가장 가까운 몬스터, 여러 마리가 있으면 x좌표가 적은 것으로
        int to_erase_idx[3] ={-1,-1,-1}; // 제거해야하는 몬스터의 인덱스
        int min_dist[3] = {99999, 99999, 99999};
        int min_x[3]={9999,9999,9999};
        for(int i=0; i<3; i++){
            for(int j=0; j<monster.size(); j++){
                int dist = abs(monster[j].first - archer[i].first) + abs(monster[j].second - archer[i].second);
                if( dist > D ) continue;
                if( dist < min_dist[i] ){
                    to_erase_idx[i] = j;
                    min_dist[i] = dist;
                    min_x[i] = monster[j].second;
                }
                else if( dist == min_dist[i] &&  min_x[i] > monster[j].second){
                    to_erase_idx[i] = j;
                    min_x[i] = monster[j].second;
                }
            }
        }

        for(int i=0; i<3; i++){
            if( to_erase_idx[i] == -1 ) continue;
            int idx = to_erase_idx[i];
            if( playboard[monster[idx].first][monster[idx].second] == 1 ) {
                playboard[monster[idx].first][monster[idx].second] = 0;
                ret += 1;
            }
        }

        for(int i=monster.size()-1; i>=0; i--){
            // erase된 것을 제외하고 나머지 몬스터들이 한칸씩 아래로 이동함.
            if( i == to_erase_idx[0] || i == to_erase_idx[1] || i == to_erase_idx[2] )
                continue;
            int ny = monster[i].first + 1;
            if( ny >= N ){
                playboard[monster[i].first][monster[i].second] = 0;
            }else {
                playboard[ny][monster[i].second] = 1;
                playboard[monster[i].first][monster[i].second] = 0;
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
