# [Platinum IV] Island Travels - 5852 

[문제 링크](https://www.acmicpc.net/problem/5852) 

### 성능 요약

메모리: 50856 KB, 시간: 628 ms

### 분류

너비 우선 탐색, 비트마스킹, 데이크스트라, 다이나믹 프로그래밍, 비트필드를 이용한 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색, 최단 경로, 외판원 순회 문제

### 제출 일자

2025년 6월 18일 17:40:22

### 문제 설명

<p>Farmer John has taken the cows to a vacation out on the ocean! The cows are living on N (1 <= N <= 15) islands, which are located on an R x C grid (1 <= R, C <= 50). An island is a maximal connected group of squares on the grid that are marked as 'X', where two 'X's are connected if they share a side. (Thus, two 'X's sharing a corner are not necessarily connected.)</p><p>Bessie, however, is arriving late, so she is coming in with FJ by helicopter. Thus, she can first land on any of the islands she chooses. She wants to visit all the cows at least once, so she will travel between islands until she has visited all N of the islands at least once.</p><p>FJ's helicopter doesn't have much fuel left, so he doesn't want to use it until the cows decide to go home. Fortunately, some of the squares in the grid are shallow water, which is denoted by 'S'. Bessie can swim through these squares in the four cardinal directions (north, east, south, west) in order to travel between the islands. She can also travel (in the four cardinal directions) between an island and shallow water, and vice versa.</p><p>Find the minimum distance Bessie will have to swim in order to visit all of the islands. (The distance Bessie will have to swim is the number of distinct times she is on a square marked 'S'.) After looking at a map of the area, Bessie knows this will be possible.</p>

### 입력 

 <ul><li>Line 1: Two space-separated integers: R and C.</li><li>Lines 2..R+1: Line i+1 contains C characters giving row i of the grid. Deep water squares are marked as '.', island squares are marked as 'X', and shallow water squares are marked as 'S'.</li></ul>

### 출력 

 <ul><li>Line 1: A single integer representing the minimum distance Bessie has to swim to visit all islands.</li></ul>

