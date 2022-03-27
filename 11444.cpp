#define MOD 1000000007LL
#include <bits/stdc++.h>
struct Matrix{
    long long data[2][2] ;
    Matrix(){
        data[0][0] = data[0][1] = data[1][0] = 1;
        data[1][1] = 0;
    }
    Matrix(int val){
        this->data[0][0] = this->data[1][1] = 1;
        this->data[0][1] = this->data[1][0] = 0;
    }
    Matrix operator* (const Matrix& op2){
        Matrix result;
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++)
                result.data[i][j] = 0;
        }

        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<2; k++){
                    result.data[i][j] += ((this->data[i][k] % MOD) * (op2.data[k][j] % MOD)) % MOD;
                    result.data[i][j] %= MOD;
                }
            }
        }
        return result;
    }
};
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    long long n;
    cin >> n;

    //  A B 에서
    //  C D                 C값이 Fibo(N)의 항이다.
    Matrix m;

    Matrix answer(0); // 항등행렬 I를 만든다 .
    while(n){
        if( n & 1 ){
            answer = answer * m;
            n -= 1;
        }
        m = m * m;
        n /= 2;
    }
    cout << answer.data[1][0] << '\n';
    return 0;
}
