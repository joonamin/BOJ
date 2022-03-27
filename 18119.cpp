#include <iostream>
#include <bitset>
#include <vector>

using namespace std;

vector< bitset<26> > input;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N, M;
    cin >> N >> M;

    for(int i=0; i<N; i++){
        string in;
        cin >> in;
        bitset<26> tmp; tmp.reset();

        for(int j=0; j<in.size(); j++){
            int idx = in[j]-'a';
            tmp.set(idx, true);
        }

        input.push_back(tmp);
    }

    bitset<26> mask;
    mask.set();

    for(int i=0; i<M; i++){
        int argc;
        char argv;
        cin >> argc >> argv;

        int idx = (int)(argv - 'a');
        if( argc == 1 ){
            // forget
            mask.set(idx, false);
        }else{
            // remember
            mask.set(idx, true);
        }

        int ans = 0;
        for(int j=0; j<input.size(); j++){
            bitset<26> s = input[j];
            bitset<26> res = s & mask;
            if( s == res )
                ans += 1;
        }
        cout << ans << '\n';

    }
    return 0;
}