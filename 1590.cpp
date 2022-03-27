#define ll long long
#include <bits/stdc++.h>

using namespace std;
ll N, T;
int main(void){
    // 버스의 개수, 영식이가 버스터미널에 도착하는 시간
    cin >> N >> T;

    bool isUpdate = false;

    // 영식이가 버스를 탈 수 있는 시간의 최솟값
    // bus_time - T 의 최솟값
    // bus_time은 >= T !

    vector<ll> bus_times;
    for(int i=0; i<N; i++){
        ll start, interval, count ;
        cin >> start >> interval >> count;
        for(int j=0; j<count; j++){
            bus_times.push_back( start + j*interval );
        }
    }
    sort( bus_times.begin(), bus_times.end() );
    // bus_time >= T 인 가장 최소의 값을 찾아라 !

    ll answer;
    ll l_idx = 0 , r_idx = bus_times.size()-1;
    while(l_idx <= r_idx){
        ll mid_idx = (l_idx+r_idx)/2;
        if( bus_times[mid_idx] < T )
            l_idx = mid_idx + 1;
        else{
            isUpdate = true;
            answer = bus_times[mid_idx] - T;
            r_idx = mid_idx - 1;
        }
    }

    if( !isUpdate )
        cout << -1;
    else
        cout << answer;

    return 0;
}