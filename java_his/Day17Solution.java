package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day17Solution {
    public static void main(String[] args) {
        Day17Solution solution = new Day17Solution();
        // String[] strs = {"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"};
        int[] nums = {1, 3, 10};
        // System.out.println(Arrays.toString(solution.sortArrayByParity(nums)));
        System.out.println(solution.smallestRangeI(nums, 4));
    }

    // 908
    public int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        if ((max - min) <= 2 * K) {
            return 0;
        }
        return max - min - 2 * K;
    }

    // 905
    public int[] sortArrayByParity(int[] A) {
        int index = A.length - 1;
        for (int i = 0; i <= index; i++) {
            if (A[i] % 2 == 1) {
                while (index > i && A[index] % 2 != 0) {
                    index--;
                }
                int tmp = A[i];
                A[i] = A[index];
                A[index] = tmp;
            }
        }
        return A;

        // 来试个骚气的
        /*Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int a : A) {
            if (a % 2 == 0) {
                A[count++] = a;
            } else {
                stack.add(a);
            }
        }
        while (!stack.isEmpty()) {
            A[count++] = stack.pop();
        }
        return A;*/
    }

    // 896
    public boolean isMonotonic(int[] A) {
        int up = -1, down = -1, pre = A[0];
        for (int a : A) {
            if (a > pre) {
                up = 1;
                if (down > 0) {
                    return false;
                }
            } else if (a < pre) {
                down = 1;
                if (up > 0) {
                    return false;
                }
            }
            pre = a;
        }
        return true;
    }

    // 893
    public int numSpecialEquivGroups(String[] A) {
        Set<String> seen = new HashSet();
        for (String S : A) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i) {
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            }
            seen.add(Arrays.toString(count));
        }
        return seen.size();
        // 效率太低
        /*Set<Map<String, Integer>> sets = new HashSet<>();
        for (String a : A) {
            Map<String, Integer> map = new HashMap<>();
            // Map<String, Integer> map = new TreeMap<>();
            for (int i = 0; i < a.length(); i++) {
                int tmp = 0;
                if (i % 2 == 1) {
                    tmp = 1;
                }
                String key = "" + a.charAt(i) + tmp;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            System.out.println(map.toString());
            sets.add(map);
        }
        System.out.println(Arrays.toString(sets.toArray()));
        return sets.size();*/
    }

    // 892 减法操作
    public int surfaceArea(int[][] grid) {
        int touchFaces = 0, count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                count += grid[i][j];
                if (grid[i][j] > 0) {
                    touchFaces += grid[i][j] - 1;
                    if (i > 0) {
                        touchFaces += Math.min(grid[i - 1][j], grid[i][j]);
                    }
                    if (j > 0) {
                        touchFaces += Math.min(grid[i][j - 1], grid[i][j]);
                    }
                }
            }
        }
        return 6 * count - 2 * touchFaces;
    }

    // 888
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            sumA += a;
        }
        for (int b : B) {
            sumB += b;
            set.add(b);
        }
        int diff = (sumA - sumB) / 2;
        for (int a : A) {
            if (set.contains(a - diff)) {
                return new int[]{a, a - diff};
            }
        }
        throw null;
    }

    // 884
    public String[] uncommonFromSentences(String A, String B) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (String word : A.split(" ")) {
            if (!set2.contains(word)) {
                if (set1.contains(word)) {
                    set2.add(word);
                    set1.remove(word);
                } else {
                    set1.add(word);
                }
            }
        }
        for (String word : B.split(" ")) {
            if (!set2.contains(word)) {
                if (set1.contains(word)) {
                    set2.add(word);
                    set1.remove(word);
                } else {
                    set1.add(word);
                }
            }
        }
        return set1.toArray(new String[0]);
    }

    // 883
    public int projectionArea(int[][] grid) {
        int[] x = new int[grid.length];
        int[] y = new int[grid[0].length];
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                x[i] = Math.max(grid[i][j], x[i]);
                y[j] = Math.max(grid[i][j], y[j]);
                area++;
            }
        }
        for (int value : x) {
            area += value;
        }
        for (int value : y) {
            area += value;
        }
        return area;
    }
}