#include <bits/stdc++.h>
using namespace std;
enum{ EAST=1, WEST=2, NORTH=3, SOUTH=4 };
int N,M,K;
// [0] : 위, [1] : 왼쪽, [2] : 뒤, [3] : 오른쪽, [4] : 앞, [5]: 아래
int dice[6] = {0,};
pair<int,int> dicePos;
int board[21][21];
bool moveDice( int cmd ){
    int ny = dicePos.first, nx = dicePos.second;
    if( cmd == EAST )
        nx += 1;
    else if( cmd == WEST )
        nx -= 1;
    else if( cmd == NORTH )
        ny -= 1;
    else if( cmd == SOUTH )
        ny += 1;

    if( nx < 0 || ny < 0 || ny >= N || nx >= M  ) return false;
    dicePos.first = ny;
    dicePos.second = nx;
    return true;
}
void rotateDice( int cmd ){
    int eastOrWestIdx[4] = {5,1,0,3}; // 바닥, 왼, 위, 오
    int southOrNorthIdx[4] = {5,4,0,2}; // 바닥, 앞, 위, 뒤
    if( cmd == EAST ){
        int temp = dice[eastOrWestIdx[3]];
        for(int i=2; i>=0; i--){
            dice[eastOrWestIdx[i+1]] = dice[eastOrWestIdx[i]];
        }
        dice[eastOrWestIdx[0]] = temp;
    }
    else if( cmd == WEST ){
        int temp = dice[eastOrWestIdx[0]];
        for(int i=1; i<4; i++){
            dice[eastOrWestIdx[i-1]] = dice[eastOrWestIdx[i]];
        }
        dice[eastOrWestIdx[3]] = temp;
    }
    else if( cmd == NORTH ){
        int temp = dice[southOrNorthIdx[3]];
        for(int i=2; i>=0; i--){
            dice[southOrNorthIdx[i+1]] = dice[southOrNorthIdx[i]];
        }
        dice[southOrNorthIdx[0]] = temp;
    }
    else if( cmd == SOUTH ){
        int temp = dice[southOrNorthIdx[0]];
        for(int i=1; i<4; i++){
            dice[southOrNorthIdx[i-1]] = dice[southOrNorthIdx[i]];
        }
        dice[southOrNorthIdx[3]] = temp;
    }
    /*
     * 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
     * 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
     */
    if( board[dicePos.first][dicePos.second] == 0 ){
        board[dicePos.first][dicePos.second] = dice[5];
    }else{
        dice[5] = board[dicePos.first][dicePos.second];
        board[dicePos.first][dicePos.second] = 0;
    }
    cout << dice[0] << '\n';
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M >> dicePos.first >> dicePos.second >> K;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }
    for(int i=0; i<K; i++){
        int cmd ;
        cin >> cmd;
        if( moveDice(cmd) ) rotateDice(cmd);
    }

    return 0;
}