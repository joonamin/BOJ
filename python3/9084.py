T = int(input())
while T > 0:
    N = int(input())
    coins = list(map(int, input().split(' ')))
    M = int(input())

    dp = [0 for x in range(10001)]
    dp[0] = 1
    for coin in coins:
        for i in range(M + 1):
            if i - coin >= 0:
                dp[i] += dp[i - coin]
    print(dp[M])
    T -= 1