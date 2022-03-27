#define ll long long
#define MOD 1000000007LL
#include <bits/stdc++.h>
using namespace std;
ll D;
const vector<vector<ll>> v = {
        {0, 1, 1, 0, 0, 0, 0, 0},
        {1, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 0, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 1, 0, 0},
        {0, 0, 1, 1, 0, 1, 0, 1},
        {0, 0, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0}
};
vector<vector<ll>> multiply(const vector<vector<ll>>& M1, const vector<vector<ll>>& M2){
    vector<vector<ll>> ret(8, vector<ll>(8));
    for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
            ll elem = 0;
            for(int k=0; k<8; k++){
                elem += (M1[i][k] * M2[k][j]);
                elem %= MOD;
            }
            ret[i][j] = elem % MOD;
        }
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> D;

    // D제곱 한 것의 1행 1열의 결과 P->P로 D만큼의 변을 거쳐서 갈 때, 나오는 경우의 수
    vector<vector<ll>> ans(8, vector<ll>(8));
    for(int i=0; i<8; i++){
        ans[i][i] = 1;
    }
    vector<vector<ll>> factor = v;
    while(D){
        if( D & 1 ){
            ans = multiply(ans, factor);
            D -= 1;
        }
        factor = multiply(factor, factor);
        D /= 2;
    }
    cout << ans[0][0] << '\n';
    return 0;
}
