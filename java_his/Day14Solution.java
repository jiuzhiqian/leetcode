package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.Employee;

import java.util.*;

/**
 * @author : zhou
 */
public class Day14Solution {
    public static void main(String[] args) {
        Day14Solution solution = new Day14Solution();
        int[] nums = {1, 0, 0};
        // System.out.println(Arrays.toString(solution.findErrorNums(nums)));
        // String s = "LLDDUURR";
        String s = "abcdcbadd";
        System.out.println(solution.isOneBitCharacter(nums));
    }

    // 717
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length < 3) {
            return bits[0] == 0;
        }
        int index = -1;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                index = i;
                break;
            }
        }
        return index < 0 ? (bits.length - 1) % 2 == 0 : (bits.length - 2 - index) % 2 == 0;
    }

    // 697 读不懂题,卧槽,傻逼题
    public int findShortestSubArray(int[] nums) {
        return 0;
    }

    // 693
    public boolean hasAlternatingBits(int n) {
        int a = -1;
        while (n > 0) {
            if (a >= 0 && n % 2 == a) {
                return false;
            }
            a = n % 2;
            n /= 2;
        }
        return true;
    }

    // 690
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, List<Integer>> mapList = new HashMap<>();
        Map<Integer, Integer> mapImportance = new HashMap<>();
        for (Employee employee : employees) {
            mapImportance.put(employee.id, employee.importance);
            mapList.put(employee.id, employee.subordinates);
        }
        return getSum(id, 0, mapList, mapImportance);
    }

    private int getSum(int id, int count, Map<Integer, List<Integer>> mapList, Map<Integer, Integer> mapImportance) {
        if (mapImportance.containsKey(id)) {
            count += mapImportance.get(id);
            if (mapList.get(id).size() != 0) {
                for (int num : mapList.get(id)) {
                    count = getSum(num, count, mapList, mapImportance);
                }
            }
        }
        return count;
    }


    // 682 傻逼题目，读题半天
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newtop = top + stack.peek();
                stack.push(top);
                stack.push(newtop);
            } else if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else {
                stack.push(Integer.valueOf(op));
            }
        }

        int ans = 0;
        for (int score : stack) {
            ans += score;
        }
        return ans;
    }

    // 680
    public boolean validPalindrome(String s) {
        char[] cc = s.toCharArray();
        boolean drome = true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (cc[left] != cc[right]) {
                int len = right - left;
                if (len == 1) {
                    return true;
                }
                char[] cc1 = new char[len];
                char[] cc2 = new char[len];
                System.arraycopy(cc, left, cc1, 0, len);
                System.arraycopy(cc, left + 1, cc2, 0, len);
                return palindrome(cc1) || palindrome(cc2);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean palindrome(char[] cc) {
        int left = 0, right = cc.length - 1;
        while (left < right) {
            if (cc[left++] != cc[right--]) {
                return false;
            }
        }
        return true;
    }

    // 674
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int curr = 0, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                if (curr > 0) {
                    curr++;
                } else {
                    curr = 2;
                }
            } else {
                if (curr > 0) {
                    max = Math.max(curr, max);
                    curr = 0;
                }
            }
        }
        return Math.max(curr, max);
    }

    // 665
    public boolean checkPossibility(int[] nums) {
        if (nums.length < 1) {
            return true;
        }
        boolean a = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (!a) {
                    return false;
                }
                a = false;
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                } else {
                    nums[i] = nums[i + 1];
                }
            }
        }
        return true;
    }

    // 661
    public int[][] imageSmoother(int[][] M) {
        // official
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r - 1; nr <= r + 1; ++nr) {
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                }
                ans[r][c] /= count;
            }
        }
        return ans;

        /*int len1 = M.length;
        if (len1 < 1) {
            return M;
        }
        int len2 = M[0].length;
        int[][] res = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int tmp = 1, count = M[i][j];
                if (i > 0) {
                    tmp++;
                    count += M[i - 1][j];
                    if (j > 0) {
                        count += M[i - 1][j - 1];
                        tmp++;
                    }
                    if (j < len2 - 1) {
                        count += M[i - 1][j + 1];
                        tmp++;
                    }
                }
                if (i < len1 - 1) {
                    tmp++;
                    count += M[i + 1][j];
                    if (j > 0) {
                        count += M[i + 1][j - 1];
                        tmp++;
                    }
                    if (j < len2 - 1) {
                        count += M[i + 1][j + 1];
                        tmp++;
                    }
                }
                if (j > 0) {
                    tmp++;
                    count += M[i][j - 1];
                }
                if (j < len2 - 1) {
                    tmp++;
                    count += M[i][j + 1];
                }
                res[i][j] = count / tmp;
            }
        }
        return res;*/
    }

    // 657
    public boolean judgeCircle(String moves) {
        int[] circle = new int[2];
        for (int i = 0; i < moves.length(); i++) {
            handle(moves.charAt(i), circle);
        }
        return circle[0] == 0 && circle[1] == 0;
    }

    private void handle(char c, int[] circle) {
        switch (c) {
            case 'L':
                circle[0]--;
                break;
            case 'R':
                circle[0]++;
                break;
            case 'U':
                circle[1]--;
                break;
            case 'D':
                circle[1]++;
                break;
            default:
                break;
        }
    }

    // 645
    public int[] findErrorNums(int[] nums) {
        int dup = -1, total = 0, len = nums.length;
        int sum = len * (len + 1) / 2;
        for (int i = 0; i < len; i++) {
            int j = Math.abs(nums[i]);
            total += j;
            if (nums[j - 1] < 0) {
                dup = j;
            } else {
                nums[j - 1] = -nums[j - 1];
            }
        }
        int missing = sum - total + dup;
        return new int[]{dup, missing};
        // not sorted
        /*int correctNum = 0, errNum = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                correctNum = i + 1;
                errNum = nums[i];
            }
        }
        return new int[]{errNum, correctNum};*/
    }
}