#include <bits/stdc++.h>
using namespace std;
int N; // 이닝 수
int board[50][9];
int order[9]; // order[i] : i번째 선수의 타순

int answer;
int game_play(){
    int score = 0 ;
    // 초기화
    int ordered_board[50][9];
    for(int i=0; i<N; i++){
        for(int j=0; j<9; j++){
           ordered_board[i][order[j]] = board[i][j];
        }
    }

    int base[3] = {-1,-1,-1}; // i번째 루수에 있는 주자의 번호
    bool is_enroll_at[9] = {false, };
    int out_cnt = 0;

    int current_player = 0; // 1번 타자 부터 시작
    int current_round = 0;
    while(current_round < N){

        // 아웃
        if(ordered_board[current_round][current_player] == 0){
            out_cnt += 1;
        }
        else if(ordered_board[current_round][current_player] == 1){
            if( base[2] != -1 ){
                score += 1;
                is_enroll_at[base[2]] = false;
            }
            base[2] = base[1];
            base[1] = base[0];
            base[0] = current_player;
            is_enroll_at[base[0]] = true;
        }
        else if(ordered_board[current_round][current_player] == 2){
            // score += ( base[2] + base[1] );
            if( base[2] != -1 ){
                score += 1;
                is_enroll_at[base[2]] = false;
            }
            if( base[1] != -1 ){
                score += 1;
                is_enroll_at[base[1]] = false;
            }
            base[2] = base[1] = -1;
            base[2] = base[0];
            base[0] = -1;
            base[1] = current_player;
            is_enroll_at[base[1]] = true;
        }
        else if(ordered_board[current_round][current_player] == 3){
//            score += ( base[2] + base[1] + base[0] );
            if( base[2] != -1 ){
                score += 1;
                is_enroll_at[base[2]] = false;
            }
            if( base[1] != -1 ){
                score += 1;
                is_enroll_at[base[1]] = false;
            }
            if( base[0] != -1 ){
                score += 1;
                is_enroll_at[base[0]] = false;
            }
            base[2] = base[1] = base[0] = -1;
            base[2] = current_player;
            is_enroll_at[current_player] = true;
        }
        else{
            if( base[2] != -1 ){
                score += 1;
                is_enroll_at[base[2]] = false;
            }
            if( base[1] != -1 ){
                score += 1;
                is_enroll_at[base[1]] = false;
            }
            if( base[0] != -1 ){
                score += 1;
                is_enroll_at[base[0]] = false;
            }
            base[2] = base[1] = base[0] = -1;
            score += 1;
        }

        current_player += 1;
        if( current_player >= 9 )
            current_player = 0;
        while(is_enroll_at[current_player]){
            current_player += 1;
            if( current_player >= 9 )
                current_player = 0;
        }
        if( out_cnt == 3 ){
            current_round += 1;
            out_cnt = 0;
            for(auto& p: base) p = -1;
            for(auto& p: is_enroll_at) p = false;
        }
    }

    return score;
}
// 대략 4만번
void set_order(int rest_player, int idx){
    if( rest_player == 0 ){
        // 게임 시작
        int score = game_play();
        answer = max(score, answer);
        return ;
    }
    if ( idx == 3 ) idx += 1;
    for(int i=0; i<9; i++){
        if( order[i] != -1 ) continue; // 이미 순서가 정해진 사람
        order[i] = idx;
        set_order(rest_player-1, idx+1);
        order[i] = -1;
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N ;
    for(int i=0; i<N; i++){
        for(int j=0; j<9; j++)
            cin >> board[i][j];
    }

    // 1번 선수는 무조건 4번 타자
    fill(order, order+9, -1);
    order[0] = 3;

    set_order(8,0);

    cout << answer;
    return 0;
}
