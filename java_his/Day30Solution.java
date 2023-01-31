package xin.jiuzhiqian.study.leetcode_history;


import java.util.*;

/**
 * @author : feng
 */
public class Day30Solution {
    int xorRes = 0;

    public static void main(String[] args) {
        Day30Solution solution = new Day30Solution();
        // String res = solution.sortSentence("Myself2 Me1 I4 and3");
        // int[] nums = {1, 2, 3, 4, 5};
        // int res = solution.getMinDistance(nums, 5, 3);
        // int[][] logs = {{1993, 1999}, {2000, 2010}};
        // int res = solution.maximumPopulation(logs);
        // int res = solution.countGoodSubstrings("aababcabc");
        // int[] nums = {5, 1, 6};
        // int res = solution.subsetXORSum(nums);
        // boolean res = solution.checkZeroOnes("110100010");
        // int[][] ranges = {{1, 7}, {3, 4}};
        // boolean res = solution.isCovered(ranges, 2, 5);
        // boolean res = solution.isSumEqual("acb", "cba", "cdb");
        // int[][] mat = {{0, 1}, {1, 0}};
        // int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        // int[][] target = {{1, 0}, {0, 1}};
        // int[][] target = {{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
        // boolean res = solution.findRotation(mat, target);
        // int[] nums = {105, 924, 32, 968};
        // boolean res = solution.canBeIncreasing(nums);
        // String[] nums = {"abc", "aabc", "bc"};
        // boolean res = solution.makeEqual(nums);
        // String res = solution.largestOddNumber("2244");
        // int res = solution.countTriples(9);
        // int[] nums = {4, 2, 5, 9, 7, 4, 8};
        // int res = solution.maxProductDifference(nums);
        // int[] nums = {0, 2, 1, 5, 3, 4};
        // int[] res = solution.buildArray(nums);
        boolean res = solution.areOccurrencesEqual("aaabb");
        System.out.println(res);
        // System.out.println(Arrays.toString(res));
    }

