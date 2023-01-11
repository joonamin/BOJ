#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
char board[3][3];
map<string, bool> isValidState;
bool checkState() {
    for (int i = 0; i < 3; i++) {
        if (board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
            return true;
        if (board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
            return true;
    }
    // 대각선 검사
    if (board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
        return true;
    if (board[0][2] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
        return true;
    return false;
}
void dfs(int turn) {
    if (turn == 9 || checkState()) {
        string s;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s.push_back(board[i][j]);
            }
        }
        isValidState[s] = true;
        return;
    }
    char placeholder;
    if (turn & 1) placeholder = 'O';
    else placeholder = 'X';

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == '.') {
                board[i][j] = placeholder;
                dfs(turn + 1);
                board[i][j] = '.';
            }
        }
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
            board[i][j] = '.';
    }
    // 게임이 끝나는 경우?
    // X, O 순서로 둔다
    dfs(0);

    string in;
    while (cin >> in, in != "end") {
        if (isValidState[in])
            cout << "valid\n";
        else
            cout << "invalid\n";
    }

    return 0;
}
