'''
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
'''

class Solution(object):
    def dailyTemperatures(self, T):
        """
        :type T: List[int]
        :rtype: List[int]
        """ 
        
        if T == None or len(T) == 0:
            return []
        
        # Stack: greater then pop; smaller then append.
        stack = []
        ret = [0] * len(T)
        
        for i in range(len(T)):
            while len(stack) > 0 and T[i] > T[stack[-1]]:
                ret[stack[-1]] = i - stack[-1]
                stack.pop(-1)
                
            # Save indeces in the stack.
            stack.append(i)
            
        return ret
        
        