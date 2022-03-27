#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;
    vector<long long> cost(N), d(N+1,0);
    for(int i=0; i<N-1; i++){
        cin >> d[i];
    }
    for(int i=0; i<N; i++)
        cin >> cost[i];

    // i번째 도시에 방문했을 때, 주유비가 더 싼 도시를 발견하기 전 까지 달리자!
    long long answer = 0 ;

    int i = 0;
    while( i < N ){
        int next = i + 1;
        int dSum = 0; // 주유비가 더 싼 다음 도시까지 도착하는데 거리
        while(next < N && cost[next] > cost[i])
            next += 1;
        for(int j=i; j<next; j++)
            dSum += d[j];
        answer += cost[i] * dSum;
        i = next;
    }

    cout << answer;
    return 0;
}
