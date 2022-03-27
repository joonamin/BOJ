#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N;
ll mx[25][25], mn[25][25];
string s;
ll calc(ll a, char op, ll b){
    if(op == '+') return a + b;
    if(op == '-') return a - b;
    if(op == '*') return a * b;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    for(int i=0; i<25; i++){
        for(int j=0; j<25; j++){
            mx[i][j] = -3e9;
            mn[i][j] = 3e9;
        }
    }

    cin >> N >> s;

    for(int i=0; i<N; i++) {
        if(i % 2 == 0)
            mx[i][i] = mn[i][i] = s[i] - '0';
    }

    // size = 3, 5, ... 2n + 1
    for(int sz=3; sz <= N; sz += 2){
        for(int i=0; i<N; i+=2){
            int j = i + sz - 1;
            if(j >= N) continue;
            // partition [i, k-1], [k+1, j]
            // k is operator
            for(int k=i+1; k<j; k+=2){
                mx[i][j] = max(mx[i][j], calc(mx[i][k-1], s[k], mx[k+1][j]));
                mx[i][j] = max(mx[i][j], calc(mx[i][k-1], s[k], mn[k+1][j]));
                mx[i][j] = max(mx[i][j], calc(mn[i][k-1], s[k], mx[k+1][j]));
                mx[i][j] = max(mx[i][j], calc(mn[i][k-1], s[k], mn[k+1][j]));

                mn[i][j] = min(mn[i][j], calc(mx[i][k-1], s[k], mx[k+1][j]));
                mn[i][j] = min(mn[i][j], calc(mx[i][k-1], s[k], mn[k+1][j]));
                mn[i][j] = min(mn[i][j], calc(mn[i][k-1], s[k], mx[k+1][j]));
                mn[i][j] = min(mn[i][j], calc(mn[i][k-1], s[k], mn[k+1][j]));
            }
        }
    }

    cout << mx[0][N-1] << '\n';
    return 0;
}

