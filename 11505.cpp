#define MOD 1000000007LL
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, M, K;
vector<ll> arr, tree;
ll initTree(int node, int s, int e){
    if( s == e ){
        return tree[node] = arr[s] ;
    }
    int m = (s+e)/2;
    return tree[node] = ( (initTree(node*2, s, m) % MOD) * (initTree(node*2+1, m+1, e) % MOD) ) % MOD;
}
ll update(int node, int nS, int nE, int idx, int val){
    if( nS > idx || nE < idx )
        return tree[node];
    // 해당 노드는 idx를 포함하므로, 업데이트 대상
    if( nS == nE )
        return tree[node] = val;

    int nM = (nS + nE) / 2;
    return tree[node] = ( (update(node*2, nS, nM, idx, val)% MOD) * (update(node*2+1, nM+1, nE, idx, val) % MOD) ) % MOD;
}
ll getProd(int node, int nS, int nE, int l, int r){
    if( r < nS || nE < l )
        return 1LL; // 항등원
    if( l <= nS && nE <= r )
        return tree[node] % MOD;
    int nM = (nS+nE)/2;
    return ( getProd(node*2, nS, nM, l, r) * getProd(node*2+1, nM+1, nE, l, r) ) % MOD;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> K;
    arr.resize(N), tree.resize(4*N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    initTree(1, 0, N-1);
    for(int i=0; i<M+K; i++){
        int a, b, c;
        cin >> a >> b >> c;
        if( a == 1 ){
            // update
            update(1, 0, N-1, b-1, c);
        }else if( a == 2 ){
            // getProd
            cout << getProd(1, 0, N-1, b-1, c-1) << '\n';
        }
    }


    return 0;
}
