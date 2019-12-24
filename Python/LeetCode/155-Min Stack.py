'''
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
'''


class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
        
class MinStack(object):

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.val = None
        self.next = None
        self.min = ListNode(None)
        

    def push(self, x):
        """
        :type x: int
        :rtype: None
        """
        # Add new node and update the pointer of next.
        tmp = ListNode(None)
        tmp.val = self.val
        tmp.next = self.next
        
        self.val = x
        self.next = tmp
        
        # Update the list of min.
        if self.min == None or self.min.val == None:
            self.min = ListNode(x)
        elif x <= self.min.val: # Add the new minimum to the head.
            tmpMin = ListNode(None)
            tmpMin.val = self.min.val
            tmpMin.next = self.min.next
            
            self.min.val = x
            self.min.next = tmpMin
        

    def pop(self):
        """
        :rtype: None
        """
        
        if self.val == self.min.val:
            self.min = self.min.next
        
        self.val = self.next.val
        self.next = self.next.next
        

    def top(self):
        """
        :rtype: int
        """
        return self.val
        

    def getMin(self):
        """
        :rtype: int
        """
        return self.min.val
   
    


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()