#include <bits/stdc++.h>
using namespace std;
string T, P;
int N, M;
vector<int> pi;
void getPI(){
    int st = 1, i = 0;
    while( st + i < M ){
        if( P[st+i] == P[i] ){
            i += 1;
            pi[st+i-1] = i;
        }
        else{
            if( i == 0 )
                st += 1;
            else {
                st = st + i - pi[i - 1];
                i = pi[i - 1];
            }
        }
    }
}
vector<int> kmp(){
    vector<int> ret;

    int st = 0, i = 0;
    while( st <= N-M ){
        // 문자열 매칭에 성공했을 경우
        if( i < M && T[st+i] == P[i] ){
            i += 1;
            if( i == M )
                ret.push_back(st+1);
        }
        else{
            if( i == 0 )
                st += 1;
            else{
                st = st + i - pi[i-1];
                i = pi[i-1];
            }
        }
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    getline(cin, T);
    getline(cin, P);
    N = T.size(), M = P.size();
    pi.resize(M, 0);

    getPI();
    vector<int> ans = kmp();

    cout << ans.size() << '\n';
    for(auto item : ans )
        cout << item << ' ';


    return 0;
}