#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, M;
vector<ll> arr, minSegmentTree, maxSegmentTree;
ll initMinTree(int node, int nodeS, int nodeE){
    if( nodeS == nodeE )
        return minSegmentTree[node] = arr[nodeS];
    int nodeM = (nodeS + nodeE) / 2;
    return minSegmentTree[node] = min(initMinTree(node*2, nodeS, nodeM), initMinTree(node*2+1, nodeM+1, nodeE));
}
ll initMaxTree(int node, int nodeS, int nodeE){
    if( nodeS == nodeE )
        return maxSegmentTree[node] = arr[nodeS];
    int nodeM = (nodeS + nodeE) / 2;
    return maxSegmentTree[node] = max(initMaxTree(node*2, nodeS, nodeM), initMaxTree(node*2+1, nodeM+1, nodeE));
}
ll findMin(int node, int s, int e, const int l, const int r){
    if( r < s || e < l )
        return 2e9;
    if( l <= s && e <= r )
        return minSegmentTree[node];
    int m = (s+e)/2;
    return min(findMin(node*2, s, m, l, r), findMin(node*2+1, m+1, e, l, r));
}
ll findMax(int node, int s, int e, const int l, const int r){
    if( r < s || e < l )
        return -2e9;
    if( l <= s && e <= r )
        return maxSegmentTree[node];
    int m = (s+e)/2;
    return max(findMax(node*2, s, m, l, r), findMax(node*2+1, m+1, e, l, r));
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M;
    arr.resize(N);
    int treeSize = 1 << ( (int)ceil(log2(N)) + 1 ) ;
    minSegmentTree.resize(treeSize), maxSegmentTree.resize(treeSize);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    initMinTree(1, 0, N-1);
    initMaxTree(1, 0, N-1);

    for(int i=0; i<M; i++){
        int a, b;
        cin >> a >> b;
        cout << findMin(1, 0, N-1, a-1, b-1) << ' ';
        cout << findMax(1, 0, N-1, a-1, b-1) << '\n';
    }


    return 0;
}