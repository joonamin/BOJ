#define OPT_NUMBER 0
#define OPT_OPERATOR 1
#include <bits/stdc++.h>
using namespace std;
int N, M;
char board[3][3] ;

bool visited[3][3];
vector<pair<int,int>> v; // 경로를 담을 임시 변수
vector<pair<int,int>> answer_path;
int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};

string expression;
int calculate( const string& exp ){
    int ret = exp[0] - '0' ;
    for(int i=1; i<exp.size()-1; i++){
        char op = exp[i];
        int factor = exp[i+1] - '0';
        if( op == '+' ) ret += factor;
        else if( op == '-' ) ret -= factor;
    }
    return ret;
}
void DFS(int y, int x, int surplus,  bool option){
    if( surplus == 0 ){
        int sum = calculate(expression);
        if( sum == N ){
            answer_path = v;
        }
        return ;
    }

    for(int i=0; i<4; i++){
        int ny = y + dir[i][0], nx = x + dir[i][1];
        if( ny < 0 || nx < 0 || ny >= 3 || nx >= 3 || visited[ny][nx] ) continue;
        if( option == OPT_NUMBER ){
            if( '1' <= board[ny][nx] && board[ny][nx] <= '9' ){
                v.push_back({ny, nx});
                expression.push_back(board[ny][nx]);
                visited[ny][nx] = true;
                DFS(ny, nx, surplus-1, !option);
                visited[ny][nx] = false;
                expression.pop_back();
                v.pop_back();
            }
        }
        else{
            if( board[ny][nx] == '+' || board[ny][nx] == '-' ){
                v.push_back({ny,nx});
                expression.push_back(board[ny][nx]);
                visited[ny][nx] = true;
                DFS(ny, nx, surplus, !option);
                visited[ny][nx] = false;
                expression.pop_back();
                v.pop_back();
            }
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin >> N >> M;

    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++)
            cin >> board[i][j];
    }

    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            if( board[i][j] <= '0' || board[i][j] > '9' ) continue;
            v.push_back({i,j});
            expression.push_back(board[i][j]);
            visited[i][j] = true;

            DFS(i,j, M-1, OPT_OPERATOR);

            visited[i][j] = false;
            expression.pop_back();
            v.pop_back();
        }
    }

    if( answer_path.empty() ) cout << 0 ;
    else {
        cout << 1 << '\n';
        for (auto elem : answer_path)
            cout << elem.first << ' ' << elem.second << '\n';
    }

    return 0;
}
