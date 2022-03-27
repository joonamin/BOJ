#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

map<string, int> mp;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int K, L;
    cin >> K >> L;

    // ump : 학생의 대기 번호
    for(int i=1; i<=L; i++){
        string s;
        cin >> s;
        mp[s] = i;
    }

    map<int, string> mp2;
    for(const auto& item : mp){
        mp2[item.second] = item.first;
    }
    auto iter = mp2.begin();
    while(K-- && iter != mp2.end()){
        cout << iter->second << '\n';
        iter++;
    }

    return 0;
}
