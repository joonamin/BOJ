#include <iostream>
#include <vector>
#include <set>

using namespace std;
int N,M;
vector<int> arr;
vector<int> ret;
set< vector<int> > result;
bool visited[10001] = {false,};
void go(int idx){
    if( idx >= M ){
        result.insert(ret);
        return ;
    }

    for(int i=0; i<N; i++){
        if( visited[i] ) continue;

        visited[i] = true;
        ret.push_back(arr[i]);
        go( idx+1 );
        ret.pop_back();
        visited[i] = false;
    }
    return ;
};
int main(void){
    cin >> N >> M;
    arr.resize(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    go(0);

    for( auto it : result ){
        for(int j=0; j<it.size(); j++)
            cout << it[j] << ' ';
        cout << '\n';
    }

    return 0;
}