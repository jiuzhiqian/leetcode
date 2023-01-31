package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day18Solution {
    public static void main(String[] args) {
        Day18Solution solution = new Day18Solution();
        // String[] deck = {"test.email+alex@leetcode_history.com","test.email.leet+alex@code.com"};
        // String s = "ab-cd";
        // System.out.println(solution.numUniqueEmails(deck));
        // System.out.println(Arrays.toString(solution.sortArrayByParityII(deck)));
        int[] aa = {1, 2, 3, 3};
        String[] words = {"apple", "app"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(solution.repeatedNTimes(aa));

        // System.out.println(Arrays.toString(solution.diStringMatch(S)));
    }

    // 961
    public int repeatedNTimes(int[] A) {
        Set<Integer> hashSet = new HashSet<>();
        for (int value : A) {
            if (hashSet.contains(value)) {
                return value;
            } else {
                hashSet.add(value);
            }
        }
        return 0;
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                if (2 * i <= A.length) {
                    map.put(A[i], 1);
                }
            }
        }
        for (int a : map.keySet()) {
            if (map.get(a) == A.length / 2) {
                return a;
            }
        }
        return 0;*/
    }

    // 953
    public boolean isAlienSorted(String[] words, String order) {
        int[] orders = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orders[order.charAt(i) - 'a'] = i + 1;
        }
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int pre = j < words[i - 1].length() ? orders[words[i - 1].charAt(j) - 'a'] : 0;
                if (orders[words[i].charAt(j) - 'a'] > pre) {
                    break;
                } else if (orders[words[i].charAt(j) - 'a'] < pre) {
                    return false;
                }
                if (j == words[i].length() - 1 && words[i - 1].length() > words[i].length()) {
                    return false;
                }
            }
        }
        return true;
    }

    // 944
    public int minDeletionSize(String[] A) {
        int count = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // 942
    public int[] diStringMatch(String S) {
        int[] ans = new int[S.length() + 1];
        int min = 0, max = S.length();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'I') {
                ans[i] = min++;
            } else {
                ans[i] = max--;
            }
        }
        ans[S.length()] = max;
        return ans;
    }

    // 941
    public boolean validMountainArray(int[] A) {
        boolean upper = true;
        if (A.length < 3 || A[1] <= A[0]) {
            return false;
        }
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                return false;
            }
            if (A[i] < A[i - 1]) {
                upper = false;
            } else if (!upper && A[i] > A[i - 1]) {
                return false;
            }
        }
        return !upper;
    }

    // 937 todo
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] sp1 = log1.split(" ", 2);
            String[] sp2 = log2.split(" ", 2);

            boolean isDi1 = Character.isDigit(sp1[1].charAt(0));
            boolean isDi2 = Character.isDigit(sp2[1].charAt(0));
            if (!isDi1 && !isDi2) {
                int tmp = sp1[1].compareTo(sp2[1]);
                if (tmp != 0) {
                    return tmp;
                }
                return sp1[0].compareTo(sp2[0]);
            }
            return isDi1 ? (isDi2 ? 0 : 1) : -1;
        });
        return logs;
    }

    // 929
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            boolean isLocal = true;
            boolean existAdd = false;
            StringBuilder sb = new StringBuilder();
            for (char c : email.toCharArray()) {
                if (c == '@') {
                    sb.append(c);
                    isLocal = false;
                } else if (isLocal && !existAdd) {
                    if (c == '+') {
                        existAdd = true;
                    } else if (c != '.') {
                        sb.append(c);
                    }
                } else if (!isLocal) {
                    sb.append(c);
                }
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    // 925
    public boolean isLongPressedName(String name, String typed) {
        char pre = name.charAt(0);
        int j = 0;
        for (int i = 0; i < typed.length(); i++) {
            if (j < name.length() && typed.charAt(i) == name.charAt(j)) {
                pre = name.charAt(j);
                j++;
            } else if (typed.charAt(i) != pre) {
                return false;
            }
        }
        return j >= name.length();
    }

    // 922
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 == 1) {
                System.out.println(A[i]);
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }

    // 917
    public String reverseOnlyLetters(String S) {
        char[] cc = S.toCharArray();
        int index = cc.length - 1;
        for (int i = 0; i <= index; i++) {
            if (isChar(cc[i])) {
                while (index >= i) {
                    if (isChar(cc[index])) {
                        break;
                    }
                    index--;
                }
                char c = cc[index];
                cc[index--] = cc[i];
                cc[i] = c;
            }
        }
        return new String(cc);
    }

    private boolean isChar(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }

    // 914
    public boolean hasGroupsSizeX(int[] deck) {
        int preCount = -1;
        int curr = 0;
        Arrays.sort(deck);
        int preNum = deck[0];
        for (int n : deck) {
            if (n == preNum) {
                curr++;
            } else {
                if (preCount < 0) {
                    preCount = curr;
                } else {
                    preCount = gcd(preCount, curr);
                    if (preCount < 2) {
                        return false;
                    }
                }
                preNum = n;
                curr = 1;
            }
        }
        if (preCount < 0) {
            preCount = curr;
        } else {
            preCount = gcd(preCount, curr);
        }
        return preCount > 1;
        // 官方
        /*int[] count = new int[10000];
        for (int c: deck) {
            count[c]++;
        }

        int g = -1;
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                if (g == -1) {
                    g = count[i];
                } else {
                    g = gcd(g, count[i]);
                }
            }
        }
        return g >= 2;*/
    }

    // 最大公约数
    private int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}