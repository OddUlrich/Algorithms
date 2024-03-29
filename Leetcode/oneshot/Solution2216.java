public class Solution2216 {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        boolean check = true;
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] == nums[i + 1] && check) {
                ++ans;
            } else {
                check = !check;
            }
        }
        if ((n - ans) % 2 != 0) {
            ++ans;
        }
        return ans;
    }
}
