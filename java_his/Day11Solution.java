package xin.jiuzhiqian.study.leetcode_history;

/**
 * @author : zhou
 */
public class Day11Solution {
    public static void main(String[] args) {
        Day11Solution solution = new Day11Solution();
//        int x = 9, y = 5;
//        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
//        int[] a = {1, 2, 3, 4}, b = {1, 4};
        String s = "2-5g-3-J";
        System.out.println(solution.licenseKeyFormatting(s, 2));
    }

    // 492
    public int[] constructRectangle(int area) {
        int skr = (int) Math.sqrt(area);
        if (skr * skr == area) {
            return new int[]{skr, skr};
        }
        for (int i = skr; i >= 1; i--) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
        return new int[2];
    }

    // 485
    public int findMaxConsecutiveOnes(int[] nums) {
        int last = 0, curr = 0;
        for (int num : nums) {
            if (num == 0) {
                if (curr != 0) {
                    last = Math.max(last, curr);
                }
                curr = 0;
            } else {
                curr++;
            }
        }
        return Math.max(last, curr);
    }

    // 482
    public String licenseKeyFormatting(String S, int K) {
        int len = S.length() - 1;
        if (len < 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        char replace = '-';
        int tmp = 0;
        while (len >= 0) {
            if (S.charAt(len) == replace) {
                len--;
                continue;
            }
            if (++tmp > 1) {
                if (K == 1 || tmp % K == 1) {
                    stringBuilder.append('-');
                }
            }
            stringBuilder.append(Character.toUpperCase(S.charAt(len--)));
        }
        return stringBuilder.reverse().toString();
    }

    /*// 475 double pointer
    public int findRadius(int[] houses, int[] heaters) {
        // GG
        *//*int maxDistance = 0, heaterIndex = 0;
        for (int i = 0; i < houses.length; i++) {
            maxDistance = Math.max(maxDistance, heaters[heaterIndex] - houses[i]);
            while (houses[i] < heaters[heaterIndex]) {
                i++;
            }
            if (i < houses.length - 1) {
                maxDistance = Math.max(maxDistance, houses[i + 1] - heaters[heaterIndex]);
            }
            if (++heaterIndex == heaters.length) {
                if (i < houses.length - 1) {
                    maxDistance = Math.max(maxDistance, houses[houses.length - 1] - heaters[heaterIndex - 1]);
                }
                break;
            }else {
                Math.max(Math.abs(houses))
            }
        }
        return maxDistance;*//*
    }*/

    // 463
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                    perimeter += 4;
                }
            }
        }
        return perimeter;
    }

    // 461 another binary
    public int hammingDistance(int x, int y) {
        int han, ming, distance = 0;
        while (x > 0 || y > 0) {
            if (x > 0) {
                han = x % 2;
                x /= 2;
            } else {
                han = 0;
            }
            if (y > 0) {
                ming = y % 2;
                y /= 2;
            } else {
                ming = 0;
            }
            if (han != ming) {
                distance++;
            }
        }
        return distance;
    }
}