#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int T;
    cin >> T;
    while(T--){
        int N;
        cin >> N;

        vector<long long> v(N);
        for(int i=0; i<N; i++){
            cin >> v[i];
        }

        priority_queue<long long, vector<long long>, greater<long long>> PQ;
        for(auto item : v)
            PQ.push(item);

        long long answer = 0;
        while(PQ.size() >= 2){
            auto fTop = PQ.top(); PQ.pop();
            auto sTop = PQ.top(); PQ.pop();
            answer += (fTop + sTop);
            PQ.push(fTop + sTop);
        }
        cout << answer << '\n';
    }

    return 0;
}