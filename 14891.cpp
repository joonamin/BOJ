#include <bits/stdc++.h>
using namespace std;
string wheel[5] ;
bool is_move[5][5] = {false, }; // is_move[i][j] : i번쨰 바퀴가 움직 였을 때, j번째 바퀴가 영향을 받는가?
// 연결 관계를 그래프로 표현한 것이다.
void update(){
    // 변화되는 관계를 표현하는 배열 업데이트
    memset(is_move, false, sizeof(is_move));
    for(int i=1; i<=4; i++){
        int left = i - 1, right = i + 1;
        if( left >= 1 && wheel[left][2] != wheel[i][6] )
            is_move[i][left] = true;
        if( right <= 4 && wheel[right][6] != wheel[i][2] )
            is_move[i][right] = true;
    }
}
void rotate(int pos, int dir){
    if( pos <= 0 || pos > 4 )
        return ;

    if( dir == 1 ){
        // 시계 방향 회전
        char tmp = wheel[pos][7];
        for(int i=6; i>=0; i--){
            wheel[pos][i+1] = wheel[pos][i];
        }
        wheel[pos][0] = tmp;
    }
    else{
        char tmp = wheel[pos][0];
        for(int i=1; i<=7; i++){
            wheel[pos][i-1] = wheel[pos][i];
        }
        wheel[pos][7] = tmp;
    }
    for(int i=1; i<=4; i++){
        if( is_move[pos][i] ){
            is_move[pos][i] = false;
            is_move[i][pos] = false;
            rotate(i, -1 * dir);
        }
    }
}

int main(void){
    for(int i=1; i<=4; i++)
        cin >> wheel[i];

    int K;
    cin >> K;
    while(K--){
        int to_move, direction;
        cin >> to_move >> direction;
        update();
        // 회전 진행
        rotate(to_move,direction);
    }
    int answer = 0;
    for(int i=1; i<=4; i++){
        if( wheel[i][0] == '1' )
            answer += pow(2,i-1);
    }
    cout << answer;
    return 0;
}