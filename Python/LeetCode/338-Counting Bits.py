'''
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.


It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
'''

class Solution(object):
    def countBits(self, num):
        """
        :type num: int
        :rtype: List[int]
        """
        
        # Method 1
#         bitCnt = [0]
#         for n in range(1,num+1):
#             bitCnt.append(format(n,'b').count('1'))
            
#         return bitCnt

        # Method 2
#         bitCnt = [0]
#         while len(bitCnt) < num+1:
#             bitCnt.extend([i+1 for i in bitCnt])
            
#         return bitCnt[:num+1]

        # Method 3: Dynamic Programming
        bitCnt = [0]*(num+1)
        for i in range(1, num+1):
            bitCnt[i] = bitCnt[i>>1] + i%2
        return bitCnt