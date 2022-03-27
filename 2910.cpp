#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, C;
vector<int> arr;
map<int, int> mp, order;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> C;
    arr.resize(N);


    for(int i=0; i<N; i++){
        cin >> arr[i];
        mp[arr[i]] += 1;
        if( order.find(arr[i]) == order.end() )
            order[arr[i]] = i;
    }

    auto comp = [&](const int& a, const int& b)->bool{
        if( mp[a] == mp[b] ){
            return order[a] < order[b];
        }
        return mp[a] > mp[b];
    };
    sort(arr.begin(), arr.end(), comp);

    for(const auto& item: arr)
        cout << item << ' ';


    return 0;
}
