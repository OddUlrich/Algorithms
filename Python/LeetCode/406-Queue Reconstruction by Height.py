'''
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.
'''

class Solution(object):
    def reconstructQueue(self, people):
        """
        :type people: List[List[int]]
        :rtype: List[List[int]]
        """
        
        # Method 1: Sorted directly.
#         queue = sorted(people, key = lambda x: (-x[0], x[1]))
        
#         res = []
#         for p in queue:
#             res.insert(p[1], p)
#         return res

        # Method 2: heap
        queue_heap = []
        for p in people:
            heapq.heappush(queue_heap, (-p[0], p[1]))
        
        res = []    
        while len(queue_heap) > 0:
            height, pos = heapq.heappop(queue_heap)
            
            if pos >= len(res):
                res.append([-height, pos])
            else:
                res.insert(pos, [-height, pos])

        return res        