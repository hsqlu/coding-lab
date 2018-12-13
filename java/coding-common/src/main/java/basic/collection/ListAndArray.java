package basic.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListAndArray {

    public void demo() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        System.out.println(list);
    }
}
