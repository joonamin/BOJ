# [Gold III] PPC 만들기 - 31778 

[문제 링크](https://www.acmicpc.net/problem/31778) 

### 성능 요약

메모리: 18480 KB, 시간: 180 ms

### 분류

수학, 그리디 알고리즘, 문자열, 누적 합, 조합론, 두 포인터

### 제출 일자

2026년 1월 19일 16:56:23

### 문제 설명

<p>포닉스에게는 아끼던 문자열 $S$가 있다. $S$는 길이가 $N$이며 알파벳 대문자 <span style="color:#e74c3c;"><code>C</code></span>와 <span style="color:#e74c3c;"><code>P</code></span>만으로 이루어져 있는 문자열이다. 문자열 $S$의 $i$번째 문자는 $S_i$와 같이 나타낸다.</p>

<p>포닉스는 PPC에 참가하는 팀들을 위해 문자열 $S$로 대회장을 장식하려 한다. 포닉스는 대회 전, $S$에 다음과 같은 연산을 최대 $K$번 시행할 수 있다.</p>

<ul>
	<li>$1 \le i < j \le N$인 두 정수 $i$, $j$를 골라 $S_i$와 $S_j$를 바꾼다.</li>
</ul>

<p>포닉스의 목표는 완성된 문자열 $S$에 <strong>PPC 부분문자열</strong>이 가장 많게 하는 것이다. <strong>PPC 부분문자열</strong>의 개수란, $1 \le i<j<k \le N$이고 $S_i=S_j=$<span style="color:#e74c3c;"><code>P</code></span>, $S_k=$<span style="color:#e74c3c;"><code>C</code></span>인 $(i,j,k)$의 개수를 의미한다.</p>

<p>포닉스가 만들 수 있는 <strong>PPC 부분문자열</strong>의 개수의 최댓값을 구하여라.</p>

### 입력 

 <p>첫 번째 줄에 문자열 $S$의 길이 $N$과 연산의 최대 사용 횟수 $K$가 공백으로 구분되어 주어진다. ($1\le K\le N\le 200\,000$)</p>

<p>두 번째 줄에 길이가 $N$인 문자열 $S$가 주어진다. $S$는 알파벳 대문자 <span style="color:#e74c3c;"><code>C</code></span>와 <span style="color:#e74c3c;"><code>P</code></span>만으로 이루어져 있음이 보장된다.</p>

### 출력 

 <p>포닉스가 만들 수 있는 <strong>PPC 부분문자열</strong>의 개수의 최댓값을 출력한다.</p>

