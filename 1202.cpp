#include <bits/stdc++.h>
using namespace std;
int N, K;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> K;

    // 제일 가벼운 가방 부터 담을 수 있는 최대 가치의 보석을 선택하는 전략
    // 제일 비싼 보석부터 하나씩 보면서, 해당 보석을 담을 수 있는 가방이 존재하는 지 확인
    // 존재한다면, 해당 가방을 erase 한다. * 시간 복잡도 개선 위해서 어떤 자료구조 ?

    vector<pair<int,int>> J(N);
    for(int i=0; i<N; i++){
        int M, V;
        cin >> M >> V ;
        J[i].first = V, J[i].second = M;
    }
    sort(J.begin(), J.end(), greater<pair<int,int>>());

    multiset<int> bagWeight;
    for(int i=0; i<K; i++){
        int W;
        cin >> W ;
        bagWeight.insert(W);
    }

    long long answer = 0;
    for(int i=0; i<N; i++){
        auto target = bagWeight.lower_bound(J[i].second);
        if( target == bagWeight.end() )
            continue;
        answer += J[i].first;
        bagWeight.erase(target);
    }

    cout << answer;
    return 0;
}
