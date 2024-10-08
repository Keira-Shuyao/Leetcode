import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class topKElement {
    public static void main(String[] args) {
        int nums[] = new int[]{3,3,3,1,1,2};
        int k = 2;
        int result[] = topKFrequent(nums, k);
        System.out.println(result);
    }


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i : nums){
            //记住这个map的method！！！
            map.put(i, map.getOrDefault(i,0)+1);
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> map.get(b)-map.get(a));

        for(int key:map.keySet()){
            maxHeap.add(key);
        }

        int res[] = new int[k];
        for(int i = 0; i<k; i++){
            res[i] = maxHeap.poll();
        }
        return res;

    }
}
