#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;



int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int N;
    cin >> N;

    vector<vector<char>> board(N, vector<char>(N));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }

    pair<int, int> heart;
    const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            bool isHeart = true;
            for (int d = 0; d < 4; d++) {
                int y = i + dir[d][0];
                int x = j + dir[d][1];
                isHeart &= (0 <= y && y < N && 0 <= x && x < N && board[y][x] == '*');
            }
            if (isHeart) {
                heart = {i, j};
            }
        }
    }

    // 심장을 기준으로 신체위치 찾기
    cout << heart.first + 1 << " " << heart.second + 1 << '\n';

    // 왼쪽 팔
    int count  = 0;
    for (int j = heart.second - 1; j >= 0 && board[heart.first][j] == '*'; j--) {
        count++;
    }
    cout << count << ' ';
    // 오른쪽 팔
    count = 0;
    for (int j = heart.second + 1; j < N && board[heart.first][j] == '*'; j++) {
        count++;
    }
    cout << count << ' ';
    // 허리
    count = 0;
    for (int i = heart.first + 1; i < N && board[i][heart.second] == '*'; i++) {
        count++;
    }
    cout << count << ' ';

    pair<int, int> left_offset = {heart.first + 1 + count, heart.second - 1},
            right_offset = {heart.first + 1 + count, heart.second + 1};
    // 왼쪽 다리
    count = 0;
    for (int i = left_offset.first; i < N && board[i][left_offset.second] == '*'; i++) {
        count++;
    }
    cout << count << ' ';
    // 오른쪽 다리
    count = 0;
    for (int i = right_offset.first; i < N && board[i][right_offset.second] == '*'; i++) {
        count++;
    }
    cout << count;
    return 0;
}
