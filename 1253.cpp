#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    int N;
    cin >> N;
    vector<ll> v(N);
    for (int i = 0; i < N; i++)
        cin >> v[i];

    sort(v.begin(), v.end());

    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if( i == j ) continue;
            int lower = lower_bound(v.begin(), v.end(), v[i]-v[j]) - v.begin();
            while(lower == i || lower == j) lower += 1;
            if( lower >= N ) continue;
            if( v[lower] + v[j] == v[i] ) {
                ans++;
                break;
            }
        }
    }
    cout << ans ;
    return 0;
}