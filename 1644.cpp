#include <bits/stdc++.h>
using namespace std;
int N;
bool sieve[4000001] = {false, };
vector<int> prime_nums;
int ans = 0 ;
bool isPrime(int num){
    for(int i=2; i*i <=num; i++){
        if( num % i == 0 )
            return false;
    }
    return true;
}
void DFS(int start_idx, int sum){
    if( sum == N ){
        ans += 1;
        return ;
    }
    if( start_idx >= prime_nums.size() || sum > N )
        return;
    if( start_idx + 1 < N )
        DFS(start_idx+1, sum + prime_nums[start_idx+1]);
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N ;

    for(int i=2; i<=N; i++){
        if( sieve[i] == false ){
            if( isPrime(i) ){
                for(long long j = (long long)i*i; j <= N; j += i)
                    sieve[j] = true;
            }
        }
    }

    for(int i=2; i<=N; i++){
        if( sieve[i] == false )
            prime_nums.push_back(i);
    }

    for(int i=0; i<prime_nums.size(); i++){
        DFS(i, prime_nums[i]);
    }
    cout << ans;

    return 0;
}
