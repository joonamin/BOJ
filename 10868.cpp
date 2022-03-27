#define INF 2e9
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, M;
vector<ll> arr, tree;
ll init(int node, int s, int e){
    if( s == e ){
        return tree[node] = arr[s];
    }
    int mid = (s+e)/2;
    return tree[node] = min( init(node*2, s, mid), init(node*2+1, mid+1, e) );
}
ll findMin(int node, int s, int e, int l, int r){
    if( r < s || e < l )
        return INF;
    if( l <= s && e <= r ){
        return tree[node] ;
    }
    int mid = (s+e) / 2;
    return min( findMin(node*2, s, mid, l, r), findMin(node*2+1, mid+1, e, l, r));
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M;
    arr.resize(N), tree.resize(4*N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    init(1, 0, N-1);

    for(int i=0; i<M; i++){
        int a, b;
        cin >> a >> b;
        ll ret = findMin(1, 0, N-1, a-1, b-1) ;
        cout << ret << '\n';
    }

    return 0;
}