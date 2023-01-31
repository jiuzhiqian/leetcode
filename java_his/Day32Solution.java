package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day32Solution {
    public static void main(String[] args) {
        Day32Solution solution = new Day32Solution();
        // int[] tickets = {5, 1, 1, 1};
        // int res = solution.timeRequiredToBuy(tickets, 0);
        // int[] colors = {1,8,3,8,3};
        // int res = solution.maxDistance(colors);
        // int[] nums = {-1, -2, 4, 3};
        // int[] res = solution.maxSubsequence(nums, 3);
        // int[] nums = {1, 2, 5, 2, 3};
        // List<Integer> res = solution.targetIndices(nums, 2);
        // int[] digits = {2, 2, 8, 8, 2};
        // int[] digits = {2, 1, 3, 0};
        // int[] res = solution.findEvenNumbers(digits);
        // String[] sentences = {"please wait", "continue to fight", "continue to win"};
        // int res = solution.mostWordsFound(sentences);
        // String rings = "B7R5B3G5B1R2B8";
        // int res = solution.countPoints(rings);
        // String[] words = {"abc", "car", "ada", "racecar", "cool"};
        // String res = solution.firstPalindrome(words);
        // String title = "First leTTeR of EACH Word";
        // String res = solution.capitalizeTitle(title);
        // int num = 123;
        // boolean res = solution.isSameAfterReversals(num);
        // String s = "bbb";
        // boolean res = solution.checkString(s);
        // int[] cost = {6, 5, 7, 9, 2, 2};
        // int res = solution.minimumCost(cost);
        // int[][] matrix = {{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        // boolean res = solution.checkValid(matrix);
        // String[] res = solution.divideString("ctoyjrwtngqwt", 8, 'x');
        // int res = solution.minimumSum(4032);
        // int[] nums = {-3, 3, 3, 90};
        // int res = solution.countElements(nums);
        // int[] nums = {4};
        // int res = solution.findFinalValue(nums, 4);
        // int[] nums = {4, 1, 2, 3, 5};
        // int[] res = solution.sortEvenOdd(nums);
        // List<String> res = solution.fizzBuzz(15);
        boolean res = solution.repeatedSubstringPattern("aab");
        System.out.println(res);
        // System.out.println(Arrays.toString(res));
    }

    // 459
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len < 2) {
            return false;
        }
        int i = 1;
        boolean ans = false;
        while (i < len && !ans) {
            if (len % i != 0) {
                i++;
                continue;
            }
            ans = true;
            int j = len / i;
            String s2 = s.substring(0, i);
            for (int k = 1; k < j; k++) {
                if (!s.substring(k * i, k * i + i).equals(s2)) {
                    ans = false;
                    break;
                }
            }
            i++;
        }
        return ans;
    }

    // 412
    public List<String> fizzBuzz(int n) {
        List<String> list = new LinkedList<>();
        int index = 1;
        while (index <= n) {
            if (index % 15 == 0) {
                list.add("FizzBuzz");
            } else if (index % 5 == 0) {
                list.add("Buzz");
            } else if (index % 3 == 0) {
                list.add("Fizz");
            } else {
                list.add(index + "");
            }
            index++;
        }
        return list;
    }

    // 2164
    public int[] sortEvenOdd(int[] nums) {
        int[] left = new int[nums.length / 2 + (nums.length % 2 == 0 ? 0 : 1)];
        int[] right = new int[nums.length / 2];
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                left[i / 2] = nums[i];
            } else {
                right[i / 2] = nums[i];
            }
        }
        Arrays.sort(left);
        Arrays.sort(right);
        for (int i = 0; i < left.length; i++) {
            nums[i * 2] = left[i];
            if (right.length > i) {
                nums[i * 2 + 1] = right[right.length - i - 1];
            }
        }
        return nums;
    }

    // 2154
    public int findFinalValue(int[] nums, int original) {
        boolean[] array = new boolean[1001];
        for (int i : nums) {
            array[i] = true;
        }
        int cur = original;
        while (cur <= 1000 && array[cur]) {
            cur *= 2;
        }
        return cur;

        /*Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (!set.contains(original)) {
            return original;
        }
        original *= 2;
        while (set.contains(original)) {
            original *= 2;
        }
        return original;*/
    }

    // 2148
    public int countElements(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, maxCnt = 0, minCnt = 0;
        for (int num : nums) {
            if (num < min) {
                min = num;
                minCnt = 1;
            } else if (num == min) {
                minCnt++;
            }
            if (num > max) {
                max = num;
                maxCnt = 1;
            } else if (num == max) {
                maxCnt++;
            }
        }
        if (min == max) {
            return 0;
        } else {
            return nums.length - minCnt - maxCnt;
        }
    }

    // 2160
    public int minimumSum(int num) {
        int[] arr = new int[10];
        arr[num / 1000]++;
        arr[num % 1000 / 100]++;
        arr[num % 100 / 10]++;
        arr[num % 10]++;
        int ten = 2;
        int individual = 2;
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (ten > 0) {
                    min += 10 * i;
                    ten--;
                } else if (individual > 0) {
                    min += i;
                    individual--;
                }
            }
        }
        return min;
    }

    // 2138
    public String[] divideString(String s, int k, char fill) {
        int mod = s.length() % k;
        int len = s.length() / k + (mod == 0 ? 0 : 1);
        String[] ans = new String[len];
        for (int i = 0; i < ans.length; i++) {
            if (mod > 0 && i == len - 1) {
                StringBuilder sb = new StringBuilder(s.substring(i * k, i * k + mod));
                while (k - mod > 0) {
                    sb.append(fill);
                    mod++;
                }
                ans[i] = String.valueOf(sb);
            } else {
                ans[i] = s.substring(i * k, (i + 1) * k);
            }
        }
        return ans;
        /*int mod = s.length() % k;
        int len = s.length() / k + (mod == 0 ? 0 : 1);
        String[] ans = new String[len];
        for (int i = 0; i < ans.length - 1; i++) {
            ans[i] = s.substring(i * k, i * k + k);
        }
        ans[len - 1] = s.substring(len * k - k);
        while (mod != 0 && mod < k) {
            ans[len - 1] = ans[len - 1] + fill;
            mod++;
        }
        return ans;*/


        /*Arrays.fill(ans, "");
        for (int i = 0; i < s.length(); i++) {
            ans[i / k] = ans[i / k] + s.charAt(i);
        }
        while (mod != 0 && mod < k) {
            ans[ans.length - 1] = ans[ans.length - 1] + fill;
            mod++;
        }
        return ans;*/
    }

    // 2133
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            boolean[] row = new boolean[n];
            boolean[] col = new boolean[n];

            for (int j = 0; j < n; j++) {
                row[matrix[i][j] - 1] = true;
                col[matrix[j][i] - 1] = true;
            }
            for (int k = 0; k < n; k++) {
                if (!(row[k] && col[k])) {
                    return false;
                }
            }
        }
        return true;

        /*for (int i = 0; i < matrix.length; i++) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int j = 0; j < matrix[0].length; j++) {
                set.add(matrix[i][j]);
                set2.add(matrix[j][i]);
            }
            if (set.size() != matrix.length || set2.size() != matrix.length) {
                return false;
            }
        }
        return true;*/
    }

    // 2144
    public int minimumCost(int[] cost) {
        int[] arr = new int[101];
        for (int n : cost) {
            arr[n]++;
        }
        int cnt = 0, total = 0;
        for (int i = 100; i > 0; i--) {
            while (arr[i] > 0) {
                arr[i]--;
                if (++cnt % 3 == 0) {
                    continue;
                }
                total += i;
            }
        }
        return total;

        /*Arrays.sort(cost);
        int ans = 0, cnt = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            if (++cnt % 3 == 0) {
                continue;
            }
            ans += cost[i];
        }
        return ans;*/
    }

    // 2124
    public boolean checkString(String s) {
        boolean isB = false;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                isB = true;
            } else {
                if (isB) {
                    return false;
                }
            }
        }
        return true;
    }

    // 2119
    public boolean isSameAfterReversals(int num) {
        return num % 10 != 0;
    }

    // 2129
    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 2) {
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                sb.append(words[i].toLowerCase().substring(1)).append(" ");
            } else {
                sb.append(words[i].toLowerCase()).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    // 2108
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            int left = 0, right = word.length() - 1;
            boolean check = true;
            while (left < right) {
                if (word.charAt(left++) != word.charAt(right--)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                return word;
            }
        }
        return "";
    }

    // 2103
    public int countPoints(String rings) {
        int[] arr = new int[10];
        for (int i = 0; i < rings.length(); i += 2) {
            int t1 = rings.charAt(i + 1) - '0';
            int t2 = rings.charAt(i);
            if (t2 == 'R') {
                arr[t1] += 1000000;
            } else if (t2 == 'G') {
                arr[t1] += 1000;
            } else {
                arr[t1] += 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            if (arr[i] > 1000000 && arr[i] % 1000000 > 1000 && arr[i] % 1000 > 0) {
                ans++;
            }
        }
        return ans;

        /*Map<Integer, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < rings.length(); i += 2) {
            int tmp = rings.charAt(i + 1) - '0';
            if (map.containsKey(tmp)) {
                map.get(tmp).add(rings.charAt(i));
            } else {
                Set<Character> set = new HashSet<>();
                set.add(rings.charAt(i));
                map.put(tmp, set);
            }
        }
        int ans = 0;
        for (int key : map.keySet()) {
            if (map.get(key).size() == 3) {
                ans++;
            }
        }
        return ans;*/
    }

    // 2114
    public int mostWordsFound(String[] sentences) {
        int max = Integer.MIN_VALUE;
        for (String sentence : sentences) {
            max = Math.max(max, sentence.split(" ").length);
        }
        return max;
    }

    // 2094
    public int[] findEvenNumbers(int[] digits) {
        int[] arr = new int[10];
        for (int digit : digits) {
            arr[digit]++;
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i += 2) {
            if (arr[i] == 0) {
                continue;
            }
            arr[i]--;
            for (int j = 1; j < 10; j++) {
                if (arr[j] == 0) {
                    continue;
                }
                arr[j]--;
                for (int k = 0; k < 10; k++) {
                    if (arr[k] == 0) {
                        continue;
                    }
                    list.add(100 * j + 10 * k + i);
                }
                arr[j]++;
            }
            arr[i]++;
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    // 2089
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, cnt = 0;
        for (int num : nums) {
            if (num < target) {
                less++;
            } else if (num == target) {
                cnt++;
            }
        }
        List<Integer> ans = new LinkedList<>();
        for (int i = 0; i < cnt; i++) {
            ans.add(i + less);
        }
        return ans;

        /*Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        List<Integer> ans = new LinkedList<>();
        while (left <= right) {
            if (nums[left] == target && nums[right] == target) {
                break;
            }
            if (nums[left] != target) {
                left++;
            }
            if (nums[right] != target) {
                right--;
            }
        }
        while (left <= right) {
            ans.add(left++);
        }
        return ans;*/
    }

    // 2099
    public int[] maxSubsequence(int[] nums, int k) {
        int index = 0, len = nums.length - 1;
        int[] arr = nums.clone();
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[len - i], map.getOrDefault(nums[len - i], 0) + 1);
        }
        int[] ans = new int[k];
        for (int num : arr) {
            if (map.getOrDefault(num, 0) > 0) {
                map.put(num, map.get(num) - 1);
                ans[index++] = num;
            }
        }
        return ans;

        // 不要求连续,此法作废
        /*if (k < 1 || nums.length < k) {
            return new int[0];
        }
        int maxKey = 0, max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        int curr = max;
        for (int i = 1; i < nums.length - k + 1; i++) {
            curr += nums[i + k - 1] - nums[i - 1];
            if (curr > max) {
                maxKey = i;
                max = curr;
            }
        }
        return Arrays.copyOfRange(nums, maxKey, maxKey + k);*/
    }

    // 2078
    public int maxDistance(int[] colors) {
        int left = 0, right = colors.length - 1, base = colors[0];
        if (colors[right] != base) {
            return right;
        }
        while (left <= right) {
            if (colors[left++] != base) {
                return colors.length - left;
            }
            if (colors[right--] != base) {
                return right + 1;
            }
        }
        return -1;
    }

    // 2073
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0, kCnt = tickets[k];
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                time += Math.min(tickets[i], kCnt);
            } else {
                time += Math.min(tickets[i], kCnt - 1);
            }
        }
        return time;
    }
}