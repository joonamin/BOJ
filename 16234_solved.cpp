#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
int N, L, R;
int country[51][51];
int league[51][51];

bool visited[51][51] = {false, };
void open_border(int y, int x, int k){
    for(int i=0; i<4; i++){
        int ny = y + dir[i][0];
        int nx = x + dir[i][1];
        if( ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] ) continue;
        int diff = abs( country[y][x] - country[ny][nx] );
        if( diff < L || diff > R ) continue;

        visited[ny][nx] = true;
        league[ny][nx] = k;
        open_border(ny,nx,k);
    }
}
vector<pair<int,int>> v;
int league_num, cnt, sum ;
void DFS(int y, int x, int league_num){
    for(int i=0; i<4; i++){
        int ny = y + dir[i][0];
        int nx = x + dir[i][1];
        if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
        if( league[ny][nx] == -1 ||  league_num != league[ny][nx] ) continue;
        cnt += 1;
        sum += country[ny][nx];
        league[ny][nx] = -1;
        v.push_back({ny,nx});
        // test
        //cout << "cnt : " << cnt << ", sum : " << sum << ", league_num = " << league_num << '\n';
        //
        DFS(ny,nx,league_num);
    }
}
bool move_population(int y, int x){
    // (y,x)기준으로 DFS를 수행하여 해당 연합 의 총 인구 수와 연합을 이루는 나라의 개수를 구한다.
    // 연합을 이루는 나라의 개수가 1개일 경우, 의미가 없으므로 false 를 리턴
    cnt = 1, sum = country[y][x], league_num = league[y][x];
    v.clear();
    if( league_num != -1 ) {
        v.push_back({y,x});
        league[y][x] = -1;
        DFS(y, x, league_num);
    }

    if( cnt == 1 ) return false;

    int population_per_cell = sum / cnt;
    for( auto it : v ){
        country[it.first][it.second] = population_per_cell;
    }

    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> L >> R;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> country[i][j];
    }

    int answer = 0;
    while(true){
        memset(visited, false, sizeof(visited));
        memset(league, -1, sizeof(league));
        // 국경선 열기
        int k = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( !visited[i][j] ){
                    visited[i][j] = true;
                    league[i][j] = k;
                    open_border(i,j,k++);
                }
            }
        }

        // 인구 이동
        // 모든 셀들에 대해 move_population을 수행하였을 때, 모두 false이면 break;
        bool canContinue = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( league[i][j] == -1 ) continue;
                if( move_population(i,j) == true )
                    canContinue = true;
            }
        }
        if( !canContinue ) break;

        answer += 1;
    }
    cout << answer ;
    return 0;
}