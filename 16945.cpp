#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
// 제발 조건을 잘 읽자
int arr[3][3], temp[3][3], used[9] ={false, }, ans = 1e9;
pair<int,int> val[4];
int check(){
    // 4방향 합은 고정됨
    const int sum = temp[0][1] + temp[1][1] + temp[2][1];
    for(int i=0; i<3; i++){
        int rowSum = 0;
        for(int j=0; j<3; j++){
            rowSum += temp[i][j];
        }
        if(rowSum != sum)
            return -1;
    }
    for(int j=0; j<3; j++){
        int colSum = 0;
        for(int i=0; i<3; i++){
            colSum += temp[i][j];
        }
        if(colSum != sum)
            return -1;
    }

    int ret = 0;
    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            ret += abs(arr[i][j] - temp[i][j]);
        }
    }

    return ret;
}
void select(int cur, const int sum){
    if(cur==4) {
        tie(temp[0][1], temp[2][1]) = val[0];
        tie(temp[0][0], temp[2][2]) = val[1];
        tie(temp[2][0], temp[0][2]) = val[2];
        tie(temp[1][0], temp[1][2]) = val[3];
        // 매직 스퀘어 인지 확인하고 맞으면 비용 리턴
        int cost = check();
        if(cost != -1)
            ans = min(ans, cost);
        return;
    }
    for(int i=1; i<=9; i++){
        for(int j=1; j<=9; j++){
            if(!used[i] && !used[j] && i + j == sum){
                val[cur] = {i, j};
                used[i] = used[j] = true;
                select(cur+1, sum);
                used[i] = used[j] = false;
            }
        }
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++)
            cin >> arr[i][j];
    }

    // center 고정
    for(int center=1; center<=9; center++){
        temp[1][1] = center;
        // 합 고정
        for(int s=3; s<=27; s++){
            // sum = s 일때, center - s 가 되는 두 원소를 찾아야함
            if(s - center < 2) continue;
            // {[0][1], [2][1]}
            // {[0][0], [2][2]}
            // {[2][0], [0][2]}
            // {[1][0], [1][2]}
            select(0, s - center);
        }
    }
    cout << ans ;
    return 0;
}
