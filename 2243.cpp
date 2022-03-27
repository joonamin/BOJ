#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, A, B, C, cnt[1000001];
ll tree[4000001] = {0, };
ll update(int node, int l, int r, int idx, int val){
    if( idx < l || r < idx )
        return tree[node];
    // [l, r] 까지의 개수의 누적합
    if( l == r )
        return tree[node] = val;
    return tree[node] = update(node*2, l, (l+r)/2, idx, val) + update(node*2+1, (l+r)/2+1, r, idx, val);
}
ll query(int node, int nl, int nr, int l, int r){
    // [l, r] 의 구간 합
    if( nr < l || nl > r )
        return 0;
    if( l <= nl && nr <= r )
        return tree[node];
    return query(node*2, nl, (nl+nr)/2, l, r) + query(node*2+1, (nl+nr)/2+1, nr, l, r);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    while(N--){
        cin >> A >> B;
        if( A == 1 ){
            // [1, k] 의 범위가 B개 이상인 최소의 k
            int k, l = 1, r = 1e6;
            while( l <= r ){
                int mid = (l+r)/2;
                ll val = query(1, 1, 1e6, 1, mid);
                if( val >= B ){
                    k = mid;
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            cout << k << '\n';
            update(1, 1, 1e6, k, cnt[k]-1);
            cnt[k] -= 1;
        }else{
            cin >> C;
            update(1, 1, 1e6, B, cnt[B] + C);
            cnt[B] += C;
        }
    }



    return 0;
}