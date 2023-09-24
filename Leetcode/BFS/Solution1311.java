import java.util.*;
import java.util.stream.Collectors;

public class Solution1311 {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Map<String, Integer> map = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{id, 0});
        visited[id] = true;
        while (queue.size() > 0) {
            int[] poi = queue.poll();
            if (poi[1] < level) {
                for (int friend : friends[poi[0]]) {
                    if (visited[friend]) continue;
                    visited[friend] = true;
                    queue.offer(new int[]{friend, poi[1] + 1});
                }
            } else {
                List<String> videos = watchedVideos.get(poi[0]);
                for (String video : videos) {
                    int count = map.getOrDefault(video, 0) + 1;
                    map.put(video, count);
                }
            }
        }

        List<String> res = new ArrayList<>(map.keySet());
        res = res.stream().sorted((x, y) -> {
            if (Objects.equals(map.get(x), map.get(y))) {
                return x.compareTo(y);
            } else {
                return map.get(x) - map.get(y);
            }
        }).collect(Collectors.toList());

        return res;
    }
}
