#include <bits/stdc++.h>
using namespace std;
int n;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> n;

    for(int i=0; i<n; i++){
        int size;
        cin >> size;
        vector<long long> land(size);
        set<long long> keys;
        for(int j=0; j<size; j++) {
            cin >> land[j];
            keys.insert(land[j]);
        }
        sort(land.begin(), land.end());
        // (key의 개수, key)
        vector< pair<long long, long long> > info;
        for(auto k : keys ){
            int l = lower_bound(land.begin(), land.end(), k) - land.begin();
            int r = upper_bound(land.begin(), land.end(), k) - land.begin();
            info.push_back({r-l, k});
        }
        sort(info.begin(), info.end(), greater<pair<long long,long long>>());
        if( info[0].first == size || info[0].first > size/2 && info[0].first != info[1].first )
            cout << info[0].second << '\n';
        else
            cout << "SYJKGW\n";
    }

    return 0;
}