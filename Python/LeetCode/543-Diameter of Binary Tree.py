'''
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
'''

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def diameterOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None:
            return 0
        
        diameter = self.maxDepth(root.left) + self.maxDepth(root.right)
        return max(diameter, 
                   self.diameterOfBinaryTree(root.left), 
                   self.diameterOfBinaryTree(root.right))
        
        
    def maxDepth(self, node):
        if node == None:
            return 0
        
        return 1 + max(self.maxDepth(node.left), self.maxDepth(node.right))
