package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author : zhou
 */
public class Day31Solution {
    public static void main(String[] args) {
        Day31Solution solution = new Day31Solution();
        // int res = solution.getLucky("iiii", 2);
        // boolean res = solution.isThree(81);
        // int res = solution.minTimeToType("zjpc");
        // String s = "a";
        // String[] words = {"aa", "aaaa", "banana"};
        // boolean res = solution.isPrefixString(s, words);
        // int[] nums = {2, 3, -1, 8, 4};
        // int res = solution.findMiddleIndex(nums);
        // int[] nums = {2, 5, 6, 9, 10};
        // int res = solution.findGCD(nums);
        // int[] nums = {87063, 61094, 44530, 21297, 95857, 93551, 9918};
        // int res = solution.minimumDifference(nums, 6);
        // int[] nums = {3, 2, 1, 5, 4};
        // int res = solution.countKDifference(nums, 2);
        // int[][] edges = {{0, 1}, {2, 3}, {3, 5}, {5, 2}};
        // boolean res = solution.validPath(5, edges, 1, 5);
        // int[] nums = {1, 1, 1, 3, 5};
        // int res = solution.countQuadruplets(nums);
        // String res = solution.reversePrefix("abcdefd", 'd');
        // int[] original = {1, 2, 3, 4};
        // int[][] res = solution.construct2DArray(original, 1, 1);
        // String[] operations = {"X++", "++X", "-+X", "X--"};
        // int res = solution.finalValueAfterOperations(operations);
        // int[] nums = {7, 6, 5, 4};
        // int res = solution.maximumDifference(nums);
        // int[] seats = {2, 2, 6, 6};
        // int[] students = {1, 3, 2, 6};
        // int res = solution.minMovesToSeat(seats, students);
        // int res = solution.minimumMoves("XXOX");
        // int[] nums1 = {2, 2, 6, 6};
        // int[] nums2 = {1, 3, 2, 6};
        // int[] nums3 = {1, 3, 2, 6};
        // List<Integer> res = solution.twoOutOfThree(nums1, nums2, nums3);
        // String[] operations = {"d", "b", "c", "b", "c", "a"};
        // String res = solution.kthDistinct(operations, 2);
        // boolean res = solution.areNumbersAscending("sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s");
        // int res = solution.countValidWords("alice and  bob are playing stone-game10");
        // int res = solution.countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener.");
        // String word1 = "abcdeef";
        // String word2 = "abaaacc";
        // boolean res = solution.checkAlmostEquivalent(word1, word2);
        // int[] nums = {7, 8, 3, 5, 2, 6, 3, 1, 1, 4, 5, 4, 8, 7, 2, 0, 9, 9, 0, 5, 7, 1, 6};
        // int res = solution.smallestEqual(nums);
        // String word = "duuebuaeeeeeeuaoeiueaoui";
        // int res = solution.countVowelSubstrings(word);
        String[] words1 = {"a","ab"};
        String[] words2 = {"a","a","a","ab"};
        int res = solution.countWords(words1, words2);
        System.out.println(res);
        // System.out.println(Arrays.deepToString(res));
    }

    // 2085
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words1) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String word : words2) {
            if (map.getOrDefault(word, 0) != 1) {
                map.remove(word);
                continue;
            }
            map.put(word, map.get(word) - 1);
        }
        int cnt = 0;
        for (String key : map.keySet()) {
            if (map.get(key) == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    // 2062
    public int countVowelSubstrings(String word) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for (int i = 0; i < word.length() - 4; i++) {
            if (!set.contains(word.charAt(i))) {
                continue;
            }
            Set<Character> set2 = new HashSet<>();
            set2.add('a');
            set2.add('e');
            set2.add('i');
            set2.add('o');
            set2.add('u');
            for (int j = i; j < word.length(); j++) {
                if (!set.contains(word.charAt(j))) {
                    break;
                }
                set2.remove(word.charAt(j));
                if (set2.isEmpty()) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 2057
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    // 2068
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] arr = new int[26];
        for (char c : word1.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            arr[c - 'a']--;
        }
        for (int n : arr) {
            if (n > 3 || n < -3) {
                return false;
            }
        }
        return true;
    }

    // 2047
    public int countValidWords(String sentence) {
        int ans = 0;
        String pattern = "[a-z]+-?[a-z]*[ -!.,]?$";
        for (String str : sentence.split(" ")) {
            if (Pattern.matches(pattern, str)) {
                ans++;
            }
        }
        return ans;
    }

    // 2042
    public boolean areNumbersAscending(String s) {
        /*int index = 0;
        int[] arr = new int[100];
        for (String str : s.split(" ")) {
            int num;
            try {
                num = Integer.parseInt(str);
            } catch (NumberFormatException exception) {
                continue;
            }
            arr[index++] = num;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                return true;
            }
            if (arr[i] <= arr[i - 1]) {
                return false;
            }
        }
        return false;*/
        // token 要么是一个由数字 0-9 组成的不含前导零的正整数,要么是一个由小写英文字母组成的 单词
        String[] arr = s.split(" ");
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > 0 && arr[i].charAt(0) >= '0' && arr[i].charAt(0) <= '9') {
                if (Integer.parseInt(arr[i]) <= min) {
                    return false;
                } else {
                    min = Integer.parseInt(arr[i]);
                }
            }
        }
        return true;
    }

    // 2053
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (String str : arr) {
            if (map.get(str) == 1) {
                k--;
                if (k == 0) {
                    return str;
                }
            }
        }
        return "";
    }

    // 2032
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> res = new ArrayList<>();
        int[] arr = new int[101];
        for (int num : nums1) {
            arr[num] += 1;
        }
        for (int num : nums2) {
            arr[num] += 1000;
        }
        for (int num : nums3) {
            arr[num] += 1000000;
        }
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int t = (num / 1000000 > 0 ? 1 : 0) + (num % 1000000 / 1000 > 0 ? 1 : 0) + (num % 1000 > 0 ? 1 : 0);
            if (t > 1) {
                res.add(i);
            }
        }
        return res;
    }

    // 2027
    public int minimumMoves(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                ans++;
                i += 2;
            }
        }
        return ans;
    }

    // 2037
    public int minMovesToSeat(int[] seats, int[] students) {
        int ans = 0;
        Arrays.sort(seats);
        Arrays.sort(students);
        for (int i = 0; i < seats.length; i++) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }

    // 2016
    public int maximumDifference(int[] nums) {
        int min = nums[0], max = -1;
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                max = Math.max(nums[i] - min, max);
            }
        }
        return max;
    }

    // 2011
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String operation : operations) {
            ans += (44 - operation.charAt(1));
        }
        return ans;
    }

    // 2022
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < original.length; i++) {
            ans[i / n][i % n] = original[i];
        }
        return ans;
    }

    // 2000
    public String reversePrefix(String word, char ch) {
        StringBuilder sb = new StringBuilder();
        boolean isRev = false;
        for (char c : word.toCharArray()) {
            sb.append(c);
            if (c == ch && !isRev) {
                sb.reverse();
                isRev = true;
            }
        }
        return sb.toString();
    }

    // 1995
    public int countQuadruplets(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    // 1971 GG
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            if (edge[0] == edge[1]) {
                continue;
            }
            if (map.containsKey(edge[1])) {
                map.get(edge[1]).add(edge[0]);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(edge[0]);
                map.put(edge[1], set);
            }
        }
        return getValid(map, source, destination, false);
    }

    private boolean getValid(Map<Integer, Set<Integer>> map, int source, int destination, boolean res) {
        if (res) {
            return true;
        }
        if (!map.containsKey(destination)) {
            return false;
        }
        if (map.get(destination).contains(source)) {
            return true;
        }
        for (Object num : map.get(destination).toArray()) {
            res = getValid(map, source, (int) num, false);
            if (res) {
                return true;
            }
        }
        return false;
    }

    // 2006
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            ans += map.getOrDefault(num, 0);
            map.put(num - k, map.getOrDefault(num - k, 0) + 1);
            map.put(num + k, map.getOrDefault(num + k, 0) + 1);
        }
        return ans;
    }

    // 1984
    public int minimumDifference(int[] nums, int k) {
        if (k < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            min = Math.min(min, nums[i + k - 1] - nums[i]);
        }
        return min;

        // 选6名取最大-最小的最小值，非两用户见最小值
        /*if (k < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int min2 = Integer.MAX_VALUE;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                min2 = Math.min(min2, Math.abs(nums[i] % 10000 - nums[j] % 10000));
            }
            nums[i] += min2 * 10000;
        }
        System.out.println(Arrays.toString(nums));
        for (int num : nums) {
            min = Math.min(min, num / 10000);
        }
        return min;*/
    }

    // 1979
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        for (int i = min; i >= 1; i--) {
            if (min % i == 0 && max % i == 0) {
                return i;
            }
        }
        return 1;
    }

    // 1991
    public int findMiddleIndex(int[] nums) {
        int total = 0, left = 0;
        for (int n : nums) {
            total += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                left += nums[i - 1];
            }
            total -= nums[i];
            if (left == total) {
                return i;
            }
        }
        return -1;
    }

    // 1967
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                ans++;
            }
        }
        return ans;
    }

    // 1961
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() >= s.length()) {
                break;
            }
            sb.append(word);
        }
        return sb.toString().equals(s);
    }

    // 1974
    public int minTimeToType(String word) {
        char pre = 'a';
        int ans = 0;
        for (char chr : word.toCharArray()) {
            ans += 1;
            ans += Math.min((chr - pre + 26) % 26, (pre - chr + 26) % 26);
            pre = chr;
        }
        return ans;
    }

    // 1952
    public boolean isThree(int n) {
        if (Math.sqrt(n) != (int) Math.sqrt(n)) {
            return false;
        }
        int powN = (int) Math.sqrt(n);
        for (int i = 2; i < powN; i++) {
            if (powN % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 1945
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int total = 0;
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        for (int i = 0; i < k; i++) {
            total = 0;
            if (sb.length() <= 1) {
                return Integer.parseInt(sb.toString());
            }
            for (char c : sb.toString().toCharArray()) {
                total += c - '0';
            }
            sb = new StringBuilder();
            sb.append(total);
        }
        return total;
    }
}