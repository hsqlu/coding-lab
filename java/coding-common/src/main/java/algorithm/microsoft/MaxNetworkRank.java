package algorithm.microsoft;

import java.util.HashMap;
import java.util.Map;

public class MaxNetworkRank {


    public static int solution(int[] A, int[] B, int N) {
        int sum = 0;
        Map<Integer, Integer> edgesMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            edgesMap.put(A[i], edgesMap.getOrDefault(A[i], 0) + 1);
            edgesMap.put(B[i], edgesMap.getOrDefault(B[i], 0) + 1);
        }

        for (int i = 0; i < A.length; i++) {
            sum = Math.max(sum, edgesMap.get(A[i]) + edgesMap.get(B[i]) - 1);
        }
        return sum;
    }
}
