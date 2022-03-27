#include <bits/stdc++.h>
using namespace std;
int N, ans = INT_MIN;
string expression;
int calculate(string s){
    int ret = s[0]-'0';
    for(int i=1; i<s.size()-1; i++){
        char op = s[i];
        int factor = s[i+1] - '0';
        if( op == '+' ) ret += factor;
        else if( op == '-' ) ret -= factor;
        else if( op == '*' ) ret *= factor;
    }
    return ret;
}
int calculate(int a, int b, char type){
    if( type == '+' )
        return a + b;
    else if( type == '-' )
        return a - b;
    else
        return a * b;
}
void search( int ret, int idx ){
    if( idx >= N ){
        if( ret > ans )
            ans = ret;
        return ;
    }
    // 괄호로 묶을 래?
    int tmp;
    if( idx + 2 < N ){
        char op = expression[idx+1];
        int factor1 = expression[idx]-'0', factor2 = expression[idx+2]-'0';
        int tmp = calculate(factor1,factor2, op);
        if( idx == 0 )
            search( tmp , idx+4 );
        else{
            int tmp2 = calculate(ret, tmp, expression[idx-1]);
            search( tmp2, idx+4 );
        }
    }
    // 묶지 않고 진행
    if( idx == 0 )
        search( expression[idx]-'0', idx+2 );
    else
        search( calculate(ret, expression[idx]-'0', expression[idx-1]), idx + 2 );
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    // 괄호 안에는 연산자가 하나만 들어가야한다.

    cin >> N >> expression;

    search(0,0);
    cout << ans ;
    return 0;
}