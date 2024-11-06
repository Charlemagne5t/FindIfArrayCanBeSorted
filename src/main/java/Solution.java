import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canSortArray(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);

        Arrays.sort(sorted);
        int[] bitsCount = new int[nums.length];
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Deque<Integer> dq = map.getOrDefault(sorted[i], new ArrayDeque<>());
            dq.offer(i);
            map.put(sorted[i], dq);
        }
        for (int i = 0; i < nums.length; i++) {
            bitsCount[i] = Integer.bitCount(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int curCount = Integer.bitCount(nums[i]);
            int targetIndex = map.get(cur).poll();
            int start = Math.min(i, targetIndex);
            int end = Math.max(i, targetIndex);
            for (int j = start; j <= end; j++) {
                if (Integer.bitCount(nums[j]) != curCount) {
                    return false;
                }
            }

        }

        return true;
    }
}