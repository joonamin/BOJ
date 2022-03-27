#define MOD 1000003
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, S, E, T;
struct Matrix{
    vector<vector<ll>> m;
    Matrix(int sz){m.resize(5*sz, vector<ll>(5*sz, 0));}
    friend Matrix operator* (const Matrix& a, const Matrix& b){
        int size = a.m.size()/5;
        Matrix ret(size);
        for(int i=0; i<5*size; i++){
            for(int j=0; j<5*size; j++){
                ll sum = 0;
                for(int k=0; k<5*size; k++)
                    sum += a.m[i][k] * b.m[k][j];
                ret.m[i][j] = sum;
                ret.m[i][j] %= MOD;
            }
        }
        return ret;
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> S >> E >> T;

    Matrix mat(N);
    for(int i=0; i<N; i++){
        for(int j=1; j<=4; j++)
            mat.m[5*i+j][5*i+j-1] = 1;
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            char in ; cin >> in;
            ll val = in - '0';
            if( val >= 1 )
                mat.m[5*i][5*j+val-1] = 1;
        }
    }

    Matrix ans(N); // 초기값 : 항등원
    for(int i=0; i<5*N; i++)
        ans.m[i][i] = 1;

    while(T){
        if( T & 1 ){
            ans = ans * mat;
            T--;
        }
        mat = mat * mat ;
        T >>= 1;
    }
    cout << ans.m[5*(S-1)][5*(E-1)];
    return 0;
}