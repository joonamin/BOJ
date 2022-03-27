#include <iostream>
#include <cmath>
using namespace std;
long long answer = 0 ;
int N,R,C;
void find(int n, int sy, int sx){
    if( n == 0 || ( sy == R && sx == C )){
        return ;
    }
    int size = pow(2,n-1);

    // 왼쪽 상단
    if( sy <= R && R < sy+size && sx <= C && C < sx + size ){
        find(n-1, sy, sx );
    }
    // 오른쪽 상단
    else if( sy <= R && R < sy+size && sx+size <= C && C < sx + 2*size ){
        answer += size*size;
        find( n-1, sy, sx+size );
    }
    // 왼쪽 하단
    else if( sy+size <= R && R < sy + 2*size && sx <= C && C < sx + size ){
        answer += 2*size*size;
        find( n-1, sy+size, sx );
    }
    // 오른쪽 하단
    else{
        answer += 3*size*size;
        find( n-1, sy+size, sx+size );
    }
    return;
}
int main(void){
    cin >> N >> R >> C;
    // 2차원 배열은 항상 2^N * 2^N의 모양

    // 재귀적으로 1개의 칸이 나올 때 까지 4분할을 한다.
    find(N,0,0);
    cout << answer ;
    return 0;
}