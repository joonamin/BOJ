# [Gold V] Moons and Umbrellas - 22886 

[문제 링크](https://www.acmicpc.net/problem/22886) 

### 성능 요약

메모리: 19236 KB, 시간: 160 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 6월 25일 10:14:29

### 문제 설명

<p>Cody-Jamal is working on his latest piece of abstract art: a mural consisting of a row of waning moons and closed umbrellas. Unfortunately, greedy copyright trolls are claiming that waning moons look like an uppercase C and closed umbrellas look like a J, and they have a copyright on CJ and JC. Therefore, for each time CJ appears in the mural, Cody-Jamal must pay X, and for each time JC appears in the mural, he must pay Y.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/7a04f85f-2dd5-4b51-bfe8-b57dc2cbce50/-/preview/"></p>

<p>Cody-Jamal is unwilling to let them compromise his art, so he will not change anything already painted. He decided, however, that the empty spaces he still has could be filled strategically, to minimize the copyright expenses.</p>

<p>For example, if <code>CJ?CC?</code> represents the current state of the mural, with <code>C</code> representing a waning moon, <code>J</code> representing a closed umbrella, and <code>?</code> representing a space that still needs to be painted with either a waning moon or a closed umbrella, he could finish the mural as <code>CJCCCC</code>, <code>CJCCCJ</code>, <code>CJJCCC</code>, or <code>CJJCCJ</code>. The first and third options would require paying X+Y in copyrights, while the second and fourth would require paying 2⋅X+Y.</p>

<p>Given the costs X and Y and a string representing the current state of the mural, how much does Cody-Jamal need to pay in copyrights if he finishes his mural in a way that minimizes that cost?</p>

### 입력 

 <p>The first line of the input gives the number of test cases, T. T lines follow. Each line contains two integers X and Y and a string S representing the two costs and the current state of the mural, respectively.</p>

### 출력 

 <p>For each test case, output one line containing <code>Case #x: y</code>, where x is the test case number (starting from 1) and y is the minimum cost that Cody-Jamal needs to pay in copyrights for a finished mural.</p>

