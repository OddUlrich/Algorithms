import java.util.*;

public class Solution365 {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {

        Queue<int[]> stateQueue = new LinkedList<>();
        Set<Long> existedState = new HashSet<>();

        stateQueue.offer(new int[]{0, 0});

        while (!stateQueue.isEmpty()) {
            int[] tmpState = stateQueue.peek();

            if (existedState.contains(hash(tmpState[0], tmpState[1]))) {
                stateQueue.poll();
                continue;
            }

            int[] state = stateQueue.poll();
            int x = state[0];
            int y = state[1];
            if (x == targetCapacity || y == targetCapacity || x + y == targetCapacity) {
                return true;
            }
            existedState.add(hash(x, y));

            // 1. full x
            stateQueue.offer(new int[]{jug1Capacity, y});
            // 2. full y
            stateQueue.offer(new int[]{x, jug2Capacity});
            // 3. empty x
            stateQueue.offer(new int[]{0, y});
            // 4. empty y
            stateQueue.offer(new int[]{x, 0});
            // 5. pour x to y
            stateQueue.offer(new int[]{x - Math.min(x, jug2Capacity - y), y + Math.min(x, jug2Capacity - y)});
            // 6. pour y to x
            stateQueue.offer(new int[]{x + Math.min(y, jug1Capacity - x), y - Math.min(y, jug1Capacity - x)});

        }

        return false;
    }

    private Long hash(int x, int y) {
        return (long) x * 1000001 + y;
    }

}
