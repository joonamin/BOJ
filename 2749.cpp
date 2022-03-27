#define ll long long
#define MOD 1000000LL

#include <bits/stdc++.h>
using namespace std;
vector<vector<ll>> mat;
vector<vector<ll>> multiply(const vector<vector<ll>>& a, const vector<vector<ll>>& b){
    vector<vector<ll>> ret;
    ret.resize(2, vector<ll>(2));

    ll e[2][2] = {
            {a[0][0]*b[0][0] % MOD + a[0][1]*b[1][0] % MOD, a[0][0]*b[0][1] % MOD + a[0][1]*b[1][1] % MOD},
            {a[1][0]*b[0][0] % MOD + a[1][1]*b[1][0] % MOD, a[1][0]*b[0][1] % MOD+ a[1][1]*b[1][1] % MOD}
    };

    for(int i=0; i<2; i++){
        for(int j=0; j<2; j++)
            ret[i][j] = e[i][j] % MOD;
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    ll n ;
    cin >> n;
    n--;

    if( n == -1 ) {
        cout << 0;
        return 0;
    }


    mat.resize(2, vector<ll>(2));
    mat[0][0] = mat[0][1] = mat[1][0] = 1;
    mat[1][1] = 0;

    vector<vector<ll>> ans_mat(2, vector<ll>(2, 0));
    ans_mat[0][0] = 1, ans_mat[1][1] = 1;

    while(n){
        if( n & 1 ){
            ans_mat = multiply(ans_mat, mat);
            n -= 1;
        }
        n /= 2;
        mat = multiply(mat, mat);
    }

    cout << ans_mat[0][0] ;
    return 0;
}