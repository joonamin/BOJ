class Solution {
    
    private int addTenMinutes(int time) {
        int hour = time / 100;
        int minute = time % 100 + 10;
        if (minute >= 60) {
            hour++;
            minute -= 60;
        }
        return 100 * hour + minute;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            int l = schedules[i], r = addTenMinutes(l);
            boolean flag = true;
            for (int j = 0; j < timelogs[i].length; j++) {
                int day = (j + startday) > 7 ? (j + startday) % 7 : (j + startday);
                if (day == 6 || day == 7) continue;
                if (timelogs[i][j] > r) {
                    flag = false;
                }
            }
            if (flag) {
                answer++;
            }
        }
        
        
        return answer;
    }
}