#include <bits/stdc++.h>
using namespace std;
int N;
int synergy[21][21] ;
bool startTeamSelected[21];
int ans = 1e9;
void select(int idx, int cnt ){
    if( cnt == 0 ){
        // linkTeam, startTeam 시너지의 총합 계산
        int linkTeamSynergy = 0, startTeamSynergy = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( startTeamSelected[i] && startTeamSelected[j] )
                    startTeamSynergy += synergy[i][j];
                else if( !startTeamSelected[i] && !startTeamSelected[j] )
                    linkTeamSynergy += synergy[i][j];
            }
        }
        int diff = abs( linkTeamSynergy - startTeamSynergy );
        if( diff < ans )
            ans = diff;
        return ;
    }
    for(int i = idx ; i<N ; i++){
        if( startTeamSelected[i] ) continue;
        startTeamSelected[i] = true;
        select(i+1, cnt - 1);
        startTeamSelected[i] = false;
    }
    return ;
}
int main(void){
   // freopen("input.txt","r",stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N;
    // 1~N중에서 N/2개를 뽑는 모든 경우를 따진다.
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> synergy[i][j];
    }

    select(0, N/2);
    cout << ans;
    return 0;
}