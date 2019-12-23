'''
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
'''

class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        # Thinking from the last step:
        # (n-1) steps + 1 or (n-2) steps + 2
        # Finally the answer becomes a Fiboracci sequence.
        
        # Let f(n) be the number of ways on reaching the n th step,
        # then f(n) = f(n-1) + f(n-2);
        # ...
        # f(3) = f(2) + f(1); f(2) = 2; f(1) = 1.
        
        if n == 1:
            return 1
        if n == 2:
            return 2
        
        # May be stack overloaded.
        # return self.climbStairs(n-1) + self.climbStairs(n-2)
        
        # F = [1, 2]
        # for i in range(n-2):
        #     F.append(F[-1] + F[-2])
        # return F[-1]
        
        s = 0
        a = 2
        b = 1
        for i in range(n-2):
            s = a + b
            b = a
            a = s
            
        return s
        
        