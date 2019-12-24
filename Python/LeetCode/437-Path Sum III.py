'''
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
'''

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """
        
        # DFS
        if root == None:
            return 0
        return self.pathFrom(root, sum) + self.pathSum(root.left, sum) + self.pathSum(root.right, sum)
        
    def pathFrom(self, node, val):
        if node == None:
            return 0
        
        cnt = self.pathFrom(node.left, val - node.val) + self.pathFrom(node.right, val - node.val)
        if node.val == val:
            cnt += 1
        return cnt