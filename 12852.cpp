#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int num ;
    cin >> num ;


    bool visited[3][1000001] = {false,};
    queue<pair<vector<int>, int>> Q;

    vector<int> v;
    v.push_back(num);

    Q.push({v, num});
    while(!Q.empty()){
        pair<vector<int>,int> front = Q.front(); Q.pop();
        vector<int> path = front.first;
        int current = front.second;

        if(current == 1){
            cout << path.size() - 1<< '\n';
            for(auto elem : path)
                cout << elem << ' ';
            break;
        }

        if( current % 3 == 0 && !visited[0][current/3] ){
            vector<int> tmp = path ;
            tmp.push_back(current/3);
            visited[0][current/3] = true;
            Q.push({tmp,current/3});
        }
        if( current % 2 == 0 && !visited[1][current/2] ){
            vector<int> tmp = path;
            tmp.push_back(current/2);
            visited[1][current/2] = true;
            Q.push({tmp, current/2});
        }
        if( current > 1 && !visited[2][current-1] ){
            vector<int> tmp = path;
            tmp.push_back(current-1);
            visited[2][current-1] = true;
            Q.push({tmp, current-1});
        }

    }

    return 0;
}
