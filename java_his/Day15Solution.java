package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.Node;

import java.util.*;

/**
 * @author : zhou
 */
public class Day15Solution {
    private PriorityQueue<Integer> priorityQueue;
    private int fixed_k;

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 3));
        Day15Solution solution = new Day15Solution();
        // String[] words = {"d", "do", "dog", "p", "pe", "pen", "peng", "pengu", "pengui", "penguin", "e", "el", "ele", "elep", "eleph", "elepha", "elephan", "elephant"};
        // int[] nums = {1, 7, 3, 6, 5, 6};
        // int[] nums = {-1, -1, -1, 0, 1, 1};
        String str = "Hello";
        // System.out.println(solution.toLowerCase(str));
        // licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder"
// ,"box","arrive","money","tax","thus"]
//         String licensePlate = "OgEu755";
//         String[] words = {"enough", "these", "play", "wide", "wonder", "box", "arrive", "money", "tax", "thus"};
//         System.out.println(solution.shortestCompletingWord(licensePlate, words));
        // System.out.println(solution.selfDividingNumbers(10, 50).toString());
        // int[] nums = {-1, 0, 3, 5, 9, 12};
        int n = 10;
        System.out.println(solution.rotatedDigits(857));
    }

    // 788 GG
    public int rotatedDigits(int N) {
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (good(i, false)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean good(int n, boolean flag) {
        if (n == 0) {
            return flag;
        }
        int d = n % 10;
        if (d == 3 || d == 4 || d == 7) {
            return false;
        }
        if (d == 0 || d == 1 || d == 8) {
            return good(n / 10, flag);
        }
        return good(n / 10, true);
    }

    // 704
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 703
    private class KthLargest {
        public KthLargest(int k, int[] nums) {
            fixed_k = k;
            priorityQueue = new PriorityQueue<>(k);
            for (int val : nums) {
                add(val);
            }
        }

        private int add(int val) {
            if (priorityQueue.size() < fixed_k) {
                priorityQueue.add(val);
            } else if (val > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(val);
            }
            return priorityQueue.peek();
        }
    }

    // 766
    public boolean isToeplitzMatrix(int[][] matrix) {
        int len1 = matrix.length, len2 = matrix[0].length;
        for (int i = 0; i < len1; i++) {
            int tmp1 = i, tmp2 = 0;
            while (tmp1 < len1 && tmp2 < len2) {
                if (matrix[tmp1++][tmp2++] != matrix[i][0]) {
                    return false;
                }
            }
        }
        for (int i = 0; i < len2; i++) {
            int tmp1 = 0, tmp2 = i;
            while (tmp1 < len1 && tmp2 < len2) {
                if (matrix[tmp1++][tmp2++] != matrix[0][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 559
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int dep = 1;
        for (Node node : root.children) {
            int tmpDep = maxDepth(node) + 1;
            if (tmpDep > dep) {
                dep = tmpDep;
            }
        }
        return dep;
    }

    // 748
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String answer = "";
        for (String word : words) {
            if (!answer.isEmpty() && word.length() >= answer.length()) {
                continue;
            }
            if (dominates(count(word.toLowerCase()), target)) {
                answer = word;
            }
        }
        return answer;
    }

    public boolean dominates(int[] count1, int[] count2) {
        for (int i = 0; i < 26; i++) {
            if (count1[i] < count2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] count(String licensePlate) {
        int[] ans = new int[26];
        for (char c : licensePlate.toCharArray()) {
            int index = Character.toLowerCase(c) - 'a';
            if (index >= 0 && index < 26) {
                ans[index]++;
            }
        }
        return ans;
    }

    // 747
    public int dominantIndex(int[] nums) {
        int first = nums[0], second = 0, index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > first) {
                second = first;
                first = nums[i];
                index = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        if (first >= 2 * second) {
            return index;
        }
        return -1;
    }

    // 746
    public int minCostClimbingStairs(int[] cost) {
        // 存上一步最小值
        int pre = 0;
        // 存最小值
        int pre2 = 0;
        for (int i = 1; i < cost.length; i++) {
            int curr = Math.min(pre + cost[i - 1], pre2 + cost[i]);
            pre = pre2;
            pre2 = curr;
        }
        return pre2;
    }

    // 744
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        if (target >= letters[len - 1]) {
            return letters[0];
        }
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[right];
    }

    // 709
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c - 'A' + 'a');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    // 733 深度优先
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originColor = image[sr][sc];
        if (originColor != newColor) {
            dfs(image, sr, sc, newColor, originColor);
        }
        return image;
    }

    private void dfs(int[][] image, int x, int y, int newColor, int originColor) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return;
        }
        if (image[x][y] == originColor) {
            image[x][y] = newColor;
            dfs(image, x - 1, y, newColor, originColor);
            dfs(image, x + 1, y, newColor, originColor);
            dfs(image, x, y - 1, newColor, originColor);
            dfs(image, x, y + 1, newColor, originColor);
        }
    }

    // 728
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i < right + 1; i++) {
            String s = "" + i;
            if (s.contains("0")) {
                continue;
            }
            boolean bb = true;
            for (int j = 0; j < s.length(); j++) {
                int tmp = s.charAt(j) - '0';
                if (i % tmp != 0) {
                    bb = false;
                    break;
                }
            }
            if (bb) {
                list.add(i);
            }
        }
        return list;
    }

    // 724
    public int pivotIndex(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }
        int count = 0, left = 0;
        for (int num : nums) {
            count += num;
        }
        int right = count;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                left += nums[i - 1];
            }
            right -= nums[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    // 720
    public String longestWord(String[] words) {
        Arrays.sort(words);
        String res = "";
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (set.contains(word)) {
                continue;
            }
            if (word.length() == 1) {
                set.add(word);
            } else {
                String tmp = word.substring(0, word.length() - 1);
                if (!set.contains(tmp)) {
                    continue;
                }
                set.add(word);
            }
            if (word.length() > res.length()) {
                res = word;
            }
        }
        return res;
    }
}