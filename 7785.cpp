#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int n;
map<string, int, greater<>> ump;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> n;
    while(n--){
        string name, opt;
        cin >> name >> opt ;
        if( opt == "enter" )
            ump.insert({name, 1});
        else
            ump.erase(name);
    }
    for(const auto& item : ump)
        cout << item.first << '\n';
    return 0;
}
