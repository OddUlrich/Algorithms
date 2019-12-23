'''
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.
'''

class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        
        length = len(prices)
        if length < 2:
            return 0
        
        lowest_price = float('inf')
        profit = 0
        
        for price in prices:
            # Update if finding a lower price.
            if price < lowest_price:
                lowest_price = price
            # Update the profit if the current price is available to higher profit with the newest lowest_price.
            elif price - lowest_price > profit:
                profit = price - lowest_price
        return profit