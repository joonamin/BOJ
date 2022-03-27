#include <bits/stdc++.h>
using namespace std;
int my_upper_bound(int st, int end, int value){
    // value 보다 큰 첫번째 항을 찾는다.

}
struct compare{
    bool operator()(const pair<int,int>& a, const pair<int,int>& b){
        if( a.first != b.first )
            return a.first > b.first;
        else
            return a.second < b.second;
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N, K;
    cin >> N >> K;

    // 가방에는 최대 한 개의 보석만 넣을 수 있다.
    // -> 이는 즉, 한 가방에 가장 가치가 비싼 보석을 담아야함을 의미한다.

    // 가정 1. 가장 무거운 걸 처리 -> 반례 존재
    // 가정 2. 가장 비싼 걸 처리 -> 반례 존재
    // 가정 3. 현재 가방에 담을 수 있는 보석 중 가장 비싼 보석을 담는다. - o

    vector<pair<int,int>> info;
    for(int i=0; i<N; i++){
        int weight, value;
        cin >> weight >> value;
        info.push_back({value, weight});
    }

    // 무게 : 오름차순 , 가치 : 내림차순
    // {무게, 가치}
    priority_queue<pair<int,int>, vector<pair<int,int>>, compare> PQ;
    for(auto d : info )
        PQ.push({d.second, d.first});

    vector<int> backpack(K);
    for(int i=0; i<K; i++){
        cin >> backpack[i];
    }
    sort(backpack.begin(), backpack.end());

    long long answer = 0;
    // PQ에 보석을 넣는다.
    for(auto w : backpack){
        if( PQ.empty() ) break;
        int weight = PQ.top().first, value = PQ.top().second;
        PQ.pop();
        // 현재 이 가방이 가장 가벼운데, 담을 수 없다는 것은 이 보석은 절대 선택될 수 없다는 뜻이다.
        if( w < weight )
            continue;
        answer += value;
    }
    cout << answer;
    return 0;
}
