#include <bits/stdc++.h>
using namespace std;
int main(void){
 //   freopen("input.txt","r",stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;

    vector<int> arr(N+1);
    for(int i=1; i<=N; i++){
        cin >> arr[i];
    }

    vector<int> answer(N+1);
    // stack에 자기 자신보다 크거나 같은 원소의 최대 인덱스 top에 유지
    stack<int> stk;
    answer[1] = 0;
    stk.push(0);

    for(int i=2; i<=N; i++){
        if( arr[i-1] >= arr[i] ){
            stk.push(i-1);
            answer[i] = i-1;
        }
        else{
            while(!stk.empty() && stk.top() != 0){
                int idx = stk.top();
                if( arr[idx] >= arr[i] ){
                    answer[i] = idx;
                    break;
                }
                else
                    stk.pop();
            }
            if( stk.top() == 0 )
                answer[i] = 0;
        }
    }

    for(int i=1; i<=N; i++)
        cout << answer[i] << ' ';
    return 0;
}