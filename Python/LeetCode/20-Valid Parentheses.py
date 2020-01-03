'''
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
'''

class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        
#         stack = []
#         for c in s:
#             if len(stack) > 0 and stack[-1] == '(' and c == ')':
#                 stack.pop()    
#             elif len(stack) > 0 and stack[-1] == '{' and c == '}':
#                 stack.pop()
#             elif len(stack) > 0 and stack[-1] == '[' and c == ']':
#                 stack.pop()
#             else:
#                 stack.append(c)
        
#         if len(stack) == 0:
#             return True
#         else:
#             return False

        pairs = {"(": ")", "{": "}", "[": "]"}
        stack = []
        for i in range(len(s)):
            if s[i] in pairs:
                stack.append(s[i])
            elif len(stack) > 0 and s[i] == pairs[stack[-1]]:
                stack.pop()
            else:
                return False
                
        if len(stack) == 0:
            return True
        else:
            return False
    
