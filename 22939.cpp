#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int N;
vector<pair<int,int>> v[4];
pair<int,int> src, dst;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            char temp;
            cin >> temp;
            if(temp == 'J') v[0].push_back({i, j});
            if(temp == 'C') v[1].push_back({i, j});
            if(temp == 'B') v[2].push_back({i, j});
            if(temp == 'W') v[3].push_back({i, j});
            if(temp == 'H') src = {i, j};
            if(temp == '#') dst = {i, j};
        }
    }
    string ans = "";
    int dist = 1e9;

    for(int i=0; i<4; i++){
        int order[3] = {0, 1, 2};
        int d = 0;
        do{
            // src에서 v[i] 의 order대로 순차적으로 방문 후, dst까지 도착하는데 걸리는 시간
            d = abs(src.first - v[i][order[0]].first) + abs(src.second - v[i][order[0]].second);
            for(int j=1;j<3; j++){
                d += abs(v[i][order[j-1]].first - v[i][order[j]].first) + abs(v[i][order[j-1]].second - v[i][order[j]].second);
            }
            d += abs(v[i][order[2]].first - dst.first) + abs(v[i][order[2]].second - dst.second);
            if(d < dist){
                dist = d;
                if(i == 0)
                    ans = "Assassin";
                if(i == 1)
                    ans = "Healer";
                if(i == 2)
                    ans = "Mage";
                if(i == 3)
                    ans = "Tanker";
            }
        }while(next_permutation(order, order+3));
    }
    cout << ans;

    return 0;
}
