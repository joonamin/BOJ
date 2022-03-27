/*
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int N, M;
vector<int> input;
vector<int> tmp;
vector<vector<int>> ans;
bool isValidAns(){
    // Unique 한 정답인가 ?
    if( ans.empty() ) return true;
    else{
        for(int i=0; i<ans.size(); i++){
            vector<int> element = ans[i];
            bool isAllSame = true;
            for(int j=0; j<element.size(); j++){
                if( element[j] != tmp[j] ) {
                    isAllSame = false;
                    break;
                }
            }
            if( isAllSame ) return false;
        }
        return true;
    }
}
void go( int idx, int cnt ){
    if( cnt == M ){
        // ans 에 이미 정답이 있는 지 확인.
        if( isValidAns() ){
            ans.push_back(tmp);
        }
        return;
    }
    for(int i=idx; i<N; i++){
        tmp.push_back(input[i]);
        go(i, cnt + 1 );
        tmp.pop_back();
    }

}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    input.resize(N);

    for(int i=0; i<N; i ++){
        cin >> input[i];
    }
    sort( input.begin(), input.end() );

    go(0, 0);

    for(int i=0; i<ans.size(); i++){
        for(int j=0; j<ans[i].size(); j++){
            cout << ans[i][j] << ' ';
        }
        cout << '\n';
    }

    return 0;
}*/
