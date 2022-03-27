#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
struct GORILLA {
    string name;
    priority_queue<int, vector<int>> PQ;
    GORILLA(string n){ name = n ; }
};
map<string, GORILLA*> mp;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int Q;
    cin >> Q;

    ll ans = 0;
    while(Q--){
        int opt, n;
        string name;
        cin >> opt >> name >> n ;
        if( opt == 1 ){
            vector<int> arr(n);
            for(int i=0; i<n; i++)
                cin >> arr[i];
            GORILLA* go = mp[name];
            if( go == nullptr ){
                go = new GORILLA(name);
                mp[name] = go;
            }
            for(int i=0; i<n; i++)
                go->PQ.push(arr[i]);
        }
        else{
            GORILLA* tr = mp[name];
            if( tr == nullptr ) continue;
            while( !tr->PQ.empty() && n ){
                ans += tr->PQ.top(); tr->PQ.pop();
                n -= 1;
            }
        }

    }
    cout << ans ;

    return 0;
}
