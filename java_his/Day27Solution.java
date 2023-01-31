package xin.jiuzhiqian.study.leetcode_history;


/**
 * @author : feng
 */
public class Day27Solution {

    public static void main(String[] args) {
        Day27Solution solution = new Day27Solution();
        String[] words = {"n", "eeitfns", "eqqqsfs", "i", "feniqis", "lhoa", "yqyitei", "sqtn", "kug", "z", "neqqis"};
        String allowed = "fstqyienx";
        String command = "(al)G(al)()()G";
        System.out.println(solution.reformatNumber("123 4-5678"));
    }

    // 1694
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        int len = number.length();
        for (int i = 0; i < len; i++) {
            if (number.charAt(i) != '-' && number.charAt(i) != ' ') {
                sb.append(number.charAt(i));
            }
        }
        number = sb.toString();
        sb = new StringBuilder();
        len = number.length();
        System.out.println(number);
        int num = len % 3;
        int curr = 1;
        if (num == 1) {
            curr = 2;
        } else if (num == 2) {
            curr = 3;
        }
        System.out.println(curr);
        sb.append(number.charAt(len - 1)).append(number.charAt(len - 2));
        if (curr > 1) {
            sb.append('-');
        }
        for (int i = len - 3; i >= 0; i--) {
            if (curr-- == 0) {
                sb.append('-');
                curr += 3;
            }
            sb.append(number.charAt(i));
        }
        return sb.reverse().toString();
    }

    // 1688
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += n / 2;
            n = (n + 1) / 2;
        }
        return count;
    }

    // 1700
    public int countStudents(int[] students, int[] sandwiches) {
        int[] arr = new int[2];
        for (int student : students) {
            arr[student]++;
        }
        for (int sandwich : sandwiches) {
            if (arr[sandwich] < 1) {
                break;
            } else {
                arr[sandwich]--;
            }
        }
        return arr[0] + arr[1];
    }

    // 1678
    public String interpret(String command) {
        // "G"、"()" 和/或 "(al)"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append('G');
            } else if (command.charAt(i + 1) == ')') {
                sb.append('o');
                i++;
            } else {
                sb.append("al");
                i += 3;
            }
        }
        return sb.toString();
    }

    // 1672
    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < accounts.length; i++) {
            int curr = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                curr += accounts[i][j];
            }
            max = Math.max(curr, max);
        }
        return max;
    }

    // 1684
    public int countConsistentStrings(String allowed, String[] words) {
        int[] cc = new int[26];
        for (int i = 0; i < allowed.length(); i++) {
            cc[allowed.charAt(i) - 'a'] = 1;
        }
        int count = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (cc[word.charAt(i) - 'a'] == 0) {
                    count++;
                    break;
                }
            }
        }
        return words.length - count;
    }

    // 1662
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String word : word1) {
            sb1.append(word);
        }
        for (String word : word2) {
            sb2.append(word);
        }
        if (sb1.toString().equals(sb2.toString())) {
            return true;
        }
        return false;
    }

    // 1668
    public int maxRepeating(String sequence, String word) {
        StringBuilder sb = new StringBuilder(word);
        int ans = 0;
        while (sequence.contains(sb.toString())) {
            ans++;
            sb.append(word);
        }
        return ans;
    }

    // 1640
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] bucket = new int[101];
        for (int n : arr) {
            bucket[n] = 1;
        }
        for (int[] piece : pieces) {
            for (int m : piece) {
                bucket[m]--;
            }
            if (piece.length > 1) {
                int tmp = -1;
                for (int j = 0; j < arr.length - piece.length + 1; j++) {
                    if (arr[j] == piece[0]) {
                        tmp = j;
                        break;
                    }
                }
                if (tmp < 0) {
                    return false;
                } else {
                    for (int i = 1; i < piece.length; i++) {
                        if (piece[i] != arr[i + tmp]) {
                            return false;
                        }
                    }
                }
            }
        }
        for (int o : bucket) {
            if (o != 0) {
                return false;
            }
        }
        return true;
    }
}