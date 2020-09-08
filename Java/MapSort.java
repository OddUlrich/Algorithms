import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MapSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        Map<String, Integer> cntMap = new HashMap<>();
        
        // 统计字符串出现次数。
        in.nextLine();
        for (int i = 0; i < N; ++i) {
            String s = in.nextLine();
            if (!cntMap.containsKey(s))  cntMap.put(s, 1);
            else  cntMap.put(s, cntMap.get(s) + 1);
        }
        
        // 排序：优先比较出现次数；若相同，则按字典序排列。
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(cntMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) {
                if (map2.getValue().compareTo(map1.getValue()) == 0) {
                    // 字典序，从小到大。
                    return map1.getKey().compareTo(map2.getKey());
                } else  return map2.getValue().compareTo(map1.getValue());
            }
        });
        int size = list.size();
        for (int i = 0; i < K; ++i) System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) {
                if (map1.getValue().compareTo(map2.getValue()) == 0) {
                    // 字典序，从小到大。
                    return map1.getKey().compareTo(map2.getKey());
                } else  return map1.getValue().compareTo(map2.getValue());
            }
        });
        for (int i = 0; i < K; ++i) System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
    }
}