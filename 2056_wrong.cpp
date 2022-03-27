/*
 * 이 문제를 틀린 이유
 * 반례 :
 *  3
    100 0
    10 0
    5 2 1 2
 *
 * in-edge가 0이 되는 시점이 반드시 최적해는 아니다.
 * 정확하게 말하자면, 특정 정점을 없앨 때 없애는 순서 또한 중요하다.
 * 먼저 1번 task 를 제거한다고 생각했을 때, 아직 2번 task가 남아있고 이것의 작업 처리 시간은 10이다.
 * 정답은 105 이지만, 이 경우 15를 출력하게된다.
 * in-edge중에서 가장 시간이 오래걸리는 작업이 가장 마지막에 제거 되는 것이 맞다!!
 * !!! 블로그에 잘 정리해서 담아보자.
 */


#include <bits/stdc++.h>
using namespace std;
bool adj[10001][10001];
int in_edge_cnt[10001] = {0, };
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int answer[10001] = {0, };

    int N;
    cin >> N;

    int cost[10001] ;
    for(int i=0; i<N; i++){
        cin >> cost[i];
        int cnt ;
        cin >> cnt ;
        // i 번째 노드의 in-edge들이 주어짐
        for(int j=0; j<cnt; j++){
            int k;
            cin >> k;
            adj[k-1][i] = true;
            in_edge_cnt[i] += 1;
        }
    }

    queue<int> Q;
    for(int i=0; i<N; i++){
        if(in_edge_cnt[i] == 0){
            Q.push(i);
            answer[i] = cost[i];
        }
    }

    // 특정 정점을 erase할 때, in_edge_cnt 가 0 인 노드를 찾아서 이전 노드가 끝난 시간 + cost 를 구하자
    while(!Q.empty()){
        int hasToErase = Q.front(); Q.pop();

        vector<int> chk ; // in-edge 가 0이 될 수도 있는 후보들
        // 지울 정점의 연결 점들을 모두 끊어 준다.
        for(int i=0; i<N; i++){
            if( adj[hasToErase][i] ) {
                adj[hasToErase][i] = false;
                in_edge_cnt[i] -= 1;
                chk.push_back(i);
            }
        }
        // 만약 i번째 노드에 in-edge가 아무것도 없으면, Queue에 추가한다.
        for(int i=0; i<chk.size(); i++){
            int m = chk[i];
            if( in_edge_cnt[m] == 0 ){
                answer[m] = answer[hasToErase] + cost[m];
                Q.push(m);
            }
        }
    }

    int max_val = answer[0];
    for(int i=1; i<N; i++){
        if( max_val < answer[i] )
            max_val = answer[i];
    }
    cout << max_val ;

    return 0;
}