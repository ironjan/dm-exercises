# Solution

## 1. Does the web page sequence < index, teaching > exist in any of the students’ browsing records? If yes, for whom?

A, B, E

## 2. What is the support count of < index >?

3

## 3. What is the support count of < index, dm >?

2

## 4. What is the support of < index, courses >?

3

## 5. List all 1-sequences (sequences containing exactly one page visit) in the data, and calculate their support counts.

A: < {index} {teaching} {courses} {dm} >
B: < {index} {teaching} {courses} {courses} {dm} >
C: < {dm} {courses} {dm} >
D: < {courses} >
E: < {index} {teaching} {courses} >


## 6. Assume (only) the following 2-sequences, and fill in the support for the sequences where they do not exist.

< courses, courses > -- support 0.2
< courses, dm >
< courses, index >
< courses, teaching > -- 0.0

< dm, courses >
< dm, dm >
< dm, index > -- support 0.0
< dm, teaching > -- 0.0

< index, courses >
< index, dm > -- 0.4
< index, index > -- support 0.0
< index, teaching >

< teaching, courses > -- 0.6
< teaching, dm >
< teaching, index >
< teaching, teaching > -- 0.0
