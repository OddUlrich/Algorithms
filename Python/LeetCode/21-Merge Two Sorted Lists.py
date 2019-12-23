'''
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        
        head = ListNode(None)
        curNode = head
        p1 = l1
        p2 = l2
        
        # Merge from the second node.
        while p1 != None and p2 != None:
            if p1.val <= p2.val:
                curNode.next = p1
                p1 = p1.next
            else:
                curNode.next = p2
                p2 = p2.next
            curNode = curNode.next
        
        if p1 == None:
            curNode.next = p2
        else:
            curNode.next = p1
        
        # Skip the first empty node.
        return head.next
            