#include <bits/stdc++.h>
using namespace std;
int N, M, K, board[51][51], ans = 1e9;
vector<tuple<int,int,int>> info;

bool visited[7] = {false, };
void rotate(int a, int b, int sz){
    // (a,b) 는 정사각형의 최상단의 좌표
    // sz는 정사각형의 크기
    if( sz <= 1 )
        return;
    int temp = board[a][b];
    for(int y = a+1; y < a+sz; y++)
        board[y-1][b] = board[y][b];
    for(int x = b+1; x < b+sz; x++)
        board[a+sz-1][x-1] = board[a+sz-1][x];
    for(int y = a+sz-2; y >= a ; y--)
        board[y+1][b+sz-1] = board[y][b+sz-1];
    for(int x = b+sz-1; x > b+1; x--)
        board[a][x] = board[a][x-1];
    board[a][b+1] = temp;
    rotate(a+1, b+1, sz-2);
}
void DFS(int p){
    if( p == K ){
        // 결과 값 도출
        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=0; j<M; j++){
                sum += board[i][j];
            }
            ans = min(ans, sum);
        }
        return;
    }
    for(int i=0; i<info.size(); i++){
        if(!visited[i] ){
            int r = get<0>(info[i]), c = get<1>(info[i]);
            int s = get<2>(info[i]);

            visited[i] = true;
            int cpy_board[51][51];
            for(int l=0; l<51; l++){
                for(int m=0; m<51; m++) {
                    cpy_board[l][m] = board[l][m];
                }
            }
            // rotate 수행
            rotate(r-s-1, c-s-1, 2*s+1);
            DFS(p+1);
            for(int l=0; l<51; l++){
                for(int m=0; m<51; m++)
                    board[l][m] = cpy_board[l][m];
            }
            visited[i] = false;
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> K;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    for(int i=0; i<K; i++){
        tuple<int,int,int> elem;
        cin >> get<0>(elem) >> get<1>(elem) >> get<2>(elem);
        info.push_back(elem);
    }

    // info ( 0 ~ K-1 )
    // DFS수행, 첫번째로 수행할 작업 ~ .. , K번째로 수행할 작업

    DFS(0);
    cout << ans;
    return 0;
}
