#include <bits/stdc++.h>
using namespace std;
vector<int> ac_sum;
int get_sum(int left, int right){
    if( left == 0 ){
        return ac_sum[right];
    }
    return ac_sum[right] - ac_sum[left-1];
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N, S;
    cin >> N >> S;

    vector<pair<int,int>> v;
    ac_sum.resize(N,0);


    vector<int> arr(N);
    int sum = 0;
    for(int i=0; i<N; i++){
        cin >> arr[i];
        sum += arr[i];
        ac_sum[i] = sum;
    }
    sort(arr.begin(), arr.end());

    int left, right;
    left = right = 0;

    while( 0 <= left && left < N && 0 <= right && right < N ){
        int sum = get_sum(left, right);
//        cout << "left : " << left << ", right : " << right << ", Sum = " << sum << '\n';
        if( sum >= S )
            v.push_back({left, right});
        if( sum < S ){
            right += 1;
        }
        else{
            left += 1;
        }
    }

    if( v.empty() )
        cout << 0;
    else{
        int min_len = 1e9;
        for( auto it : v ){
            int len = it.second - it.first + 1 ;
            if( min_len > len )
                min_len = len;
        }
        cout << min_len;
    }

//    for(auto it : v ){
//        cout << it.first << ' ' << it.second << '\n';
//    }

    return 0;
}

