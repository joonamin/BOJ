#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N ;
    vector<int> arr(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    stack<int> stk;
    int cur = 1;
    bool f = true;
    for(int i=0; i<N && f; i++){
        if( arr[i] == cur )
            cur++;
        else{
            while(!stk.empty() && stk.top() < arr[i]){
                if( stk.top() != cur ){
                    f = false;
                }else
                    stk.pop(); cur++;
            }
            stk.push(arr[i]);
        }
    }
    cout << (f?"Nice":"Sad");
    return 0;
}
