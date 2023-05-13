# edge case: 50, 05
S = input()
dp = [0 for x in range(len(S))]
dp[0] = int(1 <= int(S[0]) <= 26)
if len(S) < 2:
    print(dp[0])
    exit(0)
dp[1] = (S[0] != '0' and 1 <= int(S[1]) <= 26) + (S[0] != '0' and 1 <= int(S[0:2]) <= 26)
for i in range(2, len(S)):
    if 1 <= int(S[i]) <= 26:
        dp[i] += dp[i - 1]
    if 1 <= int(S[i - 1:i + 1]) <= 26 and '1' <= S[i - 1] <= '2':
        dp[i] += dp[i - 2]

print(dp[len(S) - 1] % 1000000)