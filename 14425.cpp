#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int N, M;
unordered_map<string, int> ump;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M;
    for(int i=0; i<N; i++){
        string s;
        cin >> s;
        ump[s] = 0;
    }
    int ans = 0;
    while(M--){
        string ss;
        cin >> ss;
        if( ump.find(ss) != ump.end() )
            ans += 1;
    }
    cout << ans ;
    return 0;
}
