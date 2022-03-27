#include <bits/stdc++.h>
using namespace std;

int ans = 0 ;
vector<string> words;
bool known[26] = {false, };
void select(int idx, int n, int rest){
    if( rest == 0 ){
        int cnt = 0;
        for(int i=0; i<n; i++){

            bool canRead = true;
            for(int j=0; j<words[i].size(); j++){
                if( !known[words[i][j]-'a'] ) {
                    canRead = false;
                    break;
                }
            }
            if( canRead ) cnt += 1;
        }
        if( cnt > ans ) ans = cnt;
        return;
    }
    for(int i=idx; i<26; i++){
        if( !known[i] ){
            known[i] = true;
            select(i+1, n, rest-1 );
            known[i] = false;
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N,K;
    cin >> N >> K;
    for(int i=0; i<N; i++){
        string tmp ;
        cin >> tmp;
        words.push_back(tmp);
    }

    // 최소 알아야하는 알파벳 개수 : 5개 ( a, c, t, i, n )
    known['a'-'a'] = known['c'-'a'] = known['t'-'a'] = known['i'-'a'] = known['n'-'a'] = true;
    if( K < 5 ){
        cout << 0 ;
    }
    else{
        K -= 5;
        // n개 중 k 개를 모두 뽑아 본다.
        select(0, N, K);
        cout << ans ;
    }

    return 0;
}