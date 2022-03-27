#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
string S;
int v[3], dp[501][501][501];
auto fn = [](int &a, int &b, int &c){
    int mn = a, mx = a, sum = a + b + c;
    if(mn > b)
        mn = b;
    if(mx < b)
        mx = b;
    if(mn > c)
        mn = c;
    if(mx < c)
        mx = c;
    a = mn, b = sum - mn - mx, c = mx;
};
int DFS(int a, int b, int c){
    fn(a, b, c);
    int &ret = dp[a][b][c];
    if(ret != -1)
        return ret;
    if(a == b && b == c)
        return ret = 1;

    ret = 0;
    if(a != b){
        ret |= DFS(a+a, b-a, c);
    }
    if(!ret && b != c){
        ret |= DFS(a, b+b, c-b);
    }
    if(!ret && a != c){
        ret |= DFS(a+a, b, c-a);
    }
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> v[0] >> v[1] >> v[2];
    memset(dp, -1, sizeof(dp));
    cout << DFS(v[0], v[1], v[2]);
    return 0;
}
