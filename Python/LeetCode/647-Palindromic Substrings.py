'''
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
'''

# class Solution(object):
# 	# Method 1.
#     def countSubstrings(self, s):
#         """
#         :type s: str
#         :rtype: int
#         """
        
#         cnt = 0
#         for i in range(len(s)):
#             # Odd length substrings.
#             cnt += self.checkFromMid(s, i, i)
#             # Even length substrings.
#             cnt += self.checkFromMid(s, i, i+1)
        
#         return cnt
    
#     def checkFromMid(self, s, left, right):
#         '''
#             Left and right are the middle indeces as starts. Check a substring whether a palindromic           string or not from its middle.
#         '''
#         cnt = 0
#         while left >= 0 and right < len(s) and s[left] == s[right]:
#             # Two edge's char is the same.
#             left -= 1
#             right += 1
#             cnt += 1
#         return cnt

class Solution(object):
	# Method 2: Dynamic Programming.
    def countSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """

        # Dynamic Programming.
        '''
            1. One-length substrings are always Palindromic Substrings.
            2. Two-length substrings are always not Palindromic Substrings due to different characters at the start and the end.
            3. Substrings whose lengths are over three are Palindromic Substrings only then their start and end characters are the same, and their substrings without the start and the end characters are still substrings.

            Therefore, the first two condition can be used as a basic step, and then generate the third situation with the previous two results.
        '''

        length = len(s)

        # Input check.
        if length == 0:
            return 0
        if length == 1:
            return 1

        # Create a len(s) by len(s) arrays to record the result of each substring by locating on the start and the end indices.
        dp = [[False for j in range(length)] for i in range(length)]

        cnt = 0

        # ith row; jth col.
        for i in range(length):
            for j in range(i+1):
                # One-length substring.
                if i == j:
                    dp[i][j] = True
                    cnt += 1
                # Two-length substring.
                elif i == j+1 and s[i] == s[j]:
                    dp[i][j] = True
                    cnt += 1
                # Inner substring is Palindromic Substring and the others characters are the same.
                elif i > 0 and dp[i-1][j+1] and s[i] == s[j]:
                    dp[i][j] = True
                    cnt += 1
        return cnt