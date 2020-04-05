'''
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
'''

class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        
        if not n:
            return None
        res = []
        self.generate(0, 0, n, "", res)
        return res
        
    def generate(self, left, right, n, string, res):
        # Required result.
        if left+right == 2*n:
            res.append(string)
            return
        
        # Recursive Step.
        if left < n:
            self.generate(left+1, right, n, string+"(", res)
        
        if left > right:
            self.generate(left, right+1, n, string+")", res)
    
        
