#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}}; // 상, 하, 좌, 우
bool input[11][11];
int board[11][11];
int paper_cnt[6] = {0,5,5,5,5,5};
int answer = -1;

void DFS(int cnt){
    // 최대 board[y][x] 크기의 종이로 커버할 수 있음.
    // 커버할 경우 해당 범위의 숫자들은 모두 0으로 초기화 됨.
    bool isEnd = true;
    // size까지로 덮을 수 있음.
    int y = -1,x = -1;
    for(int i=0; i<10; i++){
        for(int j=0; j<10; j++){
            if( board[i][j] > 0 ){
                y = i, x = j;
                isEnd = false;
                break;
            }
        }
        if( !isEnd ) break;
    }
    if( isEnd ){
        if( answer == -1 || answer > cnt )
            answer = cnt;
    }

    int size = board[y][x];
    size = min(5, size);
    int cpy_board[11][11];
    for(int sz = 1; sz <= size; sz++){
        if( paper_cnt[sz] == 0 ) continue;

        paper_cnt[sz] -= 1;
        memcpy(cpy_board, board, sizeof(board));
        // 해당 영역을 덮는다.
        bool canCover = true;
        for(int i=y; i<y+sz; i++){
            for(int j=x; j<x+sz; j++){
                if( board[i][j] == 0 ){
                    canCover = false;
                    break;
                }
                board[i][j] = 0;
            }
            if( !canCover ) break;
        }
        if( !canCover ) {
            paper_cnt[sz] += 1;
            memcpy(board, cpy_board, sizeof(cpy_board));
            continue;
        }
        DFS(cnt+1);
        paper_cnt[sz] += 1;
        memcpy(board, cpy_board, sizeof(cpy_board));
    }

}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    for(int i=0; i<10; i++){
        for(int j=0; j<10; j++)
            cin >> input[i][j];
    }

    // {i,j}를 왼쪽 상단으로 하는 사각형 중 가장 큰 사각형의 사이즈를 board에 저장
    for(int i=0; i<10; i++){
        for(int j=0; j<10; j++){
            if( input[i][j] == true ){
                pair<int,int> left_top = {i, j};
                int size = 1;
                while(size++){
                    bool flag = true;
                    for(int k = left_top.first; k < left_top.first + size; k++){
                        for(int z = left_top.second; z < left_top.second + size; z++){
                            if( !input[k][z]  ){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if( !flag ){
                        board[i][j] = size - 1;
                        break;
                    }
                }
            }
        }
    }

    // 자 이제 이것을 채워보자.
    DFS(0);
    cout << answer << '\n';
    return 0;
}