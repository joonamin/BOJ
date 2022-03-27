#include <bits/stdc++.h>
using namespace std;
vector<int> row(10, 0);
vector<int> cpy_row(10);
const int dir[5][2] = {{-1,0},{1,0},{0,-1},{0,1},{0,0}};

long long answer = 1e9;
void push(int r, int c){
    int nr, nc;
    for(int i=0; i<5; i++){
        nr = r + dir[i][0];
        nc = c + dir[i][1];
        if( nr < 0 || nc < 0 || nr >= 10 || nc >= 10 )
            continue;
        // 비트 플립
        row[nr] ^= ( 1 << (10-(nc+1)) );
    }
}
void DFS(int c, int cnt){
    if( c == 10 ){
        cpy_row = row;
        long long t = cnt ;
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if( row[i-1] & ( 1 << (10-j-1)) ) {
                    push(i, j);
                    t += 1;
                }
            }
        }
        bool isAllOff = true;
        for(int i=0; i<10; i++){
            if( row[i] != 0 )
                isAllOff = false;
        }
        if( isAllOff )
            answer = min(answer, t);

        row = cpy_row;
        return;
    }
    DFS(c+1, cnt);
    push(0, c);
    DFS(c+1, cnt + 1);
    push(0, c);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    for(int i=0; i<10; i++){
        string str;
        cin >> str;
        for(int j=0; j<10; j++){
            int val = ( str[j] == 'O' ) ? 1 : 0;
            row[i] |= ( val << (10-(j+1)) );
        }
    }

    DFS(0,0);

    if( answer == 1e9 )
        cout << -1 ;
    else
        cout << answer ;

    return 0;
}
