#define ll long long
#include <bits/stdc++.h>
using namespace std;
template<typename T>
class SegmentTree{
    vector<T> tree;
public:
    vector<T> arr;
    void resize(int N) {
        int h = (int)ceil(log2(N));
        tree.resize((1<<(h+1)) + 1);
        arr.resize(N);
    }
    T init(int node, int s, int e);
    T update(int node, int s, int e, const int idx, const T value);
    T sum(int node, int s, int e, const int left, const int right);
    void prtTree(){
        cout << "\n=============\n";
        for(int i=1; i<tree.size(); i++){
            cout << tree[i] << ' ';
        }
        cout << "\n=============\n";
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    SegmentTree<long long> segmentTree;
    int N, M, K;
    cin >> N >> M >> K;

    segmentTree.resize(N);
    for(int i=0; i<N; i++){
        long long in;
        cin >> in;
        segmentTree.arr[i] = in;
    }
    // 루트는 편의 상 1부터 시작하는 것으로 한다.
    segmentTree.init(1, 0, N-1);

//    // MARK - print Tree
//    segmentTree.prtTree();

    for(int i=0; i<M+K; i++){
        long long a, b, c;
        cin >> a >> b >> c;
        if( a == 1 ){
            // b 번째 수를 c로 바꾼다.
            segmentTree.update(1, 0, N-1, b-1, c);
        }
        else if( a == 2 ){
            // [b,c] 구간의 합을 구한다.
            cout << segmentTree.sum(1, 0, N-1, b-1, c-1) << '\n';
        }
//        segmentTree.prtTree();
    }

    return 0;
}

template<typename T>
T SegmentTree<T>::init(int node, int s, int e) {
    if( s == e ){
        return tree[node] = arr[s];
    }
    int mid = (s+e)/2;
    return tree[node] = init(node*2, s, mid) + init(node*2+1, mid+1, e);
}
template<typename T>
T SegmentTree<T>::update(int node, int s, int e, const int idx, const T value) {
    if( idx < s || e < idx ){
        return tree[node];
    }
    if( s == e )
        return tree[node] = value;
    int mid = (s+e)/2;
    return tree[node] = update(node*2, s, mid, idx, value) + update(node*2+1, mid+1, e, idx, value);
}
template<typename T>
T SegmentTree<T>::sum(int node, int s, int e, const int left, const int right) {
    if( right < s || e < left )
        return 0;
    if( left <= s && e <= right )
        return tree[node];
    int mid = (s+e)/2;
    return sum(node*2, s, mid, left, right) + sum(node*2+1, mid+1, e, left, right);
}