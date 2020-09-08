## LeetCode 算法题技巧



## 排列组合









## 链表

> 常见结构：单（向）链表、双（向）链表、循环链表、二叉链表（左子女、右兄弟）



* 快慢指针协同定位
    * 取链表倒数第 k 个结点（两个指针间距为 k）。
    * 判断链表是否为循环链表（快慢指针追逐）。
    * 回文链表（快慢指针步长分别为1、2，实现二分，再结合栈或者反转前半链表进行判断）
    * 判断两个链表是否相交，即有相同的后续部分（尾结点分别接上另一个链表头结点，即 A+B 和 B+A，则在遍历第二遍两指针必然相遇）。

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode pA = headA, pB = headB;
    if (pA == null || pB == null) {
        return null;
    }
    while (!(pA == null && pB == null)) {
        // Not reach the end at the same time for A+b and B+A.
        if (pA == null)  pA = headB;
        if (pB == null)  pB = headA;

        if (pA == pB)  return pA;
        else {
            pA = pA.next;
            pB = pB.next;
        }
    }
    return null;
}
```

* 使用虚拟头结点
    * 合并两个有序链表（return vHead.next;）
* 删除节点
    * 定义一个临时结点 curNode = pos.next，通过 next 结点进行判断能保证在删除结点时可取到父结点。
    * 通过条件筛选，以跳过结点的形式删除结点的，最终返回前记得检查末结点的下一个结点是否为空。
    * 只给定待删除的中间结点（将往后每个结点的 val 值依次向前覆盖，抛弃最后一个节点）

```java
public void deleteNode(ListNode node) {
    ListNode n = node;
    while (n.next != null && n.next.next != null) {
        n.val = n.next.val;
        n = n.next;
    }
    n.val = n.next.val;
    n.next = null;
}
```

* 倒序相关，可以使用栈的。
    * 从尾到头打印链表。
    
    * 递归反转单链表。
    
    * ```java
        class Solution {
            public ListNode reverseList(ListNode head) {
                if(head == null || head.next == null) {
                    return head;
                }
                ListNode newHead = reverseList(head.next);
                head.next.next = head;
                head.next = null;
                return newHead;
            }
        }
        ```
    
    * 迭代反转单链表。

```java
public ListNode reverseList(ListNode head) {
    ListNode tmpNext, first = head, last = null;
    while (first != null) {
        tmpNext = first.next;
        first.next = last;
        last = first;
        first = tmpNext;
    }

    return last;
}
```

* 使用 hash 表 HashSet
    * 判断重复出现的结点
* 二叉树相关的转化
    * 最好先转化成数组，通过中间位置的结点进行二分。
    * 有序链表对应二叉排序树
* 从链表中删去总和为零的连续结点

```java
public ListNode removeZeroSumSublists(ListNode head) {
    ListNode node = new ListNode(0);
    node.next = head;

    Map<Integer, ListNode> map = new HashMap<>();

    int sum = 0;
    ListNode p = node;
    // Map the sum to nodes.
    while (p != null) {
        sum += p.val;
        map.put(sum, p);

        p = p.next;
    }

    sum = 0;
    p = node;
    // If the sum between two nodes equal, then the interval nodes sums up to 0.
    while (p != null) {
        sum += p.val;
        p.next = map.get(sum).next;
        p = p.next;
    }

    return node.next;
}
```





## 队列

> 常见结构：输入输出皆受限的队列、双向队列、输入受限输出不受限的队列、输入不受限输出受限的队列、循环队列、链队列

* 头结点：front
* 尾结点：rear



## 树



* 检查平衡性

    * ```java
        public boolean isBalanced(TreeNode root) {
            // 返回树的深度。
            return dfs(root) != -1;
        }
        
        public int dfs(TreeNode node) {
            if (node == null) return 0;
        
            int leftDep = dfs(node.left);
            if (leftDep == -1) return -1;
            int rightDep = dfs(node.right);
            if (rightDep == -1) return -1;
        	
            // 左右子树比较。
            if (Math.abs(leftDep - rightDep) > 1) return -1;
        
            // 把当前根结点计入。
            return Math.max(leftDep, rightDep) + 1;
        }
        ```

        





## 图



### 欧拉通路与欧拉回路

**欧拉通路**：从指定顶点出发，通过图中所有边恰好依次且走遍所有顶点的通路。

**欧拉回路**：从指定顶点出发，通过图中所有边恰好依次且走遍所有顶点的回路。

两者的共同点在于，每条边都经过且只能恰好经过一遍，顶点可以多次经过。具有欧拉回路的无向图称为**欧拉图**，具有欧拉通路但不具有欧拉回路的无向图称为**半欧拉图**。



在连通图中寻找欧拉路径，常用 HierHolzer 算法：

1. 从起点出发，进行深度优先搜索（DFS）。

2. 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。

3. 如果没有可移动的路径，则将所在节点加入到栈中，并返回。


当我们顺序地考虑该问题时，我们也许很难解决该问题，因为我们无法判断当前节点的哪一个分支是「死胡同」分支，不妨倒过来思考。我们注意到只有那个入度与出度差为 1 的节点会导致死胡同。而该节点必然是最后一个遍历到的节点。我们可以改变入栈的规则，当我们遍历完一个节点所连的所有节点后，我们才将该节点入栈（即逆序入栈）。

对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点。而从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈。这样就能保证我们可以「一笔画」地走完所有边，最终的栈中逆序地保存了「一笔画」的结果。我们只要将栈中的内容反转，即可得到答案

```java
Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
List<String> itinerary = new LinkedList<String>();

