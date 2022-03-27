#include <bits/stdc++.h>
using namespace std;
const int dir[5][2] = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
int R, C, M;
class Shark{
public:
    int s,d,z; // {r,c} : pos, s : speed, d : direction, z : size
    Shark(int s, int d, int z){
        this->s = s;
        this->d = d;
        this->z = z;
    }
};
vector<Shark> board[105][105];
int answer = 0;
int fisher_man_col = 0;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> R >> C >> M;

    for(int i=0; i<M; i++){
        int r,c,s,d,z;
        cin >> r >> c >> s >> d >> z;
        Shark elem(s,d,z);
        board[r][c].push_back(elem);
    }

    // 낚시왕이 오른쪽으로 한칸 이동
    while(fisher_man_col <= C && M > 0){
        fisher_man_col += 1;
        // 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
        for(int i=1; i<=R; i++){
            if( !board[i][fisher_man_col].empty() ) {
                answer += board[i][fisher_man_col][0].z;
                board[i][fisher_man_col].clear();
                break;
            }
        }
        queue<tuple<pair<int,int>,Shark>> shark_queue; // 나머지 상어들을 옮기고 board 초기화
        vector<Shark> new_board[101][101];
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                if( !board[i][j].empty() ){
                    shark_queue.push({{i,j},Shark(board[i][j][0].s, board[i][j][0].d, board[i][j][0].z)});
                }
            }
        }
        // ny : 2 - 5 = -3 (1) 4 0 + 4
        while(!shark_queue.empty()){
            auto front = shark_queue.front(); shark_queue.pop();
            int y = get<0>(front).first, x = get<0>(front).second;
            Shark info = get<1>(front);
            // 이 상어가 새로운 위치로 움직인다.
            int ny = y + info.s * dir[info.d][0], nx = x + info.s * dir[info.d][1];
            while( ny <= 0 || ny > R || nx <= 0 || nx > C ) {
                if (ny <= 0 || ny > R) {
                    // 위, 아래
                    int diff = (ny > R) ? abs(ny - R) : abs(2 - ny);
                    info.d = (info.d == 1) ? 2 : 1;
                    ny = (ny > R) ? R - diff : diff;
                }
                if (nx <= 0 || nx > C) {
                    // 좌, 우
                    int diff = (nx > C) ? abs(nx - C) : abs(2 - nx);
                    info.d = (info.d == 3) ? 4 : 3;
                    nx = (nx > C) ? C - diff : diff;
                }
            }
            // 새로운 위치에 상어가 들어간다.
            if( new_board[ny][nx].empty() )
                new_board[ny][nx].push_back(Shark(info.s, info.d, info.z));
            else{
                if( new_board[ny][nx][0].z < info.z ){
                    new_board[ny][nx][0] = info;
                }

            }
        }
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++) {
                board[i][j] = new_board[i][j];
//                if( !board[i][j].empty() )
//                    cout << board[i][j][0].z << ' ';
//                else
//                    cout << 0 << ' ';
            }
//            cout << '\n';
        }
//        cout << "===\n";
    }
    cout << answer;
    return 0;
}
