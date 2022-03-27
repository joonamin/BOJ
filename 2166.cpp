#define ll long long
#include <iostream>
#include <vector>

using namespace std;
long long N;
vector<pair<long long,long long>> v;
long long CCW(pair<long long,long long> a, pair<long long,long long> b, pair<long long,long long> c){
    // 벡터의 외적 활용, 곧 CCW가 외적한 결과 벡터의 크기가 된다. (-)값도 될 수 있음.
    return (long long)(a.first*b.second)+(b.first*c.second)+(a.second*c.first) -
            (b.first*a.second)-(c.first*b.second)-(a.first*c.second);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N;
    v.resize(N);

    for(int i=0; i<N; i++)
        cin >> v[i].first >> v[i].second;

    double answer = 0 ;
    for(int i=1; i<N-1; i++){
        answer += CCW(v[0], v[i], v[i+1]) /(double)2;
    }

    cout << fixed;
    cout.precision(1);

    cout << abs(answer);

    return 0;
}
