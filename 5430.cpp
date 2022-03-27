#include <iostream>
#include <deque>
#include <sstream>
using namespace std;

void R(bool& flag){
    flag = !flag;
}
bool D(deque<int>& dq, bool isRev){
    if( dq.empty() )
        return false;
    if( isRev ) dq.pop_back();
    else dq.pop_front();
    return true;
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int T;
    cin >> T;

    while(T--){
        string p;
        cin >> p;

        int n;
        cin >> n;

        deque<int> data;

        string data_str;
        cin >> data_str;

        string token;
        istringstream iss(data_str.substr(1, data_str.size()-2)); // 맨 앞 [ 와 맨 뒤 ]를 필터링한 string
        for(int i=0; i<n; i++){
            getline(iss, token, ',' ); // ','또는 '\n'앞까지 가져온다.
            data.push_back(stoi(token));
        }

        bool isReverse = false;
        bool isError = false;
        for(auto cmd : p){
            if( cmd == 'R' )
                R(isReverse);
            else if( cmd == 'D' ) {
                if( !D(data, isReverse) ) {
                    isError = true;
                    break;
                }
            }
        }

        if( isError ) cout << "error\n";
        else{
            cout << "[";
            if( !data.empty() ) {
                if (!isReverse) {
                    for (int i = 0; i < data.size() - 1; i++)
                        cout << data[i] << ',';
                    cout << data.back();
                } else {
                    for (int j = data.size() - 1; j >= 1; j--)
                        cout << data[j] << ',';
                    cout << data.front();
                }
            }
            cout << "]\n";
        }
    }

    return 0;
}