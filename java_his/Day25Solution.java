package xin.jiuzhiqian.study.leetcode_history;


import java.util.*;

/**
 * @author : feng
 */
public class Day25Solution {

    public static void main(String[] args) {
        Day25Solution solution = new Day25Solution();
        // System.out.println(solution.isPrefixOfWord("hello world word", "word"));

        // System.out.println(0 ^ 2 ^ 4 ^ 6 ^ 8);
        int[] nums = {4, 0, 2, 6, 7, 3, 1, 5};
        // System.out.println(Arrays.toString(solution.shuffle(nums, 3)));
        System.out.println(solution.restoreString("aaiougrt", nums));

        /*List<List<String>> paths = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("qMTSlfgZlC");
        list.add("ePvzZaqLXj");
        paths.add(list);
        List<String> list2 = new ArrayList<>();
        list2.add("xKhZXfuBeC");
        list2.add("TtnllZpKKg");
        paths.add(list2);
        List<String> list3 = new ArrayList<>();
        list3.add("ePvzZaqLXj");
        list3.add("sxrvXFcqgG");
        paths.add(list3);
        List<String> list4 = new ArrayList<>();
        list4.add("sxrvXFcqgG");
        list4.add("xKhZXfuBeC");
        paths.add(list4);
        List<String> list5 = new ArrayList<>();
        list5.add("TtnllZpKKg");
        list5.add("OAxMijOZgW");
        paths.add(list5);*/
        // System.out.println(solution.maxPower("hooraaaaaaaaaaay"));
        // System.out.println(solution.destCity(paths));
    }

    // 1528
    public String restoreString(String s, int[] indices) {
        StringBuilder sb = new StringBuilder();
        char[] arr = new char[indices.length];

        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = s.charAt(i);
        }
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }

    // 1539
    public int findKthPositive(int[] arr, int k) {
        int begin = 1;
        for (int j : arr) {
            while (begin < j) {
                System.out.println(begin);
                if (k-- == 1) {
                    return begin;
                }
                begin++;
            }
            begin++;
        }
        while (k > 1) {
            begin++;
            k--;
        }
        return begin;
    }

    // 1518
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;
        while (numBottles >= numExchange) {
            count += (numBottles / numExchange);
            numBottles = (numBottles / numExchange + numBottles % numExchange);
        }
        return count;
    }

    // 1512
    public int numIdenticalPairs(int[] nums) {
        int[] bucket = new int[101];
        int count = 0;
        for (int num : nums) {
            bucket[num]++;
        }
        for (int n : bucket) {
            if (n <= 1) {
                continue;
            }
            n--;
            count += (n * (n + 1) / 2);
        }
        return count;
    }

    // 1523
    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }

    // 1502
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length < 3) {
            return true;
        }
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i - 1] + diff != arr[i]) {
                return false;
            }
        }
        return true;
    }

    // 1496
    public boolean isPathCrossing(String path) {
        Integer[] point = {0, 0};
        Map<Character, Integer[]> des = new HashMap<>();
        Set<String> location = new HashSet<>();
        location.add("0_0");
        Integer[] d;
        des.put('N', new Integer[]{0, 1});
        des.put('S', new Integer[]{0, -1});
        des.put('E', new Integer[]{-1, 0});
        des.put('W', new Integer[]{1, 0});
        for (int i = 0; i < path.length(); i++) {
            d = des.get(path.charAt(i));
            point[0] += d[0];
            point[1] += d[1];
            if (location.contains(point[0] + "_" + point[1])) {
                return true;
            } else {
                location.add(point[0] + "_" + point[1]);
            }
        }
        return false;
    }

    // 1486
    public int xorOperation(int n, int start) {
        if (n == 1) {
            return start;
        }
        int res = start;
        while (n > 1) {
            n--;
            start += 2;
            res ^= start;
        }
        return res;
    }

    // 1480
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    // 1491
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, count = 0;
        for (int sa : salary) {
            min = Math.min(sa, min);
            max = Math.max(sa, max);
            count += sa;
        }
        return (double) (count - min - max) / (salary.length - 2);
    }

    // 1470
    public int[] shuffle(int[] nums, int n) {
        int[] ret = new int[nums.length];
        for (int i = 0; i < n; i++) {
            ret[i * 2] = nums[i];
            ret[i * 2 + 1] = nums[i + n];
        }
        return ret;
    }

    // 1464
    public int maxProduct(int[] nums) {
        int maxIndex = 0;
        int first = nums[0], second = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > first) {
                first = nums[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != maxIndex && nums[i] > second) {
                second = nums[i];
            }
        }
        return (first - 1) * (second - 1);
    }

    // 1475
    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            int tmp = i + 1;
            while (tmp < prices.length) {
                if (prices[tmp] <= prices[i]) {
                    prices[i] -= prices[tmp];
                    break;
                }
                tmp++;
            }
        }
        return prices;
    }

    // 1455
    public int isPrefixOfWord(String sentence, String searchWord) {
        int index = (" " + sentence).indexOf(" " + searchWord);
        if (index == 0) {
            return 1;
        } else if (index == -1) {
            return -1;
        }
        String s = sentence.substring(0, index - 1);
        return s.split(" ").length + 1;
    }

    // 1450
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int num = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime < startTime[i]) {
                continue;
            } else if (queryTime > endTime[i]) {
                continue;
            }
            num++;
        }
        return num;
    }

    // 1460
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) {
            return false;
        }
        int[] countMap = new int[1001];
        for (int i = 0; i < target.length; i++) {
            countMap[target[i]]++;
            countMap[arr[i]]--;
        }
        for (int c : countMap) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    // 1441
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        String push = "Push";
        String pop = "Pop";
        int pre = 1;
        for (int i = 0; i < target.length; i++) {
            while (pre < target[i]) {
                ans.add(push);
                ans.add(pop);
                pre++;
            }
            ans.add(push);
            pre++;
        }
        /*int targetMax = target[target.length - 1];
        while (targetMax < n) {
            targetMax++;
            ans.add(push);
            ans.add(pop);
        }*/
        return ans;
    }

    // 1437
    public boolean kLengthApart(int[] nums, int k) {
        if (k <= 0) {
            return true;
        }
        int curr = -1;
        for (int n : nums) {
            if (n == 0 && curr < 0) {
                continue;
            }
            if (n == 1) {
                if (curr < 0) {
                    curr = 0;
                } else if (curr < k) {
                    return false;
                } else {
                    curr = 0;
                }
            } else {
                curr++;
            }
        }
        return true;
    }

    // 1436
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < paths.size(); i++) {
            map.put(paths.get(i).get(0), paths.get(i).get(1));
        }

        for (String key : map.keySet()) {
            if (!map.containsKey(map.get(key))) {
                return map.get(key);
            }
        }
        return "";

        /*Set<String> from = new HashSet<>();
        Set<String> to = new HashSet<>();
        for (int i = 0; i < paths.size(); i++) {
            String f = paths.get(i).get(0);
            String t = paths.get(i).get(1);
            if (to.contains(f)) {
                to.remove(f);
            } else {
                from.add(f);
            }
            if (from.contains(t)) {
                from.remove(t);
            } else {
                to.add(t);
            }
        }
        return (String) to.toArray()[0];*/
    }

    // 1446
    public int maxPower(String s) {
        int ans = 1, currNum = 1;
        char currChar = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == currChar) {
                currNum++;
            } else {
                currChar = s.charAt(i);
                currNum = 1;
            }
            ans = Math.max(ans, currNum);
        }
        return ans;
    }

    // 1422
    public int maxScore(String s) {
        int score = 0, left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                right++;
            }
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                left++;
            } else {
                right--;
            }
            score = Math.max(score, left + right);
        }
        return score;
    }

    // 1417
    public String reformat(String s) {
        StringBuilder sb = new StringBuilder();
        int cN = 0, cC = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' < 10) {
                cN++;
            } else {
                cC++;
            }
        }
        if (Math.abs(cC - cN) > 1) {
            return "";
        } else {
            char[] nArr = new char[cN], cArr = new char[cC];
            cN = 0;
            cC = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) - '0' < 10) {
                    nArr[cN++] = s.charAt(i);
                } else {
                    cArr[cC++] = s.charAt(i);
                }
            }
            if (cC > cN) {
                for (int i = 0; i < nArr.length; i++) {
                    sb.append(cArr[i]).append(nArr[i]);
                }
                sb.append(cArr[nArr.length]);
            } else if (cC == cN) {
                for (int i = 0; i < nArr.length; i++) {
                    sb.append(cArr[i]).append(nArr[i]);
                }
            } else {
                for (int i = 0; i < cArr.length; i++) {
                    sb.append(nArr[i]).append(cArr[i]);
                }
                sb.append(nArr[cArr.length]);
            }
        }
        return sb.toString();
    }
}