#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, root, ans = 0;
vector<int> v, tree[55];
void eraseNode(int current, int target){
    for(auto &next : tree[current]){
        if(next == target){
            next = -1;
        }else
            eraseNode(next, target);
    }
}
void DFS(int current){
    if(tree[current].empty()) {
        ans += 1;
        return;
    }
    bool canGo = false;
    for(auto next : tree[current]){
        if(next != -1){
            canGo = true;
            DFS(next);
        }
    }
    if(!canGo) ans += 1;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N;
    v.resize(N);
    for(int i=0; i<N; i++){
        cin >> v[i];
        tree[v[i]].push_back(i);
        if(v[i] == -1)
            root = i;
    }
    int k;
    cin >> k;
    if(k == root)
        ans = 0;
    else {
        eraseNode(root, k);
        DFS(root);
    }
    cout << ans ;
    return 0;
}
