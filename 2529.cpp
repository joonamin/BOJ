#include <bits/stdc++.h>
using namespace std;
int K;
vector<char> op;
vector<string> answer;
bool visited[10] = {false, };
void DFS(int op_idx, string str){
    if( op_idx == K ){
        if(str.size() == K+1)
            answer.push_back(str);
        return ;
    }
    char c = op[op_idx];
    int prev = str[op_idx] - '0';
    for(int i=0; i<10; i++){
        if( visited[i] ) continue;
        if( c == '<' && prev < i ) {
            string new_str = str + (char)('0' + i );
            visited[i] = true;
            DFS(op_idx+1, new_str );
            visited[i] = false;
        }
        else if( c == '>' && prev > i ){
            string new_str = str + (char)('0' + i );
            visited[i] = true;
            DFS(op_idx+1, new_str );
            visited[i] = false;
        }
    }

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> K;
    for(int i=0; i<K; i++) {
        char in;
        cin >> in;
        op.push_back(in);
    }

    for(int i=0; i<10; i++){
        visited[i] = true;
        string str;
        str.push_back('0'+i);
        DFS(0,str);
        visited[i] = false;
    }

    sort(answer.begin(), answer.end());
    cout << answer[answer.size()-1] << '\n';
    cout << answer[0];

    return 0;
}