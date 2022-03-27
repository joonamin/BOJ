#include <bits/stdc++.h>
using namespace std;
int N, K;
vector<unsigned long long> A,B;
void scaffold(){
    vector<unsigned long long> test;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            test.push_back(A[i]*B[j]);
    }
    sort(test.begin(), test.end());
    cout << "\nCORRECT ANSWER IS : " << test[K-1] << '\n';
    return ;
}
bool isExist(unsigned long long val){
    // O(nlogn) // A[i]를 고정 binary_search 통해서 해당 value가 있는 지 확인
    for(int i=0; i<N; i++){
        unsigned long long v = A[i];
        if( val % v == 0 ) {
            auto canFind = binary_search(B.begin(), B.end(), val / v);
            if( canFind )
                return true;
        }
    }
    return false;
}
bool check(unsigned long long val){
    // test
//    if( val <= 10 )
//        cout << "DEBUG\n";
    // val 보다 작은 값들의 개수를 구한 후, 이 개수가 K-1 개 이상이 아니라면 x
    int ac_cnt = 0;
    for(int i=0; i<N; i++){
        unsigned long long a = A[i];
        double target = (double)val / a;
        // target 초과인 첫번째 인덱스를 구함
        int idx = upper_bound(B.begin(), B.end(), target) - B.begin();
        int cnt = idx ;
        ac_cnt += cnt;


        if( ac_cnt >= K )
            return true;
    }
    return false;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> K;
    A.resize(N), B.resize(N);

    for(int i=0; i<N; i++){
        cin >> A[i];
    }
    for(int i=0; i<N; i++){
        cin >> B[i];
    }
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());

    unsigned long long low = A[0]*B[0], high = A.back() * B.back();
    unsigned long long answer = -1;

    while( low <= high ){
        unsigned long long mid = (low+high)/2;
        // 정렬된 행렬에서 mid 이하의 수가 K-1개 이상 있을까 ? (결정문제)
        // ** 조심해야할 점, mid 값이 행렬에서 정의가 안되는 수 일수도있음. **

        // K-1개 이상 있다면, mid는 행렬에서 K번째 원소 보다는 크거나 같음
        // A*B 조합에 val 가 존재하고, val 미만의 값이 K-1개 이상 있을 경우 이것은 정답해의 후보
        bool flag = isExist(mid);
        if( check(mid) ){
            if( flag ) answer = mid;
            high = mid - 1;
        }
        // 아니라면, mid는 행렬에서 K번째 원소 보다는 작음
        else{
            low = mid + 1;
        }
    }
    cout << answer ;

    //scaffold();

    return 0;
}

/*
5 6
1000000000 1 2 3 4
1000000000 5 6 7 8*/
