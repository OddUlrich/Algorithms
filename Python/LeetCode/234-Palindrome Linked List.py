'''
Given a singly linked list, determine if it is a palindrome.
'''

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        
        # Method 1:
        # l = []
        # while head != None:
        #     l.append(head.val)
        #     head = head.next
        # if l == l[::-1]:
        #     return True
        # else:
        #     return False
        
        # Method 2:
        # stack = []
        # tmp = head
        # while tmp != None:
        #     stack.append(tmp.val)
        #     tmp = tmp.next
        # cur = head
        # while cur != None:
        #     if stack[-1] == cur.val:
        #         stack.pop()
        #         cur = cur.next
        #     else:
        #         return False
        # return True
        
        # Method 3:
#         self.cur = head
#         return self.compare(head)
    
#     def compare(self, node):
#         if node == None:
#             return True
#         b = self.compare(node.next) and node.val == self.cur.val
#         self.cur = self.cur.next
#         return b
        
        # Method 4：
#         if head == None or head.next == None:
#             return True

#         slow, fast = head, head
#         stack = [slow.val]
#         while fast.next != None and fast.next.next != None:
#             slow = slow.next
#             fast = fast.next.next
#             stack.append(slow.val)

#         if fast.next == None:
#             stack.pop()  # Skip the middle val if the length of linked list is odd.
#         while slow.next != None:
#             slow = slow.next
#             if slow.val == stack[-1]:
#                 stack.pop()
#             else:
#                 return False
#         return True
        
        # Method 6: O(1) space
        if head == None or head.next == None:
            return True
        slow, fast = head, head
        while fast.next != None and fast.next.next != None:
            slow = slow. next
            fast = fast.next.next
        
        ###### (Excellent) Reverse the latter half linked list.
        last = slow.next
        while last.next != None:
            tmp = last.next
            last.next = tmp.next
            tmp.next = slow.next
            slow.next = tmp
        
        pre = head
        while slow.next != None:
            slow = slow.next
            if pre.val != slow.val:
                return False
            pre = pre.next
        return True
        
        
        
        
        
        