#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    // a1 a2 a3 ... ak ak+1 ... an
    // ak가 중간 값이라고 가정하자, (n이 홀수일때)
    // left 구간[1, k-1], right 구간[k, n]
    // left 는 k 보다 작은 원소들, right는 k보다 크거나 같은 원소들
    // 정의에의해) left 의 size는 항상 right보다 같거나 1작다.
    // left의 모든 원소들은 정의에 따라, right의 모든 원소보다 작아야한다.
    // 즉 right의 최솟값 > left의 최댓값 을 만족 시켜준다.

    int N;
    cin >> N;
    priority_queue<int, vector<int>> left;
    priority_queue<int, vector<int>, greater<>> right;

    for(int i=1; i<=N; i++){
        int in; cin >> in;
        right.push(in);

        left.push(right.top());
        right.pop();
        if( left.size() > right.size() ){
            right.push(left.top());
            left.pop();
        }

        if( i & 1 )
            cout << right.top() << '\n';
        else
            cout << min(left.top(), right.top()) << '\n';
    }



    return 0;
}