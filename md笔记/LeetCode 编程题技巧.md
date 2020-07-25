## LeetCode 编程题技巧





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

        





