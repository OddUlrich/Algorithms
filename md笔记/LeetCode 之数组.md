# LeetCode 之数组





## 子序列去重

* 串哈希算法（Rabin-Karp 编码）

即对于一个序列 $a_0, a_1, ..., a_{n-1}$， 我们可以认为是一个 $max\{a_i\} + 1$ 进制的数，这个数的数值等于（记 $b = max\{a_i\} + 1$）：
$$
f(a) = \Sigma^{n-1}_{i=0} b^i \times a_i
$$

每次我们找到一个合法序列的时候，都去计算这个序列的哈希值，用一个哈希表来记录已有的哈希值，如果该值已经出现在哈希表中，就舍弃这个序列，否则就把这个序列加入到答案中。

在实现的过程中，我们发现这个哈希值可能非常大，我们可以将它模一个大素数 $P$。所以实际上这里的哈希函数是：

$$
f(a) = \Sigma^{n-1}_{i=0} b^i \times a_i (\text{ mod } P)
$$


而这里的 $b$ 也未必是 $max\{a_i\} + 1$，它可以任意选一个大于  $max\{a_i\} + 1$ 的数。

```java
class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    Set<Integer> set = new HashSet<Integer>();
    int n;

    public List<List<Integer>> findSubsequences(int[] nums) {
        n = nums.length;
        for (int i = 0; i < (1 << n); ++i) {
            findSubsequences(i, nums);
            int hashValue = getHash(263, (int) 1E9 + 7);
            if (check() && !set.contains(hashValue)) {
                ans.add(new ArrayList<Integer>(temp));
                set.add(hashValue);
            }
        }
        return ans;
    }

    public void findSubsequences(int mask, int[] nums) {
        temp.clear();
        for (int i = 0; i < n; ++i) {
            if ((mask & 1) != 0) {
                temp.add(nums[i]);
            }
            mask >>= 1;
        }
    }

    public int getHash(int base, int mod) {
        int hashValue = 0;
        for (int x : temp) {
            hashValue = hashValue * base % mod + (x + 101);
            hashValue %= mod;
        }
        return hashValue;
    }

    public boolean check() {
        for (int i = 1; i < temp.size(); ++i) {
            if (temp.get(i) < temp.get(i - 1)) {
                return false;
            }
        }
        return temp.size() >= 2;
    }
}
```





## 数组（子数组通常指的是取原数组中位置连续的部分）

* 计算具有奇数和的子数组的数量（奇数计数器+偶数计数器）



* 峰值计数、峰谷截断。

```java
/**
* 给定整数数组target和initial，两者有同样的维度，target一开始全部为0 。返回从initial得到target的最少操作次数，每次操作仅能在initial中选择“任意”子数组，并将子数组中每个元素增加1。
*/

int minNumberOperations(int[] target) {
    int n = target.length;
    int ans = target[0];
    for (int i = 1; i < n; ++i) {
        ans += Math.max(target[i] - target[i - 1], 0);
    }
    return ans;
}
```

* 峰谷限流，逐级降低。

```java

```

