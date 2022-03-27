#include <bits/stdc++.h>
using namespace std;
enum{ HORIZONAL = 0, CROSS = 1, VERTICAL = 2 };
struct Pipe{
    pair<int,int> fr,bk;
    int currentDirType = HORIZONAL;
    Pipe(){
        fr = {0,1};
        bk = {0,0};
    }
};
int n;
int board[17][17];
Pipe movePipe(Pipe pip, int direction){
    if( direction == HORIZONAL ){
        pip.bk = pip.fr;
        pip.fr.second += 1;
    }
    else if( direction == CROSS ){
        pip.bk = pip.fr;
        pip.fr.first += 1;
        pip.fr.second += 1;
    }
    else{
        pip.bk = pip.fr;
        pip.fr.first += 1;
    }
    return pip;
}
bool check(Pipe p, int prev_dir){
    // 이전에 옮긴 명령이 정당한가?
    if( p.fr.first >= n || p.fr.second >= n || p.fr.first < 0 || p.fr.second < 0)
        return false;
    switch(prev_dir){
        case HORIZONAL:
            if( board[p.fr.first][p.fr.second] == 1 ) return false;
            break;
        case VERTICAL:
            if( board[p.fr.first][p.fr.second] == 1 ) return false;
            break;
        case CROSS:
            if( board[p.fr.first][p.fr.second] == 1 ||
            board[p.fr.first-1][p.fr.second] == 1 ||
            board[p.fr.first][p.fr.second-1] == 1 )
                return false;
            break;
    }
    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> board[i][j];
        }
    }

    Pipe pipe;
    queue<Pipe> Q;
    Q.push(pipe);
    int answer = 0;
    while(!Q.empty()){
        Pipe E = Q.front(); Q.pop();
        if( E.fr.first == n-1 && E.fr.second == n-1 ){
             answer += 1;
            continue;
        }

        if( E.currentDirType == HORIZONAL ){
            Pipe next1 = movePipe(E, HORIZONAL);
            Pipe next2 = movePipe(E, CROSS);
            next2.currentDirType = CROSS;
            if( check(next1, HORIZONAL) )
                Q.push(next1);
            if( check(next2, CROSS) )
                Q.push(next2);
        }
        else if( E.currentDirType == VERTICAL ){
            Pipe next1 = movePipe(E, VERTICAL);
            Pipe next2 = movePipe(E, CROSS);
            next2.currentDirType = CROSS;
            if( check(next1, VERTICAL) )
                Q.push(next1);
            if( check(next2, CROSS) )
                Q.push(next2);
        }
        else{
            // cross line
            Pipe next1 = movePipe(E, HORIZONAL);
            next1.currentDirType = HORIZONAL;
            Pipe next2 = movePipe(E, VERTICAL);
            next2.currentDirType = VERTICAL;
            Pipe next3 = movePipe(E, CROSS);
            next3.currentDirType = CROSS;

            if( check(next1, HORIZONAL) )
                Q.push(next1);
            if( check(next2, VERTICAL) )
                Q.push(next2);
            if( check(next3, CROSS) )
                Q.push(next3);
        }

    }
    cout << answer ;
    return 0;
}