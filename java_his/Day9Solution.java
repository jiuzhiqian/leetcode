package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day9Solution {
    public static void main(String[] args) {
        Day9Solution solution = new Day9Solution();
//        int n = Integer.MAX_VALUE;
//        System.out.println(Math.log10(n) / Math.log10(3));
//        System.out.println(solution.countPrimes(n));
//        String s = "hello";
        int num = 28;
//        System.out.println(num);
        System.out.println(solution.toHex(num));
    }

    // 405 无符号 GG
    public String toHex(int num) {
        char[] hex = "0123456789abcdef".toCharArray();
        StringBuilder str = new StringBuilder();
        while (num != 0) {
            int end = num & 15;
            str.append(hex[end]);
            //无符号右移
            num >>>= 4;
        }
        if (str.length() == 0) {
            str.append("0");
        }
        //反转字符
        StringBuilder str0 = str.reverse();

        return str0.toString();

        /*StringBuilder stringBuilder = new StringBuilder();
        List<Character> list = new ArrayList<>();
        while (num != 0) {
            int tmp = num % 16;
            System.out.println(getNumChar(tmp));
            list.add(getNumChar(tmp));
            num /= 16;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();*/
    }

    private char getNumChar(int n) {
        if (n >= 10) {
            return (char) (n + 'a' - 10);
        }
        return (char) (n + '0');
    }


    // 392
    public boolean isSubsequence(String s, String t) {
        int sn = 0, tn = 0, tmp1 = 0, tmp2 = 0;
        for (int j = 0; j < s.length(); j++) {
            if (tmp1 >= t.length()) {
                return false;
            }
            for (int k = tmp1; k < t.length(); k++) {
                if (s.charAt(j) == t.charAt(k)) {
                    tmp2 = k + 1;
                    break;
                }
            }
            if (tmp2 == tmp1) {
                return false;
            }
            tmp1 = tmp2;
        }
        return true;
    }

    // 389
    public char findTheDifference(String s, String t) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num ^= s.charAt(i) ^ t.charAt(i);
        }
        num ^= t.charAt(t.length() - 1);
        return (char) num;

        /*char[] ss = s.toCharArray();
        char[] st = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(st);
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != st[i]) {
                return st[i];
            }
        }
        return st[st.length-1];*/
    }

    // 387 数组 哈希表
    public int firstUniqChar(String s) {
        int[] chars = new int[256];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    // 383 另一种思路,用数组,(int)char作为key,出现次数为value
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (map.getOrDefault(ransomNote.charAt(i), 0) < 1) {
                return false;
            }
            map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) - 1);
        }
        return true;
    }

    private int guess(int num) {
        if (num < 10) {
            return 1;
        } else if (num > 10) {
            return -1;
        }
        return 0;
    }

    //374 二分分的有点难受了。。。
    public int guessNumber(int n) {
        int left = 0, right = n, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 371
    public int getSum(int a, int b) {
        return Math.addExact(a, b);
    }

    // 367
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        int left = 1, right = num;
        long ss;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            ss = mid * mid;
            if (ss == num) {
                return true;
            } else if (ss < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 350 直接来排序的
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = 0, n2 = 0;
        List<Integer> list = new ArrayList<>();
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] == nums2[n2]) {
                list.add(nums1[n1++]);
                n2++;
            } else if (nums1[n1] < nums2[n2]) {
                n1++;
            } else {
                n2++;
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    // 349 狗屎代码,不管了
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }
        int[] tmp = new int[res.size()];
        int i = 0;
        for (int num : res) {
            tmp[i++] = num;
        }
        return tmp;
    }

    // 345 用集合还是不快且废内存
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (!set.contains(chars[left])) {
                left++;
                continue;
            }
            if (!set.contains(chars[right])) {
                right--;
                continue;
            }

            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
        return String.copyValueOf(chars);


        /*if (s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'o' || chars[i] == 'i' || chars[i] == 'u' || chars[i] == 'A' || chars[i] == 'E' || chars[i] == 'O' || chars[i] == 'I' || chars[i] == 'U') {
                if (chars[j] == 'a' || chars[j] == 'e' || chars[j] == 'o' || chars[j] == 'i' || chars[j] == 'u' || chars[j] == 'A' || chars[j] == 'E' || chars[j] == 'O' || chars[j] == 'I' || chars[j] == 'U') {
                    if (chars[i] != chars[j]) {
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                    }
                    ++i;
                    --j;
                } else {
                    --j;
                }
            } else {
                ++i;
            }
        }
        return new String(chars);*/
        /*Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            return s;
        }
        int left = 0, right = list.size() - 1;
        char[] res = s.toCharArray();
        while (left < right) {
            char tmp = res[list.get(left)];
            res[list.get(left++)] = res[list.get(right)];
            res[list.get(right--)] = tmp;
        }
        return String.copyValueOf(res);*/
    }

    // 344
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    // 324
    public boolean isPowerOfFour(int num) {
        return (Math.log10(num) / Math.log10(3)) % 1 == 0;
    }

    // 326 其他算法不会
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    // 303 GG
    /*private Map<String, Integer> hashMap = new HashMap<>();
    private int[] nums;

    public Day9Solution(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        String key = i + "." + j;
        if (hashMap.containsKey(key)) {
            return hashMap.get(i + "." + j);
        }
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        hashMap.put(key, sum);
        return sum;
    }*/

    //  204 GG todo
    public int countPrimes(int n) {
        /*int count = 0;
        int[] array = new int[n];
        //建立数组，默认整型数组元素都是0.
        // 从2开始进行比较，最后数组元素是0时，为质数，为1时为合数。
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (array[i] == 0) {
                for (int j = i * i; j < n; j += i) {
                    array[j] = 1;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (array[i] == 0) {
                count++;
            }
        }
        System.out.println("结果:" + count);
        System.out.println("开根:" + (int) Math.sqrt(n));
        return count;*/
        if (n <= 2) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (i < 4) {
                count++;
                continue;
            }
            if (i % 2 == 0) {
                continue;
            }
            boolean isPrime = true;
            for (int j = 3; j < (i + 1) / 2; j += 2) {
                if (!isPrime) {
                    break;
                }
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                count++;
            }
        }

        System.out.println("结果:" + count);
        System.out.println("开根:" + (int) Math.sqrt(n));
        return count;
    }
}