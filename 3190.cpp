#include <iostream>
#include <list>

using namespace std;
enum{
    RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3
};
class SNAKE{
public:
    int direction ;
    list< pair<int,int> > body;
    list< pair<int,int > >::iterator head, tail;
    SNAKE(){
        direction = RIGHT;
        body.push_back({1,1});
        head = tail = body.begin();
    }
};
int N,K;
bool board[101][101]; // true: 사과가 있는 곳
SNAKE snake;
char chgDir[10001] = {'\0', };
const int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};
int cnt = 0;

bool moveSnake(){
    pair<int,int> nPos = *(snake.head);
    int curY = nPos.first , curX = nPos.second;

    // 만약 지금 시간에 방향 변경 요청이 들어올 경우.
    if( chgDir[cnt] == 'D' ){
        snake.direction = (snake.direction + 1) % 4;
    }else if( chgDir[cnt] == 'L' ){
        snake.direction -= 1;
        if( snake.direction < 0 ) snake.direction += 4;
    }

    int ny = curY + dir[snake.direction][0], nx = curX + dir[snake.direction][1];

    // 머리가 화면 밖에 있거나 자기 자신에게 붙는 경우
    if( ny > N || ny <= 0 || nx > N || nx <= 0 ) return false;
    bool isEnd = false;
    for(auto it : snake.body ){
        if( it.first == ny && it.second == nx ){
            isEnd = true;
            break;
        }
    }
    if( isEnd ) return false;

    // 뱀은 먼저 몸길이를 늘려 머리를 다음 칸에 위치 시킨다.
    snake.body.push_front({ny,nx});
    snake.head = snake.body.begin();
    // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
    if( board[snake.head->first][snake.head->second] == 1 ){
        board[snake.head->first][snake.head->second] = 0;
    }
    // ~없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 몸길이는 변화하지 않는다.
    else{
        auto it = snake.tail;
        snake.tail--;
        snake.body.erase(it);
    }

    return true;
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);


    cin >> N >> K;
    for(int i=0; i<K; i++){
        int y,x;
        cin >> y >> x;
        board[y][x] = true;
    }

    int L;
    cin >> L ;

    for(int i=0; i<L; i++){
        int t;
        char d;
        cin >> t >> d;
        chgDir[t] = d;
    }

    while(moveSnake()){
        cnt += 1;
    }
    cout << cnt + 1 ;

    return 0;
}