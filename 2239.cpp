#include <bits/stdc++.h>
using namespace std;
char board[9][9];
bool canInsertKey(pair<int,int> pos, char key){
    for(int i=0; i<9; i++){
        if( board[pos.first][i] == key )
            return false;
    }
    for(int i=0; i<9; i++){
        if( board[i][pos.second] == key )
            return false;
    }
    int sy = ( pos.first / 3 ) * 3;
    int sx = ( pos.second / 3 ) * 3;
    for(int i = sy ; i < sy + 3 ; i++){
        for(int j = sx ; j < sx + 3; j++){
            if( board[i][j] == key )
                return false;
        }
    }
    return true;
}
vector<pair<int,int>> p;
void backTracking(int idx){
    if(idx == p.size()){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++)
                cout << board[i][j];
            cout << '\n';
        }
        exit(0);
    }

    pair<int,int> pos = p[idx];
    for( char key = '1' ; key <= '9'; key++ ){
        if( !canInsertKey(pos, key) ) continue;
        board[pos.first][pos.second] = key;
        backTracking(idx+1);
        board[pos.first][pos.second] = '0';
    }

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr), cout.tie(nullptr);

    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++) {
            cin >> board[i][j];
            if( board[i][j] == '0' ){
                p.push_back({i,j});
            }
        }
    }

    // 0이 있는 곳을 채우고 본다.
    // 가능한 경우가 없을 경우, 숫자를 바꿔가며 시도한다.
    backTracking(0);


    return 0;
}