'''
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
'''

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        
        if root == None:
            return True
        
        # Iteratively.
        if root.left == None and root.right == None:
            return True
        if root.left == None or root.right == None:
            return False
        
        left_list = [root.left]
        right_list = [root.right]
        while len(left_list) > 0 and len(right_list) > 0:
            if left_list[0].val == right_list[-1].val:
                n1 = left_list.pop(0)
                n2 = right_list.pop(-1)
                
                if n1.right != None and n2.left != None:
                    left_list.insert(0, n1.right)
                    right_list.append(n2.left)
                elif n1.right == None and n2.left == None:
                    pass
                else:
                    return False
                
                if n1.left != None and n2.right != None:
                    left_list.insert(0, n1.left)
                    right_list.append(n2.right)
                elif n1.left == None and n2.right == None:
                    pass
                else:
                    return False
            else:
                return False
        return True
                
        
        # Recursively.
#         return self.isEqual(root.left, root.right)
    
#     def isEqual(self, n1, n2):
#         if n1 == None and n2 == None:
#             return True
#         elif n1 == None or n2 == None:
#             return False
#         elif n1.val == n2.val:
#             return self.isEqual(n1.left, n2.right) and self.isEqual(n1.right, n2.left)
#         else:
#             return False