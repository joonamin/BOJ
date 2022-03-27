#include <bits/stdc++.h>
using namespace std;
vector<int> arr;
long long answer = 0;
void counting(int l, int m, int r){
    int i = l, j = m+1;
    int k = 0;
    vector<int> tmp(r-l+1);
    // 시간복잡도를 줄이기 위해
    while(i <= m && j <= r ){
        if( arr[i] > arr[j] ) {
            answer += ( m - i + 1 );
            tmp[k++] = arr[j];
            j += 1;
        }
        else{
            tmp[k++] = arr[i];
            i += 1;
        }
    }
    if( m < i ){
        while( j <= r )
            tmp[k++] = arr[j++];
    }
    else{
        while( i <= m )
            tmp[k++] = arr[i++];
    }
    for(int i=l, k=0; i<=r; i++){
        arr[i] = tmp[k++];
    }
}
void partition(int l, int r){
    if( l >= r )
        return;

    int m = (l+r)/2;

    partition(l, m);
    partition(m+1, r);

    counting(l, m, r);
}
// 부분 정렬 까지 수행해야하는 이유 ?? :
// 3,5,1,2,4 => 구간에서 개수를 카운팅하기 위한 시간복잡도 줄이기 위함, 정렬 x시 최대 N^2

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N ;
    cin >> N;
    arr.resize(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    partition(0, N-1);

    cout << answer ;


    return 0;
}