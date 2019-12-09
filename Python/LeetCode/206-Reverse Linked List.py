'''
Reverse a singly linked list.

A linked list can be reversed either iteratively or recursively. Could you implement both?
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        # Iteratively.
#         tmpNode = None
#         preNode = None
#         curNode = head
#         while curNode != None:
#             # Marked with pointer.
#             preNode = ListNode(curNode.val)
            
#             # Update temp list.
#             preNode.next = tmpNode
#             tmpNode = preNode
            
#             # Continue to the next node.
#             curNode = curNode.next
        
#         return tmpNode

        # Recursively.
        return self.recurse(head, None)
    
    def recurse(self, curNode, tmpNode):
        if curNode == None:
            return tmpNode
        
        # Update temp list.
        head = ListNode(curNode.val)
        head.next = tmpNode
        
        return self.recurse(curNode.next, head)
            
        
