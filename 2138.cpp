#include <bits/stdc++.h>
using namespace std;
void flip(string& str, int idx){
    str[idx] = str[idx] == '0' ? '1' : '0';
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    // 첫번째 전구를 고정시켜주자 // i-1 번째가 다르다면 i번째를 flip 시켜주지 않으면, 이 후로는 바꾸지 못한다.
    int N;
    cin >> N;
    string str, target;
    cin >> str >> target;

    string tmp = str;

    int cnt[2] = {0, 1};
    // 첫번째 전구를 켜지 않았을 경우
    for(int i=1; i<N; i++){
        if( str[i-1] != target[i-1] ){
            flip(str,i-1);
            flip(str, i);
            if( i != N-1 ) flip(str,i+1);
            cnt[0] += 1; // 무조건 !! 바꿔줘야 함
        }
    }
    if( str != target )
        cnt[0] = -1;
    // 첫번째 전구를 켰을 경우
    str = tmp;
    flip(str, 0);
    flip(str, 1);
    for(int i=1; i<N; i++){
        if( str[i-1] != target[i-1] ){
            flip(str, i-1);
            flip(str, i);
            if( i != N-1 ) flip(str, i+1);
            cnt[1] += 1;
        }
    }
    if( str != target )
        cnt[1] = -1;

    int answer = -1;
    if( cnt[0] != -1 && cnt[1] != -1 )
        answer = cnt[0] < cnt[1] ? cnt[0] : cnt[1];
    // 둘다 -1 또는 하나가 -1
    if( cnt[0] == -1 && cnt[1] != -1 )
        answer = cnt[1];
    if( cnt[0] != -1 && cnt[1] == -1 )
        answer = cnt[0];

    cout << answer;
    return 0;
}
