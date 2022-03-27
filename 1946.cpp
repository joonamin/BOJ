#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int T;
    cin >> T;
    while(T--){
        int N;
        cin >> N;
        vector< pair<int,int> > v(N);
        for(int i=0; i<N; i++){
            cin >> v[i].first >> v[i].second;
        }
        sort(v.begin(), v.end());

        int cnt = 1;
        int super = v[0].second;
        for(int i=1; i<N; i++){
            if( v[i].second < super ){
                cnt += 1;
                super = v[i].second;
            }
        }
        cout << cnt << '\n';

    }

    return 0;
}