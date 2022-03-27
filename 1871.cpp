#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    while(N--){
        string s;
        cin >> s;
        int p1 = 0, p2 = stoi(s.substr(4));

        string s1 = s.substr(0, 3);
        int factor = 1, idx = 2;
        while( idx >= 0 ){
            p1 += ( (int)(s1[idx] - 'A') * factor );
            factor *= 26;
            idx -= 1;
        }
        if( abs(p1-p2) <= 100 )
            cout << "nice\n";
        else
            cout << "not nice\n";
    }


    return 0;
}
