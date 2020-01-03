'''
Write a program to find the node at which the intersection of two singly linked lists begins.
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        
        # Method 1
        # existed = []
        # while headA != None:
        #     existed.append(headA)
        #     headA = headA.next
        # while headB != None:
        #     if headB in existed:
        #         return headB
        #     else:
        #         headB = headB.next
        # return None
    

        # Method 2: compare from the end
        # ret = None
        # a = []
        # b = []
        # while headA != None:
        #     a.append(headA)
        #     headA = headA.next
        # while headB != None:
        #     b.append(headB)
        #     headB = headB.next
        
        # while a and b:
        #     a_node = a.pop()
        #     b_node = b.pop()
            
        #     if a_node == b_node:
        #         ret = a_node
        #     else:
        #         return ret
        # return ret
        
        # Method 3
        if headA == None or headB == None:
            return None
        a, b = headA, headB
        
        while a != b:
            if a == None:
                a = headB
            else:
                a = a.next
            
            if b == None:
                b = headA
            else:
                b = b.next
                
        return a
