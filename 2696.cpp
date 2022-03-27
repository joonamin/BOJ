#define UNDEFINED -4000000000LL
#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int T;
    cin >> T;
    while(T--){
        int M;
        cin >> M;
        // 임의의 중간 값이 정의되는 경우 (길이가 홀수일 때)
        // 중간 값이 정의 될 때, 이 중간값을 M_i라고 가정 시
        // M_i보다 작은 집합(Lower), M_i보다 큰 집합(Upper) 로 구분 되고 이 둘의 사이즈는 같다.
        // 원소를 삽입할 때, lower 의 모든 원소가 upper의 모든 원소 보다 크기 위한 조건을 유지해준다.
        // heap의 특성 상, upper을 minHeap으로 구현하고, upper에 새로운 원소가 들어간다고 가정했을 때
        // adjusting 하는 방법이 더 간단하다.
        // upper을 M_i보다 크거나 같은 집합으로 재정의하자.
        priority_queue<int, vector<int>> lower; // -> maxHeap
        priority_queue<int, vector<int>, greater<>> upper; // -> minHeap

        if( M & 1 ) cout << M/2 + 1 << '\n';
        else cout << M/2 << '\n';

        for(int i=0; i<M; i++){
            int elem;
            cin >> elem ;
            upper.push(elem);
            lower.push(upper.top()); upper.pop();
            if( lower.size() > upper.size() ) {
                upper.push(lower.top()); lower.pop();
            }
            if( i % 2 == 0 )
                cout << upper.top() << ' ';
        }
        cout << '\n';
    }

    return 0;
}