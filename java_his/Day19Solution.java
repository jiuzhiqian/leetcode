package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day19Solution {
    public static void main(String[] args) {
        Day19Solution solution = new Day19Solution();
        int x = 2, y = 3, bound = 10;
        String[] A = {"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin", "ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb", "ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl", "boygirdlggnh", "xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx", "nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop", "hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx", "juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr", "lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo", "oxgaskztzroxuntiwlfyufddl", "tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp", "qnagrpfzlyrouolqquytwnwnsqnmuzphne", "eeilfdaookieawrrbvtnqfzcricvhpiv", "sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz", "yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue", "hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv", "cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo", "teyygdmmyadppuopvqdodaczob", "qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs", "qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"};
        String chars = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";
        int[] nums = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        int K = 123;
        //		测试用例:["dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin","ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb","ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl","boygirdlggnh","xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx","nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop","hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx","juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr","lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo","oxgaskztzroxuntiwlfyufddl","tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp","qnagrpfzlyrouolqquytwnwnsqnmuzphne","eeilfdaookieawrrbvtnqfzcricvhpiv","sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz","yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue","hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv","cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo","teyygdmmyadppuopvqdodaczob","qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvk... (show balloon)

        // System.out.println(solution.powerfulIntegers(x, y, bound));
        // System.out.println(solution.largestSumAfterKNegations(nums, 6));
        System.out.println(solution.countCharacters(A, chars));
    }

    // 1160
    public int countCharacters(String[] words, String chars) {
        int[] cc = new int[26];
        for (char c : chars.toCharArray()) {
            cc[c - 'a']++;
        }
        System.out.println(Arrays.toString(cc));
        int[] c2;
        boolean mark;
        int count = 0;
        for (String word : words) {
            c2 = Arrays.copyOf(cc,26);
            mark = true;
            for (int i = 0; i < word.length(); i++) {
                if (c2[word.charAt(i) - 'a']-- < 1) {
                    mark = false;
                    break;
                }
            }
            if (mark) {
                count += word.length();
            }
        }
        return count;
    }

    // 1033
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        int[] ans = new int[2];
        ans[1] = arr[2] - arr[0] - 2;
        if (arr[1] - arr[0] == 2 || (arr[2] - arr[1] == 2)) {
            ans[0] = 1;
        } else {
            ans[0] = ((arr[1] - arr[0] == 1) ? 0 : 1) + ((arr[2] - arr[1] == 1) ? 0 : 1);
        }
        return ans;
    }

    // 1025 todo
    public boolean divisorGame(int N) {
        if (N == 1) {
            return false;
        }
        boolean[] bp = new boolean[N + 1];
        bp[1] = false;
        bp[2] = true;
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0 && !bp[i - j]) {
                    bp[i] = true;
                    break;
                }
            }
        }
        return bp[N];
    }

    // 1018
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int curr = 0;
        for (int a : A) {
            curr = (curr * 2 + a) % 5;
            ans.add(curr == 0);
        }
        return ans;
    }

    // 1013 总感觉代码好蠢
    public boolean canThreePartsEqualSum(int[] A) {
        int count = 0, curr = 0;
        for (int a : A) {
            count += a;
        }
        if (count % 3 != 0) {
            return false;
        }
        boolean first = false, second = false, third = false;
        for (int a : A) {
            curr += a;
            if (curr == count / 3) {
                if (!first) {
                    curr = 0;
                    first = true;
                } else if (!second) {
                    curr = 0;
                    second = true;
                } else {
                    third = true;
                }
            }
        }
        return third;
    }

    // 1010
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] seconds = new int[60];
        for (int t : time) {
            int tmp = t % 60;
            if (tmp == 0) {
                count += seconds[0];
            } else {
                count += seconds[60 - tmp];
            }
            seconds[tmp]++;
        }
        return count;

        // 集合
        /*Map<Integer, Integer> map = new HashMap<>();
        if (time.length < 2) {
            return 0;
        }
        int count = 0;
        map.put(time[0] % 60, 1);
        for (int i = 1; i < time.length; i++) {
            int tmp = time[i] % 60;
            if (tmp == 0) {
                int tmp2 = map.getOrDefault(0, 0);
                map.put(0, tmp2 + 1);
                count += tmp2;
            } else {
                count += map.getOrDefault(60 - tmp, 0);
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            }
        }
        return count;*/
    }

    // 1009
    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }
        for (int i = 0; i < 32; i++) {
            if ((int) Math.pow(2, i) - 1 >= N) {
                return (int) Math.pow(2, i) - 1 - N;
            }
        }
        return 0;
    }

    // 1005
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int count = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0 && K-- > 0) {
                A[i] = -A[i];
            }
            count += A[i];
            min = Math.min(min, A[i]);
        }
        if (K % 2 == 1) {
            count -= 2 * min;
        }
        return count;
    }

    // 1002
    public List<String> commonChars(String[] A) {
        int[][] aa = new int[A.length][26];
        List<String> ans = new ArrayList<>();
        int index = 0;
        for (String a : A) {
            for (char c : a.toCharArray()) {
                aa[index][c - 'a']++;
            }
            index++;
        }
        for (int i = 0; i < 26; i++) {
            int tmp = aa[0][i];
            for (int j = 1; j < aa.length; j++) {
                if (aa[j][i] < 1) {
                    tmp = 0;
                    break;
                } else {
                    tmp = Math.min(tmp, aa[j][i]);
                }
            }
            while (tmp > 0) {
                ans.add((char) (i + 'a') + "");
                tmp--;
            }
        }
        return ans;
    }

    // 999
    public int numRookCaptures(char[][] board) {
        int x = -1, y = -1, count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x >= 0) {
                break;
            }
        }
        int x0 = x, y0 = y;
        while (x0 >= 0) {
            if (board[x0][y] == '.') {
                break;
            } else if (board[x0][y] == 'P') {
                count++;
                break;
            }
            x0--;
        }
        x0 = x;
        while (x0 < 8) {
            if (board[x0][y] == '.') {
                break;
            } else if (board[x0][y] == 'P') {
                count++;
                break;
            }
            x0++;
        }
        while (y0 >= 0) {
            if (board[x][y0] == '.') {
                break;
            } else if (board[x][y0] == 'P') {
                count++;
                break;
            }
            y0--;
        }
        y0 = y;
        while (y0 < 8) {
            if (board[x][y0] == '.') {
                break;
            } else if (board[x][y0] == 'P') {
                count++;
                break;
            }
            y0++;
        }
        return count;
    }

    // 997
    public int findJudge(int N, int[][] trust) {
        int[] agree = new int[N];
        for (int[] data : trust) {
            agree[--data[0]]--;
            agree[--data[1]]++;
        }
        for (int i = 0; i < N; i++) {
            if (agree[i] == N - 1) {
                return i + 1;
            }
        }
        return -1;
    }

    // 989
    public List<Integer> addToArrayForm(int[] A, int K) {
        String s = K + "";
        char[] B = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        int a = A.length - 1, b = B.length - 1, addOne = 0;
        while (a >= 0 || b >= 0) {
            int tmp = addOne;
            if (b >= 0) {
                tmp += (B[b--] - '0');
            }
            if (a >= 0) {
                tmp += A[a--];
            }
            if (tmp >= 10) {
                addOne = 1;
                list.add(tmp % 10);
            } else {
                addOne = 0;
                list.add(tmp);
            }
        }
        if (addOne == 1) {
            list.add(addOne);
        }
        Collections.reverse(list);
        return list;

        // cao
        /*List<Integer> list = new ArrayList<>();
        int curr = 0, addNo = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            long tmp = (A[i] + addNo) * (long)Math.pow(10, curr++) + K;
            long divisionNum = (long) Math.pow(10, curr);
            if (tmp / divisionNum > K / divisionNum) {
                addNo = 1;
            } else {
                addNo = 0;
            }
            list.add((int) (tmp / (divisionNum / 10) % 10));
        }
        if (addNo == 1) {
            list.add(1);
        }
        Collections.reverse(list);
        return list;*/
    }

    // 985
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int preCount = 0, index = 0;
        int[] ans = new int[queries.length];
        for (int a : A) {
            if (a % 2 == 0) {
                preCount += a;
            }
        }
        for (int[] query : queries) {
            if (A[query[1]] % 2 == 0) {
                if (query[0] % 2 == 0) {
                    preCount += query[0];
                } else {
                    preCount -= A[query[1]];
                }
            } else {
                if (query[0] % 2 != 0) {
                    preCount += (A[query[1]] + query[0]);
                }
            }
            A[query[1]] += query[0];
            ans[index++] = preCount;
        }
        return ans;
    }

    // 977
    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        int left = 0, right = A.length - 1, index = right;
        while (left <= right) {
            if (Math.abs(A[left]) >= Math.abs(A[right])) {
                ans[index--] = A[left] * A[left++];
            } else {
                ans[index--] = A[right] * A[right--];
            }
        }
        return ans;
    }

    // 976
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 1] + A[i - 2] > A[i]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

    // 509
    public int fib(int N) {
        if (N < 1) {
            return 0;
        } else if (N < 3) {
            return 1;
        }
        int ans = 1;
        int pre = 1;
        for (int i = 3; i <= N; i++) {
            int tmp = pre;
            pre = ans;
            ans += tmp;
        }
        return ans;
    }

    // 970
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < bound; i++) {
            for (int j = 0; j < bound; j++) {
                double n = Math.pow(x, i) + Math.pow(y, j);
                if (n <= bound) {
                    set.add((int) n);
                } else {
                    break;
                }
            }
        }
        return new ArrayList<>(set);
    }
}