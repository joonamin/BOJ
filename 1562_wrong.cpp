#define MOD 1000000000
#include <bits/stdc++.h>
using namespace std;
set<pair<string, long long>> dp[101][10]; // i-1번째 까지 string 만큼 방문했을 때, i길이의 계단 수 중 j로 끝나는 경우의 수
// string에 현재까지 방문한 것을 기록
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N;
    cin >> N;

//    N이 주어질 때, 길이가 N이면서 0에서 9가 모두 등장하는 계단 수가 총 몇 개 있는 지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
    // -> 그 전에 방문했던 것을 기록해두어야한다. (0 방문 ? , 1 방문 ?, ...  )

    // base-case : 길이 1
    for(char c='1'; c<='9'; c++){
        string s = "";
        s += c;
        dp[1][c-'0'].insert({s, 0});
    }

    // dp[i][j].first = sum( dp[i-1][0]벡터의 모든 long long 값 )
    for(int i=2; i<=N; i++){
        for(int j=0; j<=9; j++){
            if( j == 0 ){
                // dp[i][0] = dp[i-1][1]
                for(auto it : dp[i-1][1]){
                    if( it.first == "0123456789" ){
                        // 이전에 이미 다 방문한 경우
                        dp[i][0].insert({"0123456789", (it.second + 1)%MOD});
                    }
                    else{
                        string tmp = it.first + "0";
                        sort(tmp.begin(), tmp.end());
                        string new_visited;
                        for(auto e : tmp){
                            if( new_visited == "" || new_visited.back() != e )
                                new_visited += e;
                        }
                        if( new_visited.size() != i ) continue;
                        if( new_visited == "0123456789")
                            dp[i][0].insert({new_visited, 1});
                        else
                            dp[i][0].insert({new_visited, 0});
                    }
                }
            }else if( j == 9 ){
                for(auto it : dp[i-1][8]){
                    if( it.first == "0123456789"){
                        dp[i][9].insert({"0123456789", (it.second + 1)%MOD});
                    }
                    else{
                        string tmp = it.first + "9";
                        sort(tmp.begin(), tmp.end());
                        string new_visited;
                        for(auto e : tmp){
                            if( new_visited == "" || new_visited.back() != e )
                                new_visited += e;
                        }
                        if( new_visited.size() != i ) continue;
                        if( new_visited == "0123456789" )
                            dp[i][9].insert({new_visited, 1});
                        else
                            dp[i][9].insert({new_visited, 0});
                    }
                }
            }
            else{
                for(auto it : dp[i-1][j-1]){
                    if( it.first == "0123456789"){
                        dp[i][j].insert({"0123456789", (it.second+1)%MOD});
                    }
                    else{
                        string tmp = it.first + (char)('0'+j);
                        sort(tmp.begin(), tmp.end());
                        string new_visited;
                        for(auto e : tmp){
                            if( new_visited == "" || new_visited.back() != e )
                                new_visited += e;
                        }
                        if( new_visited.size() != i ) continue;
                        if( new_visited == "0123456789" )
                            dp[i][j].insert({new_visited, 1});
                        else
                            dp[i][j].insert({new_visited, 0});
                    }
                }
                for(auto it : dp[i-1][j+1]) {
                    if (it.first == "0123456789") {
                        dp[i][j].insert({"0123456789", (it.second + 1) % MOD});
                    } else {
                        string tmp = it.first + "0";
                        sort(tmp.begin(), tmp.end());
                        string new_visited;
                        for(auto e : tmp){
                            if( new_visited == "" || new_visited.back() != e )
                                new_visited += e;
                        }
                        if( new_visited.size() != i ) continue;
                        if (new_visited == "0123456789")
                            dp[i][j].insert({new_visited, 1});
                        else
                            dp[i][j].insert({new_visited, 0});
                    }
                }
            }
        }
    }

////    // test
//    for(int i=0; i<=N; i++){
//        for(int j=0; j<=9; j++){
//            cout << "\t #### \n";
//            cout << "dp["<<i<<"]["<<j<<"] print\n";
//            for(const auto& it : dp[i][j] ) {
//                cout << "{ " <<  it.first << ", " << it.second << " }, ";
//            }
//            cout << "\t #### \t \n";
//
//        }
//    }
    long long answer = 0;
    for(int i=0; i<9; i++){
        for(auto it : dp[N][i]){
            if( it.first == "0123456789" ) {
                answer += it.second;
                answer %= MOD;
                break;
            }
        }
    }
    cout << answer ;
    return 0;
}