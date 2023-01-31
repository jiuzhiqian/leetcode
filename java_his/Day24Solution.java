package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : feng
 */
public class Day24Solution {

    public static void main(String[] args) {
        Day24Solution solution = new Day24Solution();
        int[][] grid = {{4, 3, 2, 1}, {3, 2, 1, 1}, {1, 1, 1, 2}, {1, 1, 2, 3}};
        int[] nums = {-3, 2, -3, 4, 2};
        System.out.println("hello".indexOf("hea"));
        // int[] index = {0, 1, 2, 2, 1};
        System.out.println(solution.minSubsequence(nums));
        // System.out.println(Arrays.toString(solution.createTargetArray(nums, index)));
    }

    // 1431
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<>();
        int max = candies[0];
        for (int n : candies) {
            max = Math.max(n, max);
        }
        for (int n : candies) {
            if (n + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }

    // 1408
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (j == i) {
                    continue;
                }
                if (words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    // 1403
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int count = 0, count2 = 0;
        for (int n : nums) {
            count += n;
        }
        count /= 2;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans.add(nums[i]);
            count2 += nums[i];
            if (count2 > count) {
                break;
            }
        }
        return ans;
    }

    // 1413
    public int minStartValue(int[] nums) {
        int min = nums[0];
        int count = nums[0];
        for (int i = 1; i < nums.length; i++) {
            count += nums[i];
            min = Math.min(min, count);
        }
        if (min >= 0) {
            return 1;
        } else {
            return 1 - min;
        }
    }

    // 1394
    public int findLucky(int[] arr) {
        int[] arr2 = new int[501];
        for (int j : arr) {
            arr2[j]++;
        }
        for (int i = 500; i > 0; i--) {
            if (arr2[i] == i) {
                return i;
            }
        }
        return -1;
    }

    // 1389
    public int[] createTargetArray(int[] nums, int[] index) {
        for (int i = 1; i < index.length; i++) {
            int j = 0;
            while (j < i) {
                if (index[j] >= index[i]) {
                    index[j]++;
                }
                j++;
            }
        }
        System.out.println(Arrays.toString(index));
        int[] ans = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            ans[index[i]] = nums[i];
        }

        return ans;
    }

    // 1399
    public int countLargestGroup(int n) {
        int ans = 0, max = 1;
        int[] sum = new int[n + 1];
        int[] count = new int[37];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i / 10] + i % 10;
            count[sum[i]]++;
            max = Math.max(count[sum[i]], max);
        }
        for (int c : count) {
            ans += c == max ? 1 : 0;
        }
        return ans;
    }

    // 1380
    public List<Integer> luckyNumbers(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[] rMin = new int[r];
        Arrays.fill(rMin, Integer.MAX_VALUE);
        int[] cMax = new int[c];
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                rMin[i] = Math.min(rMin[i], matrix[i][j]);
                cMax[j] = Math.max(cMax[j], matrix[i][j]);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (matrix[i][j] == rMin[i] && matrix[i][j] == cMax[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }

    // 1374
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            n--;
            sb.append('a');
        }
        while (n > 0) {
            sb.append('b');
            n--;
        }
        return sb.toString();
    }

    // 1385
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int cnt = 0;
        for (int x : arr1) {
            boolean ok = true;
            for (int y : arr2) {
                ok &= Math.abs(x - y) > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    // 1365
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr1 = new int[101];
        int[] arr2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr2);
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] == arr2[i - 1]) {
                continue;
            }
            arr1[arr2[i]] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            arr2[i] = arr1[nums[i]];
        }
        return arr2;
    }

    // 1351
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    break;
                } else {
                    count++;
                }
            }
        }
        return grid.length * grid[0].length - count;
    }

    // 1370
    public String sortString(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        while (len > 0) {
            for (int i = 0; i < 26; i++) {
                if (arr[i] > 0) {
                    arr[i]--;
                    sb.append((char) (i + 'a'));
                    len--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (arr[i] > 0) {
                    arr[i]--;
                    sb.append((char) (i + 'a'));
                    len--;
                }
            }
        }
        return sb.toString();
    }
}