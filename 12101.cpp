#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, K, flag = 0;
vector<int> v;
void select(int rest){
    if(rest == 0){
        K--;
        if(K == 0){
            flag = 1;
            for(int i=0; i<v.size(); i++){
                if(i == v.size() - 1)
                    cout << v[i];
                else
                    cout << v[i] << "+";
            }
        }
        return;
    }
    for(int i=1; i<=3; i++){
        if(rest - i >= 0){
            v.push_back(i);
            select(rest - i);
            v.pop_back();
        }
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> K;
    select(N);
    if(!flag)
        cout << -1 << '\n';
    return 0;
}
