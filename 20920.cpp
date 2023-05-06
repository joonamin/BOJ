#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;



int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    auto comp = [](const pair<string,int>& p1, const pair<string,int>& p2) {
        if (p1.second == p2.second) {
            if (p1.first.size() == p2.first.size()) {
                return p1.first < p2.first;
            }
            return p1.first.size() > p2.first.size();
        }
        return p1.second > p2.second;
    };

    map<string, int> freq;

    int N, M;
    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        string word;
        cin >> word;
        if (word.length() >= M) {
            freq[word] += 1;
        }
    }

    vector<pair<string,int>> v;
    for (const auto &[key, value] : freq) {
        v.emplace_back(key, value);
    }

    sort(v.begin(), v.end(), comp);
    for (const auto& [str, fr] : v) {
        cout << str << '\n';
    }

    return 0;
}
