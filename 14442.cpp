#include <bits/stdc++.h>
using namespace std;

int N,M,K;
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
bool visited[11][1001][1001] ={false, };
char board[1001][1001];
int BFS(){
    queue< tuple<int, int, int, int> > Q;
    for(int i=0; i<11; i++){
        visited[i][1][1] = true;
    }
    Q.push({1,1, K,1});

    while(!Q.empty()){
        tuple<int,int,int,int> tup = Q.front(); Q.pop();
        int y = get<0>(tup), x = get<1>(tup), canBreak = get<2>(tup);
        int distance = get<3>(tup);

        if( y == N && x == M ) return distance;

        for(int i=0; i<4; i++){
            int ny = y + dir[i][0], nx = x + dir[i][1];
            if( ny <= 0 || nx <= 0 || ny > N || nx > M ) continue;

            if( board[ny][nx] == '0' && !visited[canBreak][ny][nx]){
                visited[canBreak][ny][nx] = true;
                Q.push({ny,nx,canBreak,distance+1});
            }
            if( board[ny][nx] == '1' && canBreak > 0 && !visited[canBreak-1][ny][nx] ){
                visited[canBreak-1][ny][nx] = true;
                Q.push({ny,nx,canBreak-1,distance+1});
            }
        }
    }
    return -1;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M >> K;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++)
            cin >> board[i][j];
    }

    int answer = BFS();
    cout << answer ;

    return 0;
}