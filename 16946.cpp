#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
int N, M;
char arr[1001][1001];
int group[1001][1001]; // 같은 그룹에 있는 것은 같은 숫자를 가진다.
int cnt_per_group[1000001] = {0, };
int answer[1001][1001];
void make_group(int sy, int sx, int GID){
    int cnt = 1;
    queue<pair<int,int>> Q;
    group[sy][sx] = GID;
    Q.push({sy,sx});

    while(!Q.empty()){
        auto fr = Q.front(); Q.pop();
        int y = fr.first, x = fr.second;
        for(int i=0; i<4; i++){
            int ny =  y + dir[i][0], nx = x + dir[i][1] ;
            if( ny < 0 || nx < 0 || ny >= N || nx >= M )
                continue;
            if( group[ny][nx] != 0 || arr[ny][nx] != '0' )
                continue;
            group[ny][nx] = GID;
            cnt += 1;
            Q.push({ny,nx});
        }
    }
    cnt_per_group[GID] = cnt % 10 ;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> arr[i][j];
    }

    // Grouping With adjacent-zeros
    int GID = 1;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( arr[i][j] == '0' && group[i][j] == 0 ){
                make_group(i,j,GID++);
            }
        }
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( arr[i][j] == '1' ){
                set<int> search_group;
                for(int k=0; k<4; k++){
                    int ny = i + dir[k][0], nx = j + dir[k][1];
                    if( 0 <= ny && ny < N && 0 <= nx && nx < M && arr[ny][nx] == '0' ) {
                        search_group.insert(group[ny][nx]);
                    }
                }
                for(auto item : search_group){
                    answer[i][j] += cnt_per_group[item];
                }
                answer[i][j] += 1;
                answer[i][j] %= 10;
            }
        }
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cout << answer[i][j];
        cout << '\n';
    }

    return 0;
}