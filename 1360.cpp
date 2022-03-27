#include <bits/stdc++.h>
using namespace std;

int N;
// 시간대 별로 상태 저장.
vector< pair<string,int> > v;
long long getPrevTime(long long time){
    //parametric search
    long long prevTime = 0 ;
    int left = 0, right = v.size()-1;
    while(left <= right){
        int mid = (left+right)/2;
        if( v[mid].second <= time ){
            prevTime = v[mid].second;
            left = mid + 1;
        }else
            right = mid - 1;
    }
    return prevTime;
}
int main(void){

	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

    cin >> N;
    v.push_back({"", 0});
    for(int i=0; i<N; i++){
        string cmd;
        cin >> cmd ;

        if( cmd == "type" ){
            string factor;
            long long time ;
            cin >> factor >> time ;

            string status = v.back().first;
            v.push_back({status+factor, time});
        }else{
            long long factorInt;
            long long time;
            cin >> factorInt >> time ;

            long long t = time - factorInt - 1;
            if( t < 0 ) t = 0;
            // t초전 상태가 존재하지 않을 수도 있음, t이하 중 최댓값!
            long long tPrev = getPrevTime(t);

            vector<pair<string,int>>::iterator iter ;
            for( iter = v.begin(); iter != v.end(); iter++ ){
                if( iter->second == tPrev )
                    break;
            }
            string status = iter->first ;
            v.push_back({status, time});
        }

    }
    cout << v.back().first;

    return 0;
}
