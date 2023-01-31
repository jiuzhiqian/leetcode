package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;

import java.util.*;

/**
 * @author : zhou
 */
public class Day8Solution {

    public boolean isBadVersion(int version) {
        if (version < 1702766719) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Day8Solution solution = new Day8Solution();
//        String s = "anagram", t = "nagaram";
//        System.out.println(solution.isAnagram(s, t));
//        int num = 2126753390;
        int[] nums = {1, 3, 0, 5, 0, 7, 1};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    // 299
    public String getHint(String secret, String guess) {
        int A = 0, B = 0, len = secret.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
                continue;
            }
            if (map.getOrDefault(guess.charAt(i), 0) > 0) {
                B++;
                map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) - 1);
            }
        }
        return A + "A" + B + "B";
    }

    // 292
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    // 290
    public boolean wordPattern(String pattern, String str) {
        String[] strings = str.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        Map<String, Character> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            if (map.containsKey(strings[i])) {
                if (!map.get(strings[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            } else if (map.containsValue(pattern.charAt(i))) {
                return false;
            } else {
                map.put(strings[i], pattern.charAt(i));
            }
        }
        return true;
    }

    // 283
    public void moveZeroes(int[] nums) {
        int index = 0, zeroCount = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > len - 1 - zeroCount) {
                nums[i] = 0;
                continue;
            }
            int curr = i + index;
            if (nums[curr] == 0) {
                while (nums[curr] == 0 && curr < len - 1) {
                    index++;
                    curr++;
                    zeroCount++;
                }
            }
            nums[i] = nums[i + index];
        }
    }

    // 278
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left / 2 + right / 2;
            if (this.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;


        // GG
        /*if (this.isBadVersion(1)) {
            return 1;
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = right / 2 + left / 2 + 1;
            if (this.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
            if (right == left + 1) {
                return right;
            }
        }
        return right;*/
    }

    // 268 高斯公式...数组/位运算
    public int missingNumber(int[] nums) {
        // 高斯
        int len = nums.length;
        int total = (len + 1) * (len) / 2, numTotal = 0;
        for (int num : nums) {
            numTotal += num;
        }
        return total - numTotal;
    }

    // 263
    public boolean isUgly(int num) {
        int tmp = num;
        while (num > 1) {
            if (num % 5 == 0) {
                num /= 5;
            }
            if (num % 2 == 0) {
                num /= 2;
            }
            if (num % 3 == 0) {
                num /= 3;
            }
            if (tmp == num) {
                return false;
            }
            tmp = num;
        }
        return tmp == 1;
    }

    // 258
    public int addDigits(int num) {
//        return (num - 1) % 9 + 1;
        int sum = num;
        while (sum >= 10) {
            num = sum;
            sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
        }
        return sum;
    }

    // 242
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;

        // 读错题了
        /*Map<Character, Character> charMap = new HashMap<>();
        int len = s.length();
        if (len != t.length()) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (charMap.containsKey(s.charAt(i))) {
                if (!charMap.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                charMap.put(s.charAt(i), t.charAt(i));
                charMap.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;*/
    }

    // 237 什么傻逼题
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode curr = head;
        List<Integer> arrayList = new ArrayList<>();
        while (curr != null) {
            arrayList.add(curr.val);
            curr = curr.next;
        }
        int right = arrayList.size() - 1, left = 0;
        while (left < right) {
            if (!arrayList.get(left++).equals(arrayList.get(right--))) {
                return false;
            }
        }
        return true;
    }
}