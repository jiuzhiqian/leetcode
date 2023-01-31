package xin.jiuzhiqian.study.leetcode_history;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : feng
 */
public class Day29Solution {

    public static void main(String[] args) {
        Day29Solution solution = new Day29Solution();
        int[] nums = {12, 17, 15, 13, 10, 11, 12};
        String s1 = "waterbottle", s2 = "terbottlewaw";
        System.out.println(solution.isFlipedString(s1, s2));
    }

    // 01.09
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if ((s1 + s1).contains(s2) && (s2 + s2).contains(s1)) {
            return true;
        }
        return false;
    }

    // 01.06
    public String compressString(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int curr = 1;
        char pre = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                curr++;
            } else {
                sb.append(pre).append(curr);
                pre = s.charAt(i);
                curr = 1;
            }
        }
        sb.append(pre).append(curr);
        return sb.length() > s.length() ? s : sb.toString();
    }

    // 01.03
    public String replaceSpaces(String s, int length) {
        return s.substring(0, length).replace(" ", "%20");
    }

    // 01.02
    public boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
            arr[s2.charAt(i) - 'a']--;
        }
        for (int n : arr) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }

    // 01.01
    public boolean isUnique(String astr) {
        int[] arr = new int[128];
        for (int i = 0; i < astr.length(); i++) {
            if (++arr[astr.charAt(i)] > 1) {
                return false;
            }
        }
        return true;
    }

    // lcp 01
    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                count++;
            }
        }
        return count;
    }

    // 1832
    public boolean checkIfPangram(String sentence) {
        int[] arr = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            arr[sentence.charAt(i) - 'a'] = 1;
        }
        for (int n : arr) {
            if (n < 1) {
                return false;
            }
        }
        return true;
    }

    // 1822
    public int arraySign(int[] nums) {
        int ans = 1;
        for (int n : nums) {
            if (n == 0) {
                return 0;
            } else if (n < 0) {
                ans *= -1;
            }
        }
        return ans;
    }

    // 1816
    public String truncateSentence(String s, int k) {
        if (k < 1) {
            return "";
        }
        int n = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                sb.append(s.charAt(i));
            } else if (++n == k) {
                break;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    // 1827
    public int minOperations(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                ans += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return ans;
    }

    // 1805
    public int numDifferentIntegers(String word) {
        boolean isNum = false, isBegin = true;
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                isNum = true;
                if (isBegin && c == '0') {
                    continue;
                }
                isBegin = false;
                sb.append(c);
            } else {
                if (isNum) {
                    System.out.println(sb.toString());
                    set.add(sb.toString());
                    sb = new StringBuilder();
                }
                isBegin = true;
                isNum = false;
            }
        }
        if (isNum) {
            System.out.println(sb.toString());
            set.add(sb.toString());
        }
        return set.size();
    }

    // 1800
    public int maxAscendingSum(int[] nums) {
        int max = Integer.MIN_VALUE, curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curr += nums[i];
            } else {
                max = Math.max(max, curr);
                curr = nums[i];
            }
        }
        return Math.max(max, curr);
    }

    // 1812
    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) - 'a' + coordinates.charAt(1) - '1') % 2 == 1;
    }

    // 1790
    public boolean areAlmostEqual(String s1, String s2) {
        int count = 0, index = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (++count > 2) {
                return false;
            }
            if (index < 0) {
                index = i;
            } else {
                if (s1.charAt(index) != s2.charAt(i) || s1.charAt(i) != s2.charAt(index)) {
                    return false;
                }
            }
        }
        return count != 1;
    }

    // 1784
    public boolean checkOnesSegment(String s) {
        boolean pre = false;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (!pre) {
                    count++;
                }
                pre = true;
            } else {
                pre = false;
            }
        }
        return count == 1;
    }

    // 1796
    public int secondHighest(String s) {
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            int t = s.charAt(i) - '0';
            if (t < 0 || t > 9) {
                continue;
            }
            if (t > first) {
                second = first;
                first = t;
            } else if (t > second && t < first) {
                second = t;
            }
        }
        return second;
    }

    // 1773
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = 0, count = 0;
        if ("color".equals(ruleKey)) {
            index = 1;
        } else if ("name".equals(ruleKey)) {
            index = 2;
        }
        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                count++;
            }
        }
        return count;
    }
}