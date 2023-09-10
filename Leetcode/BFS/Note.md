# BFS(Breadth First Search) - 广度优先搜索


使用场景：
- 层序遍历：Query<Node> q = new LinkedList<>();
- 完全二叉树：编码规律，每一个节点的编号与其左右节点编号的关系 x -> 2x,2x+1
- 问题建模 - 有限状态机：每个状态的下一个状态可以看做是下一层的子节点（注意使用Set对已经出现过的状态）
- 最短路径问题：四维宫格寻路


重要思想以及编码技巧：
- 由于二叉树每一层的处理逻辑基本相同（有时候根节点需要特殊处理），因此可以使用递归方法依次对下一层的子节点进行处理。
- 如果有额外的特征（比如数值与深度、横纵坐标等）需要与层级遍历的节点共同维护的，则可以定义一个相同的结构，保持同样步长进行遍历。
  - 可参考 [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/description/)
- 有限状态机：每个状态的下一个状态可以看做是下一层的子节点（注意使用Set对已经出现过的状态进行记录，可以通过高低位hash对状态进行编码）
  - 可参考 [365. 水壶问题](https://leetcode.cn/problems/water-and-jug-problem/description/)
- 对于二维数组寻路问题，可以预定义两个数据 dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0} 模拟上右下左四个方向的变化步长。


