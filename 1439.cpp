#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    string in;
    cin >> in ;
    int size = in.size();
    int zero = 0, one = 0;
    int i = 0, j = 0;

    while(i<size && j<size){
        if( i == j ){
            if( in[i] == '0' ) zero += 1;
            else one += 1;
        }
        if( in[i] != in[j] ){
            i = j;
        }
        else
            j++;

    }
    cout << min(one, zero);
    return 0;
}
