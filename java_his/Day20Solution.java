package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day20Solution {
    public static void main(String[] args) {
        Day20Solution solution = new Day20Solution();
        int[] aa = {1, 5, 2, 0, 6, 8, 0, 6, 0};
        int[] ab = {0};
        System.out.println(Arrays.toString(solution.distributeCandies(60, 4)));
        // solution.duplicateZeros(aa);
        // solution.duplicateZeros(ab);
        // System.out.println(solution.gcdOfStrings("ABABABAB", "ABAB"));
        // System.out.println(Arrays.deepToString(solution.allCellsDistOrder(2, 2, 0, 1)));
    }

    // 1103
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int index = 0;
        while (candies > index) {
            ans[index++ % num_people] += index;
            candies -= index;
        }
        System.out.println(candies);
        System.out.println(index);
        if (candies > 0) {
            ans[index % num_people] += candies;
        }
        return ans;
    }

    // 1089
    public void duplicateZeros(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count++;
            }
        }
        int ptr = arr.length - 1;
        while (count != 0) {
            if (arr[ptr] == 0) {
                count--;
                if (ptr + count < arr.length) {
                    arr[ptr + count] = 0;
                }
                if (ptr + 1 + count < arr.length) {
                    arr[ptr + 1 + count] = 0;
                }
            } else {
                if (ptr + count < arr.length) {
                    arr[ptr + count] = arr[ptr];
                }
            }
            ptr--;
        }
    }

    // 1078
    public String[] findOcurrences(String text, String first, String second) {
        String[] arr = text.split(" ");
        List<String> ans = new ArrayList<>();
        boolean matchFirst = false, matchSecond = false;
        for (String word : arr) {
            if (matchFirst) {
                if (matchSecond) {
                    matchSecond = false;
                    ans.add(word);
                    if (!word.equals(first)) {
                        matchFirst = false;
                    }
                    if (matchFirst && word.equals(second)) {
                        matchSecond = true;
                    }
                } else if (word.equals(second)) {
                    matchSecond = true;
                } else if (!word.equals(first)) {
                    matchFirst = false;
                }
            } else if (word.equals(first)) {
                matchFirst = true;
            }
        }
        return ans.toArray(new String[0]);
    }

    // 1071
    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) {
            gcdOfStrings(str2, str1);
        }
        int leftLen = str1.length(), currIndex = 0;
        while (leftLen >= str2.length()) {
            String tmp = str1.substring(currIndex, currIndex + str2.length());
            if (!tmp.equals(str2)) {
                return "";
            }
            leftLen -= str2.length();
            currIndex += str2.length();
        }
        if (leftLen == 0) {
            return str2;
        } else {
            String left = str1.substring(currIndex);
            return gcdOfStrings(str2, left);
        }
    }

    // 1051
    public int heightChecker(int[] heights) {
        int[] he2 = Arrays.copyOf(heights, heights.length);
        Arrays.sort(he2);
        int count = 0;
        for (int i = 0; i < he2.length; i++) {
            if (he2[i] != heights[i]) {
                count++;
            }
        }
        return count;
    }

    // 1047
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == S.charAt(i)) {
                stack.pop();
            } else {
                stack.add(S.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    // 1046
    public int lastStoneWeight(int[] stones) {
        // 大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int stone : stones) {
            priorityQueue.offer(stone);
        }
        int curr = 0;
        while (!priorityQueue.isEmpty()) {
            curr = priorityQueue.poll();
            System.out.println(curr);
            if (priorityQueue.isEmpty()) {
                break;
            }
            int nextPoll = priorityQueue.poll();
            if (nextPoll != curr) {
                priorityQueue.add(curr - nextPoll);
            }
            curr = 0;
        }
        return curr;
    }

    // 1042 todo
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[][] topo = new int[N + 1][3];
        for (int[] cur : paths) {
            int temp = 0;
            while (topo[cur[0]][temp] != 0) {
                temp++;
            }
            topo[cur[0]][temp] = cur[1];
            temp = 0;
            while (topo[cur[1]][temp] != 0) {
                temp++;
            }
            topo[cur[1]][temp] = cur[0];
        }
        int[] res1 = new int[N + 1];
        int[] res = new int[N];
        for (int i = 1; i <= N; i++) {
            int temp = 1;
            while (res1[topo[i][0]] == temp || res1[topo[i][1]] == temp || res1[topo[i][2]] == temp) {
                temp++;
            }
            res1[i] = temp;
        }
        if (N >= 0) {
            System.arraycopy(res1, 1, res, 0, N);
        }
        return res;
    }

    // 1037
    public boolean isBoomerang(int[][] points) {
        long[] lo = new long[4];
        lo[0] = points[1][0] - points[0][0];
        lo[1] = points[1][1] - points[0][1];
        lo[2] = points[2][0] - points[0][0];
        lo[3] = points[2][1] - points[0][1];
        if (lo[0] * lo[3] == lo[1] * lo[2]) {
            return false;
        }
        return true;
    }

    // 1030 傻逼题目,判断顺序不对不给通过
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int maxR = Math.max(R - r0, r0 + 1);
        int maxC = Math.max(C - c0, c0 + 1);
        int[][] ans = new int[maxR * maxC][2];
        int index = 0;
        System.out.println(maxR);
        System.out.println(maxC);
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                ans[index][0] = i;
                ans[index++][1] = j;
            }
        }
        return ans;
    }
}