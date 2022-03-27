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

    // 어떤 차가 추월했을 경우?
    // 자기 보다 앞에 있던 차가 나중에는 뒤에 있는 경우, 현재 차는 추월 한 것이다.
    int N;
    cin >> N;
    map<string, int> mp;
    vector<string> before(N), after(N);
    for(int i=0; i<N; i++)
        cin >> before[i];
    for(int i=0; i<N; i++) {
        cin >> after[i];
        mp[after[i]] = i;
    }
    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<i; j++){
            if( mp[before[i]] < mp[before[j]] ){
                ans += 1;
                break;
            }
        }
    }

    cout << ans ;
    return 0;
}
