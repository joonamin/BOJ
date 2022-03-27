#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    string s;
    cin >> s;
    int cnt = 0;
    auto getDigitSum = [](string str)->int{
        int sum = 0;
        for(const auto &c : str){
            sum += (c - '0');
        }
        return sum;
    };

    int sum = (s.size() == 1) ? stoi(s) : -1;
    while(s.size() >= 2){
        cnt += 1;
        sum = getDigitSum(s);
        s = to_string(sum);
    }

    cout << cnt << '\n';
    if(sum != 0 && sum % 3 == 0) cout << "YES\n";
    else cout << "NO\n";
    return 0;
}
