#include <bits/stdc++.h>
using namespace std;
struct Node{
    int cost;
    vector<int> in, out; // 선행되는 정점(in), 이 후 정점(out)
};
vector<int> answer;
vector<Node> nodes;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N; // 수행해야 할 작업
    cin >> N;
    answer.resize(N+1);
    nodes.resize(N+1);

    // in-edge, out-edge!!
    for(int i=1; i<=N; i++){
        int cost, precedence_cnt;
        cin >> cost >> precedence_cnt;

        nodes[i].cost = cost;

        while(precedence_cnt--){
            int precedence_vertex;
            cin >> precedence_vertex;

            nodes[i].in.push_back(precedence_vertex);
            nodes[precedence_vertex].out.push_back(i);
        }
    }

    // 조심해야해! in-edge가 0인 정점들을 제거할 때 cost가 작은 것을 먼저 지워야한다!!
    // 그냥 priority queue로 구현해보자.

    // PQ 의 원소 -> (cost, 정점)
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;
    for(int i=1; i<=N; i++){
        if( nodes[i].in.empty() ){
            PQ.push({nodes[i].cost, i});
            answer[i] = nodes[i].cost;
        }
    }

    while(!PQ.empty()){
        // Cycle이 존재하는 그래프는 주어지지 않는다.
        pair<int,int> top = PQ.top(); PQ.pop();
        // 해당 정점과 연결 되어 있는 간선들을 모두 끊는다.
        int current_vertex = top.second;

        // 해당 노드들은 current_vertex 로 부터 들어오는 in-edge가 없어진다.
        vector<int> hasToErase;
        for(auto item : nodes[current_vertex].out)
            hasToErase.push_back(item);

        for(int i=0; i<hasToErase.size(); i++){
            int target_vertex = hasToErase[i];
            // target의 in-edge중 current_vertex를 지운다.
            for(auto iter = nodes[target_vertex].in.begin(); iter != nodes[target_vertex].in.end(); iter++){
                if( *iter == current_vertex){
                    nodes[target_vertex].in.erase(iter);
                    break;
                }
            }
            if( nodes[target_vertex].in.empty() ){
                answer[target_vertex] = answer[current_vertex] + nodes[target_vertex].cost;
                PQ.push({answer[target_vertex], target_vertex});
            }
        }
    }

    int min_time = 0;
    for(int i=1; i<=N; i++){
        if( min_time < answer[i] )
            min_time = answer[i];
    }
    cout << min_time;

    return 0;
}
