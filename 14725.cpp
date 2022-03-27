#include <bits/stdc++.h>
using namespace std;
int N;
struct TrieNode{
    string strData;
    map<string, TrieNode*> mp;
    TrieNode(){strData = "", mp.clear();}
};
class Trie{
public:
    TrieNode* root;
    Trie(){root = new TrieNode();}
    void insert(TrieNode* current, string data){
        TrieNode* keyNode = current->mp[data];
        if( keyNode == nullptr ){
            keyNode = new TrieNode();
            current -> mp[data] = keyNode;
        }
        keyNode -> strData = data;
    }
    void prtAllNode(TrieNode* current, int depth){
        if( current == nullptr )
            return;
        for(int i=1; i<depth; i++)
            cout << "--";
        if( current != root )
            cout << current->strData << '\n';

        for(auto item : current->mp){
            prtAllNode(item.second, depth+1);
        }
    }
};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    Trie trie;
    for(int i=0; i<N; i++){
        int k;
        cin >> k;

        TrieNode* current = trie.root;
        for(int j=0; j<k; j++){
            string in;
            cin >> in;
            trie.insert(current, in);
            current = current->mp[in];
        }
    }
    trie.prtAllNode(trie.root, 0);
    return 0;
}
