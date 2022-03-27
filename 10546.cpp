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
    map<string, int> mp;
    for(int i=0; i<N; i++){
        string s; cin >> s;
        mp[s] += 1;
    }
    for(int i=0; i<N-1; i++){
        string s; cin >> s;
        mp[s] -= 1;
    }
    for(const auto& item : mp){
        if( item.second != 0 ) {
            cout << item.first;
            break;
        }
    }
    return 0;
}
