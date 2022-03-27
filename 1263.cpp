#include <bits/stdc++.h>
using namespace std;
int N;
vector<pair<int,int>> info;
int answer = -1;
bool canDo(int start){
    // start에서 시작해서 모든 일을 마칠 수 있는가?
    int time = start;
    for(int i=0; i<info.size(); i++){
        if( time + info[i].second > info[i].first ){
            return false;
        }
        time += info[i].second;
    }
    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    cin >> N;

    info.resize(N);
    for(int i=0; i<N; i++){
        cin >> info[i].second >> info[i].first; // 마감시간, 소요시간
    }
    sort(info.begin(), info.end());

    long long left = 0, right = 1000 * 1000000;
    while(left <= right ){
        long long st = (left+right)/2; // 시작 시간
        if( canDo(st) ){
            answer = st;
            left = st + 1;
        }else{
            right = st - 1;
        }
    }
    cout << answer;


    return 0;
}
