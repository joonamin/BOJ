#include <bits/stdc++.h>
using namespace std;
struct Node{
    Node* next[11];
    bool isFinish;
    Node(){memset(next, 0, sizeof(next)); isFinish = false;}
};
class Trie{
    // Trie의 루트노드는 아무런 값도 가지지 않는다.
    Node* root;
public:
    Trie(){root = new Node();}
    Node* getStart(){ return root; }
    void insert(Node* current, const string& str, int idx){
        if( idx == str.size() ) {
            current->isFinish = true;
            return;
        }
        if( current->next[str[idx]-'0'] == nullptr )
            current->next[str[idx]-'0'] = new Node();
        insert( current->next[str[idx]-'0'], str, idx+1 );
    }
    int find(Node* current, const string& str, int idx){
        if( idx == str.size() )
            return true;
        else if( current->isFinish )
            return false;
        return find(current->next[str[idx]-'0'], str, idx+1);
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int t, n;
    cin >> t;
    while(t--){
        cin >> n;
        Trie trie;
        vector<string> v;
        for(int i=0; i<n; i++){
            string str;
            cin >> str;
            trie.insert(trie.getStart(), str, 0);
            v.push_back(str);
        }
        // 탐색 중, isFinish = true인 것을 발견했다면 이것은 이전에 이미 접두사가 존재한다는 뜻
        bool flag = true;
        for(auto s : v){
            if( !flag ) break;
            flag &= trie.find(trie.getStart(), s, 0);
        }
        if( flag )
            cout << "YES\n";
        else
            cout << "NO\n";
    }

    return 0;
}