package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day13Solution {
    public static void main(String[] args) {
        Day13Solution solution = new Day13Solution();
        // int[] nums = {2, 4, 6, 8};
        int[] flowerbed = {1, 12, -5, -6, 50, 3};
        System.out.println(solution.findMaxAverage(flowerbed, 4));
    }

    // 643
    public double findMaxAverage(int[] nums, int k) {
        int start = 0, total = 0;
        double max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                total += nums[i];
                max += nums[i];
                start = nums[0];
            } else {
                total = total + nums[i] - start;
                max = Math.max(total, max);
                start = nums[i - k + 1];
            }
        }
        return max / k;
    }

    // 633
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            double j = Math.sqrt(c - i * i);
            if (j == (int) j) {
                return true;
            }
        }
        return false;
    }

    // 628
    public int maximumProduct(int[] nums) {
        // official
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {
                min2 = n;
            }
            if (n >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
        /*int first = -1, second = 0, third = 0, underFirst = 0, underSecond = 0, maxFirst = Integer.MIN_VALUE, maxSecond = Integer.MIN_VALUE, maxThird = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > 0) {
                if (num >= first) {
                    third = second;
                    second = first;
                    first = num;
                } else if (num >= second) {
                    third = second;
                    second = num;
                } else if (num > third) {
                    third = num;
                }
            } else if (num < 0) {
                if (num >= maxFirst) {
                    maxThird = maxSecond;
                    maxSecond = maxFirst;
                    maxFirst = num;
                } else if (num >= maxSecond) {
                    maxThird = maxSecond;
                    maxSecond = num;
                } else if (num > maxThird) {
                    maxThird = num;
                }
                if (num <= underFirst) {
                    underSecond = underFirst;
                    underFirst = num;
                } else if (num < underSecond) {
                    underSecond = num;
                }
            }
        }
        if (first < 0) {
            return maxFirst * maxSecond * maxThird;
        } else {
            return first * Math.max(underFirst * underSecond, second * third);
        }*/
    }


    // 605
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] == 0 && (i > 0 && flowerbed[i - 1] == 0 || i == 0) && (i < flowerbed.length - 1 && flowerbed[i + 1] == 0 || i == flowerbed.length - 1)) {
                n--;
                flowerbed[i] = 1;
            }
        }
        return n == 0;
    }

    // 599
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            return findRestaurant(list2, list1);
        }
        int minIndex = Integer.MAX_VALUE;
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>((int) (list1.length / 0.75));

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {

            if (i > minIndex) {
                break;
            }

            if (map.containsKey(list2[i])) {

                int index = i + map.get(list2[i]);
                if (index < minIndex) {
                    ans.clear();
                    ans.add(list2[i]);
                    minIndex = index;
                } else if (index == minIndex) {
                    ans.add(list2[i]);
                }

            }

        }
        return ans.toArray(new String[]{});
        /*Map<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            hashMap.put(list1[i], i);
        }
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE, sum;
        for (int i = 0; i < list2.length; i++) {
            if (hashMap.containsKey(list2[i])) {
                sum = i + hashMap.get(list2[i]);
                if (sum < min) {
                    list.clear();
                    list.add(list2[i]);
                    min = sum;
                } else if (sum == min) {
                    list.add(list2[i]);
                }
            }
        }
        return list.toArray(new String[0]);*/
    }


    // 598
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return 0;
        }
        if (ops[0].length != 2) {
            return 0;
        }
        int i = m, j = n;
        for (int[] op : ops) {
            if (op[0] == 0 || op[1] == 0) {
                continue;
            }
            i = Math.min(op[0], i);
            j = Math.min(op[1], j);
        }
        return i * j;
    }

    // 594
    public int findLHS(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int max = 0;
        for (int num : hashMap.keySet()) {
            if (hashMap.containsKey(num + 1)) {
                max = Math.max(max, hashMap.get(num) + hashMap.get(num + 1));
            }
        }
        return max;
    }

    // 581 perfect
    public int findUnsortedSubarray(int[] nums) {
        int first = -1, second = 0, max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            if (nums[i] < nums[i - 1]) {
                if (first < 0) {
                    first = i - 1;
                    max = nums[first];
                }
                second = i;
            } else if (first >= 0 && nums[i] < max) {
                second = i;
            }
            while (first > 0 && nums[i] < nums[first - 1]) {
                first--;
            }
        }
        return second == 0 ? 0 : second - first + 1;
    }

    // 575
    public int distributeCandies(int[] candies) {
        // Sort
        Arrays.sort(candies);
        int count = 1;
        for (int i = 1; i < candies.length && count < candies.length / 2; i++) {
            if (candies[i] > candies[i - 1]) {
                count++;
            }
        }
        return count;
        // Set
        /*Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(candies.length / 2, set.size());*/
    }

    // 566
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums.length * nums[0].length != r * c) {
            return nums;
        }
        int curr = 0;
        int[][] reshape = new int[r][c];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                reshape[curr / c][curr++ % c] = nums[i][j];
            }
        }
        return reshape;
    }
}