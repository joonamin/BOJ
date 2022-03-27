#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, parent[3001], rnk[3001];
class Line{
public:
    pair<ll, ll> st, dest;
    bool isIntersect(Line op);
};
ll CCW(pair<ll,ll> a, pair<ll,ll> b, pair<ll,ll> c){
    // ab벡터를 기준으로 ac 벡터가 반시계 방향이면 +1 리턴
    ll ret = ( a.first * b.second + b.first * c.second + c.first * a.second ) -
             ( c.first * b.second + c.second * a.first + b.first * a.second );
    if( ret > 0 )
        return 1LL;
    if( ret < 0 )
        return -1LL;
    return 0LL;
}
int find(int u){
    if( parent[u] == u )
        return u;
    return parent[u] = find(parent[u]);
}
void _union(int u, int v){
    u = find(u), v = find(v);
    if( u == v )
        return;
    if( rnk[u] > rnk[v] )
        swap(u, v);
    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v]++;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    vector<Line> lines(N);
    for(int i=0; i<N; i++){
        cin >> lines[i].st.first >> lines[i].st.second;
        cin >> lines[i].dest.first >> lines[i].dest.second;
        parent[i] = i;
    }

    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            if( lines[i].isIntersect(lines[j]) )
                _union(i, j);
        }
    }

    map<int,int> mp;
    for(int i=0; i<N; i++){
        int groupID = find(i);
        mp[groupID] += 1;
    }
    cout << mp.size() << '\n';

    int maxPerGroup = 0, suchGroupID;
    for(auto item: mp){
        if( item.second > maxPerGroup ){
            maxPerGroup = item.second;
            suchGroupID = item.first;
        }
    }

    int cnt = 0;
    for(int i=0; i<N; i++){
        if( find(i) == suchGroupID )
            cnt += 1;
    }
    cout << cnt << '\n';

    return 0;
}
bool Line::isIntersect(Line op) {
    ll CCW1 = CCW(st, dest, op.st) * CCW(st, dest, op.dest);
    ll CCW2 = CCW(op.st, op.dest, st) * CCW(op.st, op.dest, dest);
    if( CCW1 <= 0 && CCW2 <= 0 ){
        if( CCW1 == 0 && CCW2 == 0 ){
            // 상대적인 위치 관계로 포함여부 확인
            if( st > dest )
                swap(st, dest);
            if( op.st > op.dest )
                swap(op.st, op.dest);
            if( (st <= op.dest && op.st <= dest) || (op.st <= dest && st <= op.dest))
                return true;
            else
                return false;
        }
        return true;
    }
    return false;
}