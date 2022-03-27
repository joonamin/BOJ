#include <bits/stdc++.h>
using namespace std;
int N;
int synergy[21][21];
bool startTeamSelected[21];
int ans = 1e9;
void test(){
    for(int i=1; i<=N; i++){
        if(startTeamSelected[i])
            cout << i << ' ';
    }
    cout << "\n--------\n";
}
void select(int idx, int count){
    if( count == N/2 ){
        test();

        int startTeamScore = 0 , linkTeamScore = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if( startTeamSelected[i] && startTeamSelected[j] )
                    startTeamScore += synergy[i][j];
                else if( !startTeamSelected[i] && ! startTeamSelected[j] )
                    linkTeamScore += synergy[i][j];
            }
        }
        int diff = abs( startTeamScore - linkTeamScore );
        if( ans > diff )
            ans = diff;
        return;
    }
    for(int i=idx; i<=N; i++){
        if( startTeamSelected[i] ) continue;

        startTeamSelected[i] = true;
        select(idx+1, count + 1);
        startTeamSelected[i] = false;
    }
}
int main(void){
    cin >> N;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> synergy[i][j];
        }
    }
    select(1,0);
    cout << ans ;

    return 0;
}