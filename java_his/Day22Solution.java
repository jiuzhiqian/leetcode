package xin.jiuzhiqian.study.leetcode_history;


import java.util.*;

/**
 * @author : feng
 */
public class Day22Solution {

    public static void main(String[] args) {
        Day22Solution solution = new Day22Solution();
        // System.out.println(solution.reformatDate("6th Jun 1933"));
        int[] distance = {968, 463, 205, 846, 678, 247, 551, 721, 592, 596};
        // System.out.println(solution.distanceBetweenBusStops(distance, 3, 10));
        // System.out.println(solution.dayOfTheWeek(6, 11, 2020));
        // System.out.println(solution.threeConsecutiveOdds(distance));
        int[][] grid = {{1}, {6}, {9}};
        // ListNode listNode = new ListNode(1, new ListNode(0, new ListNode(1)));
        // System.out.println(solution.getDecimalValue(listNode));
        System.out.println(solution.freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }

    // 1309
    public String freqAlphabets(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                char curr = (char) ((s.charAt(--i) - '0') + (s.charAt(--i) - '1') * 10 + 'j');
                ans.append(curr);
            } else {
                char curr = (char) (s.charAt(i) - '1' + 'a');
                ans.append(curr);
            }
        }
        return ans.reverse().toString();
    }

    // 1304
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int left = 0, right = n - 1, value = n / 2;
        while (left <= right) {
            ans[left++] = -value;
            ans[right--] = value--;
        }
        return ans;
    }

    // 1295
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            String tmp = "" + num;
            if (tmp.length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    // 1290 todo find
    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            System.out.println(head.val);
            sb.append(head.val);
            head = head.next;
        }
        String s = sb.reverse().toString();
        System.out.println(s);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                ans += (int) Math.pow(2, i);
            }
        }
        return ans;
    }

    // 1281
    public int subtractProductAndSum(int n) {
        int product = 1, count = 0;
        while (n > 0) {
            product *= (n % 10);
            count += (n % 10);
            n /= 10;
        }
        return product - count;
    }

    // 1275 todo
    public String tictactoe(int[][] moves) {
        int len = moves.length;
        char[][] cc = new char[3][3];
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                cc[moves[i][0]][moves[i][1]] = 'X';
            } else {
                cc[moves[i][0]][moves[i][1]] = 'O';
            }
            if (i > 3 && check(cc, moves[i])) {
                return i % 2 == 0 ? "A" : "B";
            }
        }
        return len == 9 ? "Draw" : "Pending";
    }

    private boolean check(char[][] cc, int[] xy) {
        return false;
    }

    // 1266
    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        for (int i = 1; i < points.length; i++) {
            time += Math.max(Math.abs(points[i][0] - points[i - 1][0]), Math.abs(points[i][1] - points[i - 1][1]));
        }
        return time;
    }

    // 1260 不是返回数组,原地爆炸 todo
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= (m * n);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < grid[0].length; j++) {
                int dest = i * m + j - k;
                int x = dest / m;
                int y = (dest - m * x + 1) % m;
                if (dest < 0) {
                    dest += (m * n);
                }
                System.out.println(x);
                System.out.println(y);
                list.add(grid[x][y]);
            }
            ans.add(list);
        }
        return ans;

        // 搞反了
        /*for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int dest = i * m + j + k + 1;
                int tmp = grid[i][j];
                if (dest > m * n) {
                    if (k % m != 0) {
                        grid[i][j] = grid[m - 1][n - 1];
                        grid[m - 1][n - 1] = tmp;
                    }
                } else {
                    grid[i][j] = grid[(dest - 1) / m][(dest - 1) % m];
                    grid[(dest - 1) / m][(dest - 1) % m] = tmp;
                }
                list.add(grid[i][j]);
            }
            ans.add(list);
        }
        return ans;*/
    }

    // 1232
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[1][0] - coordinates[0][0];
        int y0 = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0] - coordinates[0][0];
            int y = coordinates[i][1] - coordinates[0][1];
            if (x0 * y != y0 * x) {
                return false;
            }
        }
        return true;
    }


    // 1221
    public int balancedStringSplit(String s) {
        int lCount = 0, rCount = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                lCount++;
            } else {
                rCount++;
            }
            if (lCount == rCount) {
                count++;
            }
        }
        return count;

        // stack
        /*Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                count++;
                stack.add(s.charAt(i));
            } else {
                if (stack.peek() != s.charAt(i)) {
                    stack.pop();
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }
        return count;*/
    }

    // 1217 读不懂题,傻逼题
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int num : position) {
            if ((num & 1) == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }

    // 1207
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int a : arr) {
            hash.put(a, hash.getOrDefault(a, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (int a : hash.keySet()) {
            if (set.contains(hash.get(a))) {
                return false;
            } else {
                set.add(hash.get(a));
            }
        }
        return true;
    }

    // 1200
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            min = Math.min(min, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == min) {
                List<Integer> list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[i + 1]);
                ans.add(list);
            }
        }
        return ans;
    }

    // 1189
    public int maxNumberOfBalloons(String text) {
        int[] tt = new int[26];
        for (int i = 0; i < text.length(); i++) {
            tt[text.charAt(i) - 'a']++;
        }
        int ans = Math.min(tt[0], tt[1]);
        ans = Math.min(ans, tt['n' - 'a']);
        ans = Math.min(ans, tt['l' - 'a'] / 2);
        ans = Math.min(ans, tt['o' - 'a'] / 2);
        return ans;
    }

    // 1550
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] % 2 == 0) {
                i++;
            } else {
                if (arr[i + 1] % 2 == 1) {
                    if (arr[i - 1] % 2 == 1) {
                        return true;
                    }
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    // 1185
    public String dayOfTheWeek(int day, int month, int year) {
        int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        day += 365 * (year - 1971);
        for (int i = 0; i < month; i++) {
            day += monthDays[i];
        }
        for (int i = 1971; i <= year; i++) {
            if (i % 400 == 0 || (i % 100 != 0 && i % 4 == 0)) {
                if (i < year || (i == year && month > 2)) {
                    day++;
                }
            }
        }
        return week[(day + 4) % 7];
    }

    // 1184
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int index = start, len = distance.length, dis1 = 0, dis2 = 0;
        boolean next = false;
        while (index != start + len) {
            int disIndex = index % len;
            if (disIndex == destination || next) {
                dis2 += distance[disIndex];
                next = true;
            } else {
                dis1 += distance[disIndex];
            }
            index = (index + 1);
        }
        return Math.min(dis1, dis2);
    }

    // 1507
    public String reformatDate(String date) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, Integer> monthMap = new HashMap<String, Integer>();
        for (int i = 1; i <= 12; i++) {
            monthMap.put(months[i - 1], i);
        }

        String[] arr = date.split(" ");
        int mon = monthMap.getOrDefault(arr[1], 1);
        int day = Integer.parseInt(arr[0].substring(0, arr[0].length() - 2));
        return String.format("%s-%02d-%02d", arr[2], mon, day);
    }

    // 1175
    public int numPrimeArrangements(int n) {
        if (n < 3) {
            return 1;
        }
        int count = 1;
        for (int i = 2; i < n + 1; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j < n + 1 && j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                count++;
            }
        }
        int primeCount = n - count;
        long res = 1;
        int i = 1;
        while (i <= primeCount) {
            res = (res * i++) % 1000000007;
        }
        i = 1;
        while (i <= count) {
            res = (res * i++) % 1000000007;
        }
        return (int) res;
    }
}