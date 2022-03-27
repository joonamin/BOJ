#include <bits/stdc++.h>
using namespace std;
int N;
vector<pair<int,int>> dp; // dp[i].first : i+1길이를 가지는 lis중 마지막 원소의 최솟값을 가지는 a값 , dp는 정의에 따라 ascending order
// dp[i].second : 실제 이것이 arr상에서 몇번쨰 인덱스에 저장되는가 ?
bool comp(const pair<int,int>& a, const pair<int,int>& b){
    return a.first < b.second;
}
int parent[500001] ;
void backtrace(vector<bool>& m, int idx){
    m[idx] = true;
    if( parent[idx] == -1 )
        return;
    backtrace(m, parent[idx]);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    for(auto& it : parent) it = -1;
    cin >> N;

    vector<pair<int,int>> arr;
    for(int i=0; i<N; i++){
         int a, b;
         cin >> a >> b;
         arr.push_back({a,b});
    }
    sort(arr.begin(), arr.end());

    dp.push_back({arr[0].second,0});
    for(int i=1; i<N; i++){
        if( dp.back().first < arr[i].second ) {
            parent[i] = dp.back().second; // 마지막 항을 밟고 올라간 것.
            dp.push_back({arr[i].second, i});
        }
        else{
            int idx = lower_bound(dp.begin(), dp.end(), arr[i], comp) - dp.begin();
            // idx - 1 번째 있는 항을 밟고 올라간 것
            if( idx - 1 >= 0 ) {
                parent[i] = dp[idx - 1].second;
            }else{
                parent[i] = -1;
            }
            dp[idx] = {arr[i].second, i};
        }
    }
    // 전체 전기줄의 개수 - lis 길이 = 지워야할 전기줄의 개수
    cout << N - dp.size() << '\n';

    // 자 이제, 이것을 backtrace 해보자.
    vector<bool> mask(arr.size(), false);
    backtrace(mask, dp.back().second);
    for(int i=0; i<N; i++){
        if( !mask[i] )
            cout << arr[i].first << '\n';
    }

    return 0;
}