public List<String> findItinerary(List<List<String>> tickets) {
    for (List<String> ticket : tickets) {
        String src = ticket.get(0), dst = ticket.get(1);
        if (!map.containsKey(src)) {
            map.put(src, new PriorityQueue<String>());
        }
        map.get(src).offer(dst);
    }
    dfs("JFK");
    Collections.reverse(itinerary);
    return itinerary;
}

public void dfs(String curr) {
    while (map.containsKey(curr) && map.get(curr).size() > 0) {
        String tmp = map.get(curr).poll();
        dfs(tmp);
    }
    itinerary.add(curr);
}
```



* 给定相连结点的连通问题

```java
int[][] egdes = new int[n][];  // 实现 c++ 中的 vector。
```





## 哈希表



* 字符串统计排序问题
    * 使用 HashMap 统计字符串个数。
    * 将 HashMap 的每一对 key-value 作为元素插入到 List 中，再用 sort 排序。
    * 重写 sort，使之先比较 value， 相等时再比较 key。

```java
public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int K = in.nextInt();
    Map<String, Integer> cntMap = new HashMap<>();

    // 统计字符串出现次数。
    in.nextLine();
    for (int i = 0; i < N; ++i) {
        String s = in.nextLine();
        cntMap.put(str, cntMap.getOrDefault(s, 0) + 1);
    }

    // 排序：优先比较出现次数；若相同，则按字典序排列。
    List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(cntMap.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) {
            if (map2.getValue().compareTo(map1.getValue()) == 0) {
                // 字典序，从小到大。
                return map1.getKey().compareTo(map2.getKey());
            } else  return map2.getValue().compareTo(map1.getValue());
        }
    });
```

* 字符串排序另一种思路
    * 遍历每一项，找出每次的最优项后将其删除。

```java
int max = 0;
String maxString = null;
// 硬拷贝一份 HashMap 的副本。
HashMap<String, Integer> map2 = (HashMap<String, Integer>) map1.clone();
for (int i = 0; i < k; i++) {  // 找前k个。
    for (String string2 : map2.keySet()) {
        if (map2.get(string2) > max ||
            (map2.get(string2) == max && string2.compareTo(maxString) < 0)) {
            max = map2.get(string2);
            maxString = string2;
        }
    }
    // 遍历找出符合条件的最优项，然后将其从表中删去。
    map2.remove(maxString);
    System.out.println(maxString + " " + max);
    max = 0;
}
```

