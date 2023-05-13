import sys
input = sys.stdin.readline
N = int(input())
v = [list(map(int, input().split())) for _ in range(N)]
v.sort()
cur = -0x3f3f3f3f
ans = 0
for s, e in v:
    if e > cur:
        ans += (e - max(s, cur))
        cur = e
print(ans)
