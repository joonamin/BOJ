#include <bits/stdc++.h>
using namespace std;
int N;
vector<pair<int,int>> v;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;

    v.resize(N);

    for(int i=0; i<N; i++)
        cin >> v[i].first >> v[i].second;

    // 음 .. 모든 수업을 다 하되, 최소의 강의실 사용

    // 한 강의실에 최대한 많은 강의를 넣기 위해선 ?
    // 먼저 시작하는 거 우선 넣되, 그 중 끝나는 시간이 빠른 것
    // 그렇게 해서 배정이 끝난다면?
    // 남은 원소들에 대해서 그 과정을 반복해주자..

    // N^2 -> NlogN 방식으로 최적화 ! 생각을 바꿔보자
    // 현재 강의실에서 사용가능 한 시작시간 = 최근 강의가 끝난 시간


    sort(v.begin(), v.end());
    priority_queue<int, vector<int>, greater<int>> PQ;
    PQ.push(v[0].second);

    for(int i=1; i<N; i++){
        int fastest_end = PQ.top();
        if( fastest_end > v[i].first ) {
            PQ.push(v[i].second); // 새로운 강의실 배정
        }
        else{
            PQ.pop();
            PQ.push(v[i].second);
        }
    }
    cout << PQ.size();

    return 0;
}