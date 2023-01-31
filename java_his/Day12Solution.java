package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day12Solution {
    public static void main(String[] args) {
        Day12Solution solution = new Day12Solution();
//        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
//         int[] nums = {1, 3, 1, 3, 4, 5, 6};
//        int num = 1;
//        String word = "HELLOZZA";
//        System.out.println(Arrays.toString(solution.findRelativeRanks(nums)));
        String s = "L take leetcode_history contest";
        System.out.println(solution.reverseWords(s));
    }

    // 561
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    // 557
    public String reverseWords(String s) {
        int curr = 0, start, left, right;
        char tmp;
        char[] cc = s.toCharArray();
        char sep = ' ';
        while (curr < cc.length) {
            start = curr;
            while (curr < cc.length && cc[curr] != sep) {
                curr++;
            }
            left = start;
            right = curr - 1;
            while (left < right) {
                tmp = cc[left];
                cc[left++] = cc[right];
                cc[right--] = tmp;
            }
            curr++;
        }
        return new String(cc);
        /*List<Integer> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        char sep = ' ';
        int tmp = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == sep) {
                list.add(j);
            }
        }
        list.add(s.length());
        int left, right, pre = 0;
        char tmp2;
        for (int i = 0; i < list.size(); i++) {
            left = pre;
            right = list.get(i) - 1;
            pre = right + 2;
            while (left < right) {
                tmp2 = chars[left];
                chars[left++] = chars[right];
                chars[right--] = tmp2;
            }
        }
        return new String(chars);*/
    }

    // 551
    public boolean checkRecord(String s) {
        int absent = 0, late = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                if (++absent > 1) {
                    return false;
                }
                late = 0;
            } else if (s.charAt(i) == 'L') {
                if (++late > 2) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        return true;
    }

    // 541
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int left, right;
        char tmp;
        for (int i = 0; i < chars.length; i += 2 * k) {
            left = i;
            right = Math.min(left + k - 1, chars.length - 1);
            while (left < right) {
                tmp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = tmp;
            }
        }
        return new String(chars);
        /*if (k < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        int moreLen = sb.length() % (2 * k);
        int left, right;
        char tmp;
        for (int i = 0; i < sb.length() - moreLen; i += 2 * k) {
            left = i;
            right = k + i - 1;
            while (left < right) {
                tmp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, tmp);
            }
        }
        if (moreLen == 0) {
            System.out.println(1);
            return sb.toString();
        }
        int aa = sb.length() / (2 * k);
        left = aa * 2 * k;
        if (moreLen >= k) {
            right = left + k - 1;
        } else {
            right = sb.length() - 1;
        }
        while (left < right) {
            tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
        return sb.toString();*/
    }

    // 532
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Arrays.sort(nums);
        int start = 0, count = 0, prev = 0x7fffffff;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[start] > k || prev == nums[start]) {
                // 当遇到一串相同数字的时候 那么只需要i不断前进即可
                // 就是当差值大于k的时候 我们需要start进1 此时需要判断增长后的start是否等于i
                // 只有相等的情况才需要i也进1 否则需要i--
                if (++start != i) {
                    i--;
                }
            } else if (nums[i] - nums[start] == k) {
                // 定义一个起始指针start 随着i的增加找到差值为k的时候 start++, i++
                // 当遇到差值为k的时候记录下prev = nums[start] 然后跳过所有等于prev的start
                prev = nums[start++];
                count++;
            }
        }
        return count;
        /*if (k < 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set2.add(num);
            }
            set.add(num);
        }
        if (k == 0) {
            return set2.size();
        }
        int pairs = 0;
        for (int key : set) {
            if (set.contains(key + k)) {
                pairs++;
            }
        }
        return pairs;*/
    }

    // 521 脑筋急转弯...
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

    // 520
    public boolean detectCapitalUse(String word) {
        if (word.length() < 2) {
            return true;
        }
        boolean firstUp, secondUp, next;
        firstUp = isUpper(word.charAt(0));
        secondUp = isUpper(word.charAt(1));
        if (firstUp) {
            next = secondUp;
        } else {
            if (secondUp) {
                return false;
            }
            next = false;
        }
        for (int i = 2; i < word.length(); i++) {
            if (isUpper(word.charAt(i)) != next) {
                return false;
            }
        }
        return true;
    }

    private boolean isUpper(Character c) {
        return Character.toUpperCase(c) == c;
    }

    // 507
    public boolean checkPerfectNumber(int num) {
        if (num < 2) {
            return false;
        }
        int skr = (int) Math.sqrt(num);
        int count = 0, tmp = skr + 1;
        if (skr * skr == num) {
            count += skr;
            tmp--;
        }
        for (int i = 1; i < tmp; i++) {
            if (i == 1) {
                count++;
                continue;
            }
            if (num % i == 0) {
                count += i;
                count += num / i;
            }
        }
        return count == num;
    }

    // 506
    public String[] findRelativeRanks(int[] nums) {
        int len = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < len; i++) {
            map.put(temp[i], i);
        }
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            int index = map.get(nums[i]);
            if (index == len - 1) {
                res[i] = "Gold Medal";
            } else if (index == len - 2) {
                res[i] = "Silver Medal";
            } else if (index == len - 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = String.valueOf(len - index);
            }
        }
        return res;
    }

    // 504 negative positive
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean negative = num < 0;
        if (negative) {
            num = -num;
        }
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (negative) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }

    // 500
    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        String second = "asdfghjkl", third = "zxcvbnm";
        for (String word : words) {
            int curr = 0, cc;
            for (int i = 0; i < word.length(); i++) {
                if (second.indexOf(Character.toLowerCase(word.charAt(i))) != -1) {
                    cc = 2;
                } else if (third.indexOf(Character.toLowerCase(word.charAt(i))) != -1) {
                    cc = 3;
                } else {
                    cc = 1;
                }
                if (curr == 0) {
                    curr = cc;
                } else if (curr != cc) {
                    curr = 0;
                    break;
                }
            }
            if (curr != 0) {
                list.add(word);
            }
        }
        return list.toArray(new String[0]);
    }

    // 496
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                hashMap.put(stack.pop(), num);
            }
            stack.push(num);
        }
        while (!stack.isEmpty()) {
            hashMap.put(stack.pop(), -1);
        }
        int[] element = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            element[i] = hashMap.get(nums1[i]);
        }
        return element;
    }
}