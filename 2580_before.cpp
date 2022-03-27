#include <bits/stdc++.h>

using namespace std;
int board[9][9];
void test(){
    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++)
            cout << board[i][j] << ' ';
        cout << '\n';
    }
}
vector<pair<int,int>> v;
vector<bool> getMask(int y, int x){
    vector<bool> m(10,false) ;
    for(int i=0; i<9; i++){
        int val = board[i][x];
        m[val] = true;
    }
    for(int i=0; i<9; i++){
        int val = board[y][i];
        m[val] = true;
    }
    int cy = y / 3, cx = x / 3;
    cy *= 3, cx *= 3;

    for(int i = cy ; i < cy + 3 ; i++){
        for(int j = cx ; j < cx + 3; j++){
            int val = board[i][j];
            m[val] = true;
        }
    }
    return m;
}
bool BT( int nth ){
    if( nth >= v.size() ){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if( board[i][j] == 0 )
                    return false;
            }
        }
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++)
                cout << board[i][j] << ' ';
            cout << '\n';
        }
        exit(0);
    }
    pair<int,int> cur = v[nth];
    int y = cur.first, x = cur.second;

    // mask 확인, 현재 넣을 수 있는 경우만 넣고 backtracking 시작.
    vector<bool> mask = getMask(y,x);
    bool isPossible = true;
    for(int i=1; i<=9; i++){
        if( !mask[i] ){
            board[y][x] = i;
            isPossible = BT(nth+1);
            board[y][x] = 0;
        }
    }
    return isPossible;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++) {
            cin >> board[i][j];
            if( board[i][j] == 0 )
                v.push_back({i,j});
        }
    }

    BT(0);
    return 0;
}