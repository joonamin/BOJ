#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N, M;
    cin >> N >> M;
    vector<int> arr1(N), arr2(M);
    for(int i=0; i<N; i++)
        cin >> arr1[i];
    for(int i=0; i<M; i++)
        cin >> arr2[i];

    sort(arr1.begin(), arr1.end());
    sort(arr2.begin(), arr2.end());

    vector<int> ans;
    for(int i=0; i<N; i++){
        int& target = arr1[i];
        if(!binary_search(arr2.begin(), arr2.end(), target))
            ans.push_back(target);
    }

    cout << ans.size() << '\n';
    for(auto item: ans) cout << item << ' ';

    return 0;
}