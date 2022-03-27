#include <bits/stdc++.h>
using namespace std;
int K;
int board[4];
vector<vector<int>> tmp;
void DFS(int cnt){
    if( cnt == 0 ){
        int fill_cnt = 0;
        for(int i=0; i<4; i++){
            if( board[i] >= 1 ){
                fill_cnt += 1;
            }
        }
        if( fill_cnt == 1 ){
            for(int i=0; i<tmp.size(); i++){
                for(auto item : tmp[i])
                    cout << item << ' ';
                cout << '\n';
            }
            exit(0);
        }
        return;
    }

    int select_1, select_2;
    for(int i=0; i<4; i++){
        for(int j=0; j<4; j++){
            if( i != j && board[i] > 0 && board[j] > 0 ){
                select_1 = i;
                select_2 = j;
                // i,j 가 아닌 k, z를 선택하여 그곳에 select_1, select_2를 담는다.
                for(int k=0; k<4; k++){
                    for(int z=0; z<4; z++) {
                        if (k != i && k != j && z != i && z != j) {
                            board[select_1] -= 1;
                            board[select_2] -= 1;
                            board[k] += 1;
                            board[z] += 1;

                            int prev[4];
                            for (int ii = 0; ii < 4; ii++) {
                                prev[ii] = tmp[K - cnt][ii];
                                tmp[K - cnt][ii] = board[ii];
                            }
                            DFS(cnt - 1);

                            for (int ii = 0; ii < 4; ii++) {
                                tmp[K - cnt][ii] = prev[ii];
                            }

                            board[z] -= 1;
                            board[k] -= 1;
                            board[select_2] += 1;
                            board[select_1] += 1;
                        }
                    }
                }

            }
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> K ;
    tmp.resize(K, vector<int>(4));

    for(int i=0; i<4; i++){
        cin >> board[i];
    }

    for(int i=0; i<4; i++){
        tmp[0][i] = board[i] ;
    }
    DFS(K-1);

    return 0;
}
