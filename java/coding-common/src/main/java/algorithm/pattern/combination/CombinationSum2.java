package algorithm.pattern.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        comb(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }


    void comb(int[] candidates, int index, int target, List<Integer> current, List<List<Integer>> result) {
        // Return result when target equals 0, must before the index bound check.
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index >= candidates.length || target < 0) {
            return;
        }

        if (candidates[index] == target) {
            current.add(candidates[index]);
            result.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i == index || candidates[i] != candidates[i-1] ) {
                current.add(candidates[i]);
                comb(candidates, i + 1, target - candidates[i], current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
