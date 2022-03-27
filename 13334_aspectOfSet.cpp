#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


int N, D;
vector<pair<int,int>> v;
vector<int> a,b;

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N ;
    v.resize(N);
    for(int i=0; i<N; i++){
        cin >> v[i].first >> v[i].second;
        if( v[i].first > v[i].second )
            swap(v[i].first, v[i].second);
    }
    cin >> D;

    for(int i=0; i<N; i++){
        if( v[i].second - v[i].first > D )
            continue;
        a.push_back(v[i].first);
        b.push_back(v[i].second);
    }

    sort(a.begin(), a.end());
    sort(b.begin(), b.end());

    int current = -1, ans = 0;
    for(int i=0; i<a.size(); i++){
        if( a[i] == current ) continue;
        current = a[i];

        int temp = a.end() - lower_bound(a.begin(), a.end(), current);
        temp += upper_bound(b.begin(), b.end(), current+D) - b.begin();
        temp -= a.size();
        ans = max(ans, temp);
    }
    cout << ans ;

    return 0;
}
