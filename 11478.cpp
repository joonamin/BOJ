#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

string s, v;
unordered_map<string, int> ump;


int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> s;
    for(int i=0; i<s.size(); i++){
        v = s[i];
        ump[v] += 1;
        for(int j=i+1; j<s.size(); j++){
            v.push_back(s[j]);
            ump[v] += 1;
        }
    }
    cout << ump.size();

    return 0;
}
