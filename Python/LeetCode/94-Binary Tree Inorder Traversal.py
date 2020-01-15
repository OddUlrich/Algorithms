'''
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
'''

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        
        # Recursively.
        # ret = []
        
        # if root == None:
        #     return []
        
        # if root.left != None:
        #     ret += self.inorderTraversal(root.left)
        
        # ret.append(root.val)
        
        # if root.right != None:
        #     ret += self.inorderTraversal(root.right)
        
        # return ret

        # Iteratively.
        stack, ret, cur = [], [], root
        while True:
            if cur != None:
                # Push the middle root node.
                stack.append(cur)
                cur = cur.left
            elif len(stack) > 0:
                # Pop the middle root node and record its value.
                cur = stack.pop()
                ret.append(cur.val)
                cur = cur.right
            else:
                break
        return ret
        

