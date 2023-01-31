package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;

import java.util.*;

/**
 * @author : zhou
 */
public class Day16Solution {
    public static void main(String[] args) {
        Day16Solution solution = new Day16Solution();
        // String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        String s = "abbxxxxzyyyyy";
        // String s = "The quick brown fox jumped over the lazy dog";
        char c = 'e';
        int[][] a = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        // System.out.println(Arrays.toString(solution.flipAndInvertImage(a)[2]));
        int[] arr = {0, 1, 2, 1, 0};
        System.out.println(solution.peakIndexInMountainArray(arr));
        // System.out.println(solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }

    // 876
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 874 todo
    public int robotSim(int[] commands, int[][] obstacles) {
        int direction = 0, ans = 0;
        int[] location = {0, 0};
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + " " + obstacle[1]);
        }
        for (int command : commands) {
            int nextX = 0, nextY = 0;
            if (command < 0) {
                direction = command == -1 ? (direction + 1) % 4 : (direction + 3) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    nextX = location[0] + directions[direction][0];
                    nextY = location[1] + directions[direction][1];
                    if (set.contains(nextX + " " + nextY)) {
                        break;
                    }
                    location[0] = nextX;
                    location[1] = nextY;
                    ans = Math.max(ans, location[0] * location[0] + location[1] * location[1]);
                }
            }
        }
        return ans;
    }

    // 867
    public int[][] transpose(int[][] A) {
        // 不使用额外空间
        if (A.length == A[0].length) {
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A[0].length; j++) {
                    int tmp = A[i][j];
                    A[i][j] = A[j][i];
                    A[j][i] = tmp;
                }
            }
            return A;
        } else {
            int[][] ans = new int[A[0].length][A.length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    ans[j][i] = A[i][j];
                }
            }
            return ans;
        }
    }

    // 860
    public boolean lemonadeChange(int[] bills) {
        int[] a = new int[2];
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                a[0]++;
            } else if (bills[i] == 10) {
                if (a[0] < 1) {
                    return false;
                }
                a[0]--;
                a[1]++;
            } else {
                if (a[0] < 1) {
                    return false;
                }
                if (a[1] < 1) {
                    if (a[0] < 3) {
                        return false;
                    }
                    a[0] -= 3;
                } else {
                    a[0]--;
                    a[1]--;
                }
            }
        }
        return true;
        // deprecated 不用map
        /*Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                hashMap.put(5, hashMap.getOrDefault(5, 0) + 1);
            } else if (bills[i] == 10) {
                if (hashMap.getOrDefault(5, 0) < 1) {
                    return false;
                }
                hashMap.put(5, hashMap.get(5) - 1);
                hashMap.put(10, hashMap.getOrDefault(10, 0) + 1);
            } else {

            }
        }*/
    }

    // 859
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        boolean hasChanged = false, firstChange = false;
        boolean hasDouble = false;
        char tmpA = ' ', tmpB = ' ';
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == B.charAt(i)) {
                if (!hasDouble) {
                    if (set.contains(A.charAt(i))) {
                        hasDouble = true;
                    } else {
                        set.add(A.charAt(i));
                    }
                }
            } else {
                if (hasChanged) {
                    return false;
                }
                if (!firstChange) {
                    tmpA = A.charAt(i);
                    tmpB = B.charAt(i);
                    firstChange = true;
                } else {
                    if (tmpA != B.charAt(i) || tmpB != A.charAt(i)) {
                        return false;
                    }
                    hasChanged = true;
                }
            }
        }
        return hasChanged || (!firstChange && hasDouble);
    }

    // 852
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    // 844
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                }
            } else {
                stack1.push(S.charAt(i));
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == '#') {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                }
            } else {
                stack2.push(T.charAt(i));
            }
        }
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (!stack1.pop().equals(stack2.pop())) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // 836
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec2[1] >= rec1[3] || rec2[0] >= rec1[2] || rec2[3] <= rec1[1] || rec2[2] <= rec1[0]) {
            return false;
        }
        return true;
    }

    // 832
    public int[][] flipAndInvertImage(int[][] a) {
        int left, right;
        for (int i = 0; i < a.length; i++) {
            left = 0;
            right = a[i].length - 1;
            while (left <= right) {
                if (left == right) {
                    a[i][left] = 1 - a[i][left];
                    break;
                }
                int tmp = a[i][left];
                a[i][left++] = 1 - a[i][right];
                a[i][right--] = 1 - tmp;
            }
        }
        return a;
    }

    // 830
    public List<List<Integer>> largeGroupPositions(String s) {
        int count = 1, start = 0, end = 0;
        List<List<Integer>> list = new ArrayList<>();
        char prev = ' ';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                end = i;
                count++;
            } else {
                prev = s.charAt(i);
                if (count >= 3) {
                    List<Integer> ll = new ArrayList<>();
                    ll.add(start);
                    ll.add(end);
                    list.add(ll);
                }
                count = 1;
                start = i;
            }
        }
        if (count >= 3) {
            List<Integer> ll = new ArrayList<>();
            ll.add(start);
            ll.add(end);
            list.add(ll);
        }
        return list;
    }

    // 824
    public String toGoatLatin(String S) {
        String[] words = S.split(" ");
        Set<Character> set = new HashSet<>();
        for (char c : new char[]{'a', 'u', 'o', 'i', 'e'}) {
            set.add(c);
        }
        int n = 1;
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            if (set.contains(Character.toLowerCase(word.charAt(0)))) {
                sb.append(word);
            } else {
                sb.append(word.substring(1)).append(word.charAt(0));
            }
            sb.append('m');
            for (int i = 0; i <= n; i++) {
                sb.append('a');
            }
            words[n++ - 1] = sb.toString();
        }
        StringBuilder ans = new StringBuilder();
        for (String word : words) {
            if (ans.length() == 0) {
                ans = new StringBuilder(word);
            } else {
                ans.append(" ").append(word);
            }
        }
        return ans.toString();
    }

    // 821
    public int[] shortestToChar(String S, char C) {
        int[] ans = new int[S.length()];
        int pre = -S.length() * 2;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                pre = i;
                ans[i] = 0;
                continue;
            }
            ans[i] = i - pre;
        }
        pre = Integer.MAX_VALUE;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                pre = i;
                continue;
            }
            ans[i] = Math.min(ans[i], pre - i);
        }
        return ans;

        /*int[] ans = new int[S.length()];
        int left = -1, right = -1;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                left = right = i;
                ans[i] = 0;
                continue;
            }
            if (left < 0) {
                for (int j = 0; j < S.length(); j++) {
                    if (S.charAt(j) == C) {
                        left = right = j;
                        break;
                    }
                }
                if (left < 0) {
                    break;
                }
            }
            if (i > right) {
                for (int j = i + 1; j < S.length(); j++) {
                    if (S.charAt(j) == C) {
                        right = j;
                        break;
                    }
                }
                if (i > right) {
                    right = S.length() * 2;
                }
            }
            ans[i] = Math.min(Math.abs(i - left), right - i);
        }
        return ans;*/
    }

    // 819
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> hashMap = new HashMap<>();
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        String[] paras = paragraph.split("[ !?',;.]+");
        for (String para : paras) {
            para = para.toLowerCase();
            if (banSet.contains(para)) {
                continue;
            }
            hashMap.put(para, hashMap.getOrDefault(para, 0) + 1);
        }
        String ans = "";
        int num = 0;
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) > num) {
                num = hashMap.get(key);
                ans = key;
            }
        }

        return ans;
    }

    // 811
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> hashMap = new HashMap<>();
        for (String domain : cpdomains) {
            String[] strs = domain.split(" ");
            String[] domains = strs[1].split("\\.");
            StringBuilder tmp = new StringBuilder();
            for (int i = domains.length - 1; i >= 0; i--) {
                if (tmp.length() == 0) {
                    tmp = new StringBuilder(domains[i]);
                } else {
                    tmp.insert(0, domains[i] + ".");
                }
                hashMap.put(tmp.toString(), hashMap.getOrDefault(tmp.toString(), 0) + Integer.parseInt(strs[0]));
            }
        }
        for (String key : hashMap.keySet()) {
            list.add(hashMap.get(key) + " " + key);
        }
        return list;
    }

    // 806
    public int[] numberOfLines(int[] widths, String S) {
        int row = 1, remain = 100;
        for (int i = 0; i < S.length(); i++) {
            int k = S.charAt(i) - 'a';
            if (remain > widths[k]) {
                remain -= widths[k];
            } else {
                remain = 100;
                row++;
            }
        }
        return new int[]{row, 100 - remain};
    }
}