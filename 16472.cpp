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
    int N;
    cin >> N;
    string s;
    cin >> s;

    int l = 1; // [i, i+l)
    int size = s.size();
    for(int i=0; i+l <= size; i++){
        string sub = s.substr(i, l);
        set<char> set1;
        for(auto item : sub) set1.insert(item);
        int j = i+l;
        while(j < size){
            set1.insert(s[j]);
            if(set1.size() > N)
                break;
            j++, l++;
        }
    }
    cout << l ;
    return 0;
}
