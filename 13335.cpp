#include <bits/stdc++.h>
using namespace std;
int n, w, L;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> n >> w >> L;

    queue<int> rest_trucks;
    queue<pair<int,int>> on_bridge;

    int current_bridge_weight = 0;

    vector<int> truck_weight(n);
    for(int i=0; i<n; i++){
        cin >> truck_weight[i];
        rest_trucks.push(truck_weight[i]);
    }

    int time = 0;
    while(!rest_trucks.empty()){
        time += 1;
        int N = on_bridge.size();
        for(int i=0; i<N; i++){
            // 다리 위에 있는 트럭이 움직인다.
            pair<int,int> front = on_bridge.front(); on_bridge.pop();
            front.first -= 1;
            current_bridge_weight -= front.second;

            if( front.first > 0 ) {
                on_bridge.push({front.first, front.second});
                current_bridge_weight += front.second;
            }
        }
        int new_truck_weight = rest_trucks.front();
        if( new_truck_weight + current_bridge_weight <= L ){
            // 트럭이 하나 더 다리위에 올라선다.
            on_bridge.push({w, new_truck_weight});
            current_bridge_weight += new_truck_weight;
            rest_trucks.pop();
        }
    }
    // 다리 위에 있는 트럭들이 전부 지나가야 함
    while(!on_bridge.empty()){
        time += 1;
        int N = on_bridge.size();
        for(int i=0; i<N; i++){
            // 다리 위에 있는 트럭이 움직인다.
            pair<int,int> front = on_bridge.front(); on_bridge.pop();
            front.first -= 1;
            current_bridge_weight -= front.second;

            if( front.first > 0 ) {
                on_bridge.push({front.first, front.second});
                current_bridge_weight += front.second;
            }
        }
    }
    cout << time;
    return 0;
}