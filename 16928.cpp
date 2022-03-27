#include <bits/stdc++.h>
using namespace std;
int N, M;
int board[101];
int cnt[101];
struct comp{
    bool operator()(const pair<int,int>& a, const pair<int,int>& b){
        return a.second > b.second;
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    fill(board, board+101, 1e9); // 디폴트 value는 INF다
    fill(cnt, cnt+101, 1e9);
    cin >> N >> M;
    for(int i=0; i<N; i++){
        int x, y;
        cin >> x >> y;
        board[x] = y;
    }
    for(int i=0; i<M; i++){
        int x, y;
        cin >> x >> y;
        board[x] = y;
    }

    long long answer = 0;
    priority_queue<pair<int,int>, vector<pair<int,int>>, comp> Q; // {현재 좌표, Cnt}
    Q.push({1,0});
    while(!Q.empty()){
        auto fr = Q.top(); Q.pop();
        if( fr.first == 100 ){
            answer = fr.second;
            break;
        }
        if( cnt[fr.first] < fr.second )
            continue;

        cnt[fr.first] = fr.second;

        if( board[fr.first] != 1e9 ){
            Q.push({board[fr.first], fr.second});
        }
        else {
            for (int i = 1; i <= 6; i++) {
                int nPos = fr.first + i;
                if (nPos < 1 || nPos > 100)
                    continue;
                Q.push({nPos, fr.second + 1});
            }
        }
    }
    cout << answer;
    return 0;
}