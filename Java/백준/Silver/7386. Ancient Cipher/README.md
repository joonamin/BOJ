# [Silver V] Ancient Cipher - 7386 

[문제 링크](https://www.acmicpc.net/problem/7386) 

### 성능 요약

메모리: 14236 KB, 시간: 100 ms

### 분류

구현, 문자열, 정렬

### 제출 일자

2025년 7월 5일 17:03:57

### 문제 설명

<p>Ancient Roman empire had a strong government system with various departments, including a secret service department. Important documents were sent between provinces and the capital in encrypted form to prevent eavesdropping. The most popular ciphers in those times were so called <em>substitution cipher</em> and <em>permutation cipher</em>.</p>

<p>Substitution cipher changes all occurrences of each letter to some other letter. Substitutes for all letters must be different. For some letters substitute letter may coincide with the original letter. For example, applying substitution cipher that changes all letters from '<code>A</code>' to '<code>Y</code>' to the next ones in the alphabet, and changes '<code>Z</code>' to '<code>A</code>', to the message "<code>VICTORIOUS</code>" one gets the message "<code>WJDUPSJPVT</code>". </p>

<p>Permutation cipher applies some permutation to the letters of the message. For example, applying the permutation <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c27E8"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c35"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c34"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c33"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c37"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c36"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c31"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c39"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="2"><mjx-c class="mjx-c38"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c27E9"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo fence="false" stretchy="false">⟨</mo><mn>2</mn><mo>,</mo><mn>1</mn><mo>,</mo><mn>5</mn><mo>,</mo><mn>4</mn><mo>,</mo><mn>3</mn><mo>,</mo><mn>7</mn><mo>,</mo><mn>6</mn><mo>,</mo><mn>10</mn><mo>,</mo><mn>9</mn><mo>,</mo><mn>8</mn><mo fence="false" stretchy="false">⟩</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$\langle 2, 1, 5, 4, 3, 7, 6, 10, 9, 8\rangle$</span></mjx-container> to the message "<code>VICTORIOUS</code>" one gets the message "<code>IVOTCIRSUO</code>".</p>

<p>It was quickly noticed that being applied separately, both substitution cipher and permutation cipher were rather weak. But when being combined, they were strong enough for those times. Thus, the most important messages were first encrypted using substitution cipher, and then the result was encrypted using permutation cipher. Encrypting the message "<code>VICTORIOUS</code>" with the combination of the ciphers described above one gets the message "<code>JWPUDJSTVP</code>".</p>

<p>Archeologists have recently found the message engraved on a stone plate. At the first glance it seemed completely meaningless, so it was suggested that the message was encrypted with some substitution and permutation ciphers. They have conjectured the possible text of the original message that was encrypted, and now they want to check their conjecture. They need a computer program to do it, so you have to write one.</p>

### 입력 

 <p>Input file contains two lines. The first line contains the message engraved on the plate. Before encrypting, all spaces and punctuation marks were removed, so the encrypted message contains only capital letters of the English alphabet. The second line contains the original message that is conjectured to be encrypted in the message on the first line. It also contains only capital letters of the English alphabet. </p>

<p>The lengths of both lines of the input file are equal and do not exceed~<mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>100</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$100$</span></mjx-container>.</p>

### 출력 

 <p>Output "<code>YES</code>" if the message on the first line of the input file could be the result of encrypting the message on the second line, or "<code>NO</code>" in the other case.</p>