    // 1941
    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        for (char c : s.toCharArray()) {
            hash.put(c, hash.getOrDefault(c, 0) + 1);
        }
        int n = 0;
        for (char c : hash.keySet()) {
            if (n == 0) {
                n = hash.get(c);
            }
            if (hash.get(c) != n) {
                return false;
            }
        }
        return true;
    }

    // 1920
    public int[] buildArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] += 10000 * (nums[nums[i]] % 10000);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] /= 10000;
        }
        return nums;

        /*int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;*/
    }

    // 1913
    public int maxProductDifference(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max2) {
                if (num > max1) {
                    max2 = max1;
                    max1 = num;
                } else {
                    max2 = num;
                }
            }
            if (num < min2) {
                if (num < min1) {
                    min2 = min1;
                    min1 = num;
                } else {
                    min2 = num;
                }
            }
        }
        return max1 * max2 - min1 * min2;
    }

    // 1925
    public int countTriples(int n) {
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                double aa = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                if (aa - (int) aa == 0 && aa <= n) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 1903
    public String largestOddNumber(String num) {
        int offset = num.length();
        for (int i = 0; i < num.length(); i++) {
            if ((num.charAt(offset - 1) - '0') % 2 == 1) {
                break;
            }
            offset--;
        }
        return num.substring(0, offset);
    }

    // 1897
    public boolean makeEqual(String[] words) {
        int[] arr = new int[26];
        int cnt = words.length;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                arr[c - 'a']++;
            }
        }
        for (int num : arr) {
            if (num % cnt != 0) {
                return false;
            }
        }
        return true;
    }

    // 1909
    public boolean canBeIncreasing(int[] nums) {
        int pre = nums[0];
        boolean isDel = false, res1 = true, res2 = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= pre) {
                if (isDel) {
                    res1 = false;
                    break;
                } else {
                    isDel = true;
                    if (i > 1 && nums[i] <= nums[i - 2]) {
                        res1 = false;
                        break;
                    }
                }
            }
            pre = nums[i];
        }
        pre = nums[0];
        isDel = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= pre) {
                if (isDel) {
                    res2 = false;
                    break;
                } else {
                    isDel = true;
                }
            } else {
                pre = nums[i];
            }
        }
        return res1 || res2;
    }

    // 1886
    public boolean findRotation(int[][] mat, int[][] target) {
        int len = mat.length;
        boolean b1 = true, b2 = true, b3 = true, b4 = true;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (b1 && mat[i][j] != target[len - j - 1][i]) {
                    b1 = false;
                }
                if (b2 && mat[i][j] != target[len - i - 1][len - j - 1]) {
                    b2 = false;
                }
                if (b3 && mat[i][j] != target[j][len - i - 1]) {
                    b3 = false;
                }
                if (b4 && mat[i][j] != target[i][j]) {
                    b4 = false;
                }
            }
        }
        return b1 || b2 || b3 || b4;
    }

    // 1880
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int code = 0;
        for (int i = 0; i < firstWord.length(); i++) {
            code += (firstWord.charAt(firstWord.length() - i - 1) - 'a') * Math.pow(10, i);
        }
        for (int i = 0; i < secondWord.length(); i++) {
            code += (secondWord.charAt(secondWord.length() - i - 1) - 'a') * Math.pow(10, i);
        }
        for (int i = 0; i < targetWord.length(); i++) {
            code -= (targetWord.charAt(targetWord.length() - i - 1) - 'a') * Math.pow(10, i);
        }
        return code == 0;
    }

    // 1893
    public boolean isCovered(int[][] ranges, int left, int right) {
        /*int[] bucket = new int[51];
        while (left <= right) {
            bucket[left] = 1;
            left++;
        }

        for (int[] range : ranges) {
            while (range[0] <= range[1]) {
                bucket[range[0]] = 0;
                range[0]++;
            }
        }
        for (int n : bucket) {
            if (n > 0) {
                return false;
            }
        }
        return true;*/
        int[] diff = new int[52];   // 差分数组
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
            System.out.println(Arrays.toString(diff));
        }
        System.out.println(Arrays.toString(diff));
        // 前缀和
        int curr = 0;
        for (int i = 1; i <= 50; ++i) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0) {
                return false;
            }
        }
        return true;
    }

    //  1869
    public boolean checkZeroOnes(String s) {
        if (s.isEmpty()) {
            return false;
        }
        int max0 = 0, max1 = 0, curr = 1;
        char pre = s.charAt(0);
        if (pre == '1') {
            max1 = 1;
        } else {
            max0 = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                curr++;
            } else {
                curr = 1;
                pre = s.charAt(i);
            }
            if (s.charAt(i) == '1') {
                max1 = Math.max(max1, curr);
            } else {
                max0 = Math.max(max0, curr);
            }
        }
        return max1 > max0;
    }

    // 1863 看答案都想不明白
    public int subsetXORSum(int[] nums) {
        if (nums.length == 1) return nums[0];
        dfs(nums, 0, 0);
        return xorRes;
    }

    public void dfs(int[] nums, int i, int xor_sum) {
        if (i == nums.length) {
            xorRes += xor_sum;
            return;
        }
        //当前位置要
        dfs(nums, i + 1, xor_sum ^ nums[i]);
        //当前位置不要
        dfs(nums, i + 1, xor_sum);
    }

    // 1876
    public int countGoodSubstrings(String s) {
        int cnt = 0;
        if (s.length() < 3) {
            return cnt;
        }
        char pre1 = s.charAt(0);
        char pre2 = s.charAt(1);
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) != pre1 && s.charAt(i) != pre2 && pre1 != pre2) {
                cnt++;
            }
            if (i % 2 == 0) {
                pre1 = s.charAt(i);
            } else {
                pre2 = s.charAt(i);
            }
        }
        return cnt;
    }

    // 1859
    public String sortSentence(String s) {
        String[] strArr = s.split(" ");
        String[] strArr2 = new String[strArr.length];
        for (String str : strArr) {
            int sort = Integer.parseInt(str.substring(str.length() - 1));
            str = str.substring(0, str.length() - 1);
            if (sort != strArr.length) {
                str += " ";
            }
            strArr2[sort - 1] = str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr2) {
            sb.append(str);
        }
        return sb.toString();
    }

    // 1848
    public int getMinDistance(int[] nums, int target, int start) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                min1 = Math.abs(i - start);
                break;
            }
        }

        for (int j = start; j >= 0; j--) {
            if (nums[j] == target) {
                min2 = Math.abs(j - start);
                break;
            }
        }
        return Math.min(min1, min2);
    }

    // 1854
    public int maximumPopulation(int[][] logs) {
        int[] arr1 = new int[101];
        int[] arr2 = new int[101];
        for (int[] log : logs) {
            arr1[log[0] - 1950]++;
            arr2[log[1] - 1950]++;
        }
        int birth = 0, dead = 0, year = 0, max_alive = 0;
        for (int i = 0; i < 101; i++) {
            birth += arr1[i];
            dead += arr2[i];
            if (birth - dead > max_alive) {
                max_alive = birth - dead;
                year = 1950 + i;
            }
        }
        return year;
    }
}