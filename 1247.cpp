#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N;
void add(vector<int>& sum, ll num){
    bool isNegative = num < 0LL ? true : false;
    num = abs(num);
    vector<int> v(500, 0);
    int i = 0;
    while(num){
        v[i++] = num % 2;
        num >>= 1;
    }
    if(isNegative){
        for(auto &bit : v)
            bit = !bit;
        add(v, 1);
    }

    for(int j=0; j<500; j++){
        sum[j] += v[j];
        if(j + 1 < 500)
            sum[j+1] += (sum[j] / 2);
        sum[j] %= 2;
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    for(int t=0; t<3; t++){
        cin >> N;
        vector<int> sum(500, 0);
        for(int i=0; i<N; i++){
            ll in;
            cin >> in;
            add(sum, in);
        }

        if(sum.back() == 1)
            cout << "-\n";
        else{
            if(any_of(sum.begin(), sum.end(), [](const int& k){return k == 1;}))
                cout << "+\n";
            else
                cout << "0\n";
        }

    }
    return 0;
}
