package Programmers.Codekata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class _146_베스트앨범 {

    class Solution {

        class Genre{
            int totalPlay;
            int firstIdx;
            int firstPlay;
            int secondIdx;
            int secondPlay;

            public Genre(int totalPlay, int firstIdx, int firstPlay, int secondIdx, int secondPlay) {
                this.totalPlay = totalPlay;
                this.firstIdx = firstIdx;
                this.firstPlay = firstPlay;
                this.secondIdx = secondIdx;
                this.secondPlay = secondPlay;
            }
        }

        public int[] solution(String[] genres, int[] plays) {
            HashMap<String, Genre> hm = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                String genre = genres[i];
                int play = plays[i];
                if(!hm.containsKey(genre)){
                    Genre g = new Genre(play, i, play, -1, -1);
                    hm.put(genre, g);
                }else{
                    Genre g = hm.get(genre);
                    g.totalPlay += play;
                    if (play > g.firstPlay) {
                        g.secondPlay = g.firstPlay;
                        g.secondIdx = g.firstIdx;
                        g.firstPlay = play;
                        g.firstIdx = i;
                    }else{
                        if (g.secondPlay == -1 || play > g.secondPlay) {
                            g.secondIdx = i;
                            g.secondPlay = play;
                        }
                    }
                    hm.replace(genre, g);
                }
            }

            List<String> keySet = new ArrayList<>(hm.keySet());
            keySet.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return hm.get(o2).totalPlay - hm.get(o1).totalPlay;
                }
            });

            List<Integer> result = new ArrayList<>();

            for (String s : keySet) {
                Genre genre = hm.get(s);
                result.add(genre.firstIdx);
                if (genre.secondIdx != -1) {
                    result.add(genre.secondIdx);
                }
            }
            int[] answer = new int[result.size()];

            for (int i = 0; i < result.size(); i++) {
                answer[i] = result.get(i);
            }
            return answer;
        }
    }

}

/*
문제 분석
1. 정보
    - 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개 씩 모아 베스트 앨범을 출시하려 함.
    - 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같음
        - 속한 노래가 많이 재생된 장르를 먼저 수록
        - 장르 내에서 많이 재생된 노래를 먼저 수록
        - 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록
2. 목표
    - 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때. 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
3. 제약 조건
    - genres[i] = 고유번호가 i인 노래의 장르
    - plays[i] = 고유번호가 i인 노래가 재생된 횟수
    - genres와 plays의 길이는 같고, 1 이상 10000 이하
    - 장르 종류는 100개 미만
    - 장르에 속한 곡이 하나라면, 하나의 곡만 선택
    - 모든 장르는 재생된 횟수가 다름

풀이
1. 아이디어
    - HashMap을 활용함
        - hm<장르,장르 정보>를 수록
        - 장르 정보 = 전체 재생 횟수, 많이 재생된 노래 2개 정보가 들어 있음
        - 모든 장르와 plays를 돌면서 hm에 삽입
            - 만약 해당 장르에 대한 hm이 없다면 새로 만들어 삽입
            - 존재한다면 값을 꺼내 비교
                - 많이 재생된 노래가 비어있다면 새로 만들어 삽입
                - 존재한다면 비교하여 업데이트
                - 전체 노래 재생 횟수 추가
        - 모든 장르를 돌고 난 이후
            - 전체 재생 횟수가 많은 순서대로 answer에 값을 담음
            - answer를 return
*/
