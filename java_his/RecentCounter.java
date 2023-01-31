package xin.jiuzhiqian.study.leetcode_history;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : feng
 */
public class RecentCounter {
    private List<Integer> arrayList;

    public RecentCounter() {
        arrayList = new ArrayList<>();
    }

    public int ping(int t) {
        arrayList.add(t);
        while (arrayList.get(0) + 3000 < t) {
            arrayList.remove(0);
        }
        return arrayList.size();
    }
}