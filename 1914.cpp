#include <bits/stdc++.h>
using namespace std;
class BigInteger{
public:
    vector<int> data; // 역순으로 저장
    BigInteger(string val){
        data.resize(val.size(), 0);
        int k = val.size()-1;
        for(auto c : val){
            data[k--] = (int)(c - '0');
        }
    }
    BigInteger(int val, int size){
        data.resize(size, val);
    }
    BigInteger operator+(BigInteger& op){
        int size = max( this->data.size(), op.data.size() );
        BigInteger ret(0, size+1);

        this->data.resize(size, 0);
        op.data.resize(size, 0);

        for(int i=0; i<size; i++){
            int sum = this->data[i] + op.data[i] + ret.data[i];
            ret.data[i+1] += sum / 10 ;
            ret.data[i] = sum % 10 ;
        }
        // trailing zero 제거 후 리턴
        while( ret.data.back() == 0 )
            ret.data.pop_back();
        return ret;
    }
};
int N;
// cnt 만큼을 m을 경유하여 from -> to 로 옮긴다.
vector<pair<int,int>> logs;
void solve(int cnt, int from, int to, int m){
    if( cnt == 1 ){
        logs.push_back({from,to});
        return;
    }
    // 위에서 cnt 개를 m을 경유하여, from -> to 로 옮길 때
    solve(cnt-1, from, m, to);
    solve(1, from, to, m);
    solve(cnt-1, m, to, from);
}
BigInteger getCnt(int N){
    BigInteger one("1");
    if( N == 1 ) return one;

    BigInteger a = getCnt(N-1);
    BigInteger b = a;
    BigInteger ret = a + b;
    ret = ret + one;

    return  ret ;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N;

    BigInteger K = getCnt(N);
    for(auto it = K.data.rbegin(); it != K.data.rend(); it++){
        cout << *it ;
    }
    cout << '\n';
    if( N <= 20 )
        solve(N, 1,3, 2);

    for(auto it : logs)
        cout << it.first << ' ' << it.second << '\n';
    return 0;
}
