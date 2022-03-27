#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


int K, L, N;
string s;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> K >> L >> N;
    cin >> s;

    vector<int> ans;
    bool isInUse = false, f = false;
    int ut = 0, et = 0;
    for(int i=0; i<N; i++){
        if(s[i] == '0'){
            if(isInUse){
                isInUse = false;
                ut = 0, et = 1;
            }else
                et++;
        }else{
            if(isInUse){
                ut++;
            }else{
                isInUse = true;
                ut = 1, et = 0;
            }
        }
        if(ut == K)
            f = true;
        if(et == L){
            if(f){
                ans.push_back(i+1);
                f = false;
            }
            et = 0;
        }
    }
    if(f)
        ans.push_back(N+L-et);
    if(ans.empty()){
        cout << "NIKAD";
    }else {
        for (auto item : ans)
            cout << item << '\n';
    }

    return 0;
}
