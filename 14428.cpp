#define UNDEFINED (-1)
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, M;
vector<ll> arr;
vector<int> minTree;
int initTree(int node, int s, int e){
    if( s == e )
        return minTree[node] = s;
    int mid = (s+e)/2;
    int left = initTree(node*2, s, mid);
    int right = initTree(node*2+1, mid+1, e);
    int ret = (arr[left] <= arr[right]) ? left : right;
    return minTree[node] = ret;
}
int update(int node, int s, int e, int index, ll val){
    if( s > index || e < index )
        return minTree[node] ;
    if( s == e ){
        // s == index 일 때
        arr[index] = val;
        return s;
    }
    int mid = (s+e)/2;

    int leftChild = update(node*2, s, mid, index, val);
    int rightChild = update(node*2+1, mid+1, e, index, val);
    int ret = leftChild;
    ret = (arr[ret] <= arr[rightChild]) ? ret : rightChild;
    return minTree[node] = ret;
}
void prt(){
    cout << "\nPrint Tree\n";
    for(int i=1; i<4*N; i++){
        cout << minTree[i] << ' ';
    }
    cout << "\n--------------------\n";
}
int findMinIdx(int node, int s, int e, int l, int r){
    if( r < s || e < l ){
        return UNDEFINED;
    }
    if( l <= s && e <= r ){
        return minTree[node];
    }
    int mid = (s+e)/2;
    int leftChild = findMinIdx(node*2, s, mid, l, r );
    int rightChild = findMinIdx(node*2+1, mid+1, e, l, r);
    int ret;
    if( leftChild == UNDEFINED && rightChild == UNDEFINED )
        return UNDEFINED;

    if( leftChild != UNDEFINED && rightChild == UNDEFINED )
        ret = leftChild;
    else if( leftChild == UNDEFINED && rightChild != UNDEFINED )
        ret = rightChild;
    else if( leftChild != UNDEFINED && rightChild != UNDEFINED ){
        ret = ( arr[leftChild] <= arr[rightChild] ) ? leftChild : rightChild;
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    arr.resize(N);

    for(int i=0; i<N; i++)
        cin >> arr[i];

    // initializing segment Tree
    minTree.resize(4*N);
    initTree(1, 0, N-1);
    cin >> M;
    for(int i=0; i<M; i++){
        ll opt, a, b;
        cin >> opt >> a >> b;
        if( opt == 1 ){
            // update ath value as b
            update(1, 0, N-1, a-1, b);
        }
        else if( opt == 2 ){
            // print min element's index in [a, b]
            cout << findMinIdx(1, 0, N-1, a-1, b-1) + 1 << '\n';
        }

//        cout << "opt : " << ( (opt==1)?"update":"print" ) << ", a : " <<  a-1 << ", b : " << b << " 수행 후";
//        prt();
    }


    return 0;
}