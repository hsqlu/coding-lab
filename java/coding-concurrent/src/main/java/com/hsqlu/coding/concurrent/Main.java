package com.hsqlu.coding.concurrent;

import java.util.*;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Main {


    public static List<Long> sumValues(List<Integer> parents, List<Integer> startPoint, List<Integer> jumpLength) {
        // Write your code here
        Map<String, Long> cache = new HashMap<>();
        List<Long> ans = new ArrayList<>(startPoint.size());

        TreeMap<Integer, Set<Integer>> reorg = new TreeMap<>();
        for (int i = 0; i < startPoint.size(); i++) {
            Set<Integer> m = reorg.getOrDefault(startPoint.get(i), new TreeSet<>());
            m.add(i);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : reorg.entrySet()) {
            for (int index : entry.getValue()) {
                sumValues(cache, parents, entry.getKey(), jumpLength.get(index));
            }
        }


        for (int i = 0; i < startPoint.size(); i++) {
            ans.add(sumValues(cache, parents, startPoint.get(i), jumpLength.get(i)));
        }
        return ans;

    }

    private static long recursiveSumValues(Map<String, Long> cache, List<Integer> parents, int startPoint, int jumpLength) {
        String key = startPoint + "-" + jumpLength;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (parents.get(startPoint) == 0) {
            return startPoint;
        }

        long ans = 0;
        ans += startPoint;
        int parent = startPoint;
        while (true) {
            for (int i = jumpLength; i > 0; i--) {
                parent = parents.get(parent);
                if (parent <= 0) {
                    break;
                }
            }
            if (parent <= 0) {
                break;
            }

            String cacheKey = parent + "-" + jumpLength;
            if (cache.containsKey(cacheKey)) {
                return ans + cache.get(cacheKey);
            } else {
                long res = recursiveSumValues(cache, parents, parent, jumpLength);
                cache.put(cacheKey, res);
                ans += res;
                return ans;
            }
        }
        cache.put(key, ans);
        return ans;
    }

    private static long sumValues(Map<String, Long> cache, List<Integer> parents, int startPoint, int jumpLength) {
        String key = startPoint + "-" + jumpLength;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long ans = 0;
        ans += startPoint;
        int parent = startPoint;
        while (true) {
            for (int i = jumpLength; i > 0; i--) {
                parent = parents.get(parent);
                if (parent <= 0) {
                    break;
                }
            }
            if (parent <= 0) {
                break;
            }

            String cacheKey = parent + "-" + jumpLength;
            if (cache.containsKey(cacheKey)) {
                return ans + cache.get(cacheKey);
            } else {
                ans += parent;
            }
        }
        cache.put(key, ans);
        return ans;
    }

//    private static long sumValues(Map<String, Long> cache, List<Integer> parents, int startPoint, int jumpLength) {
//        String key = startPoint + "-" + jumpLength;
//        if (cache.containsKey(key)) {
//            return cache.get(key);
//        }
//
//        if (parents.get(startPoint) == 0) {
//            return startPoint;
//        }
//
//        long ans = 0;
//        ans += startPoint;
//        int parent = startPoint;
//        while (true) {
//            for (int i = jumpLength; i > 0; i--) {
//                parent = parents.get(parent);
//                if (parent <= 0) {
//                    break;
//                }
//            }
//            if (parent <= 0) {
//                break;
//            }
//
//            String cacheKey = parent + "-" + jumpLength;
//            if (cache.containsKey(cacheKey)) {
//                return ans + cache.get(cacheKey);
//            } else {
//                long res = sumValues(cache, parents, parent, jumpLength);
//                cache.put(cacheKey, res);
//                ans += res;
//                return ans;
//            }
//        }
//        cache.put(key, ans);
//        return ans;
//    }


    public static boolean isValid(List<Integer> nodeValues) {
        // Write your code here
        if (nodeValues == null || nodeValues.isEmpty() || nodeValues.size() == 1) {
            return true;
        }
        int root = nodeValues.get(0);
        int index = 1;
        for (int i = 1; i < nodeValues.size(); i++) {
            if (nodeValues.get(i) < root) {
                index++;
                continue;
            }
            break;
        }

        for (int i = index; i < nodeValues.size(); i++) {
            if (nodeValues.get(i) < root) {
                return false;
            }
        }
        if (index <= 2) {
            return isValid(nodeValues.subList(index, nodeValues.size()));
        }

        return isValid(nodeValues.subList(1, index - 1)) && isValid(nodeValues.subList(index, nodeValues.size()));
    }

    private static boolean fastCheck(List<Integer> nodeValues) {
        if (nodeValues.isEmpty() || nodeValues.size() == 1) {
            return true;
        }

        int slow = nodeValues.get(0);
        int fast = nodeValues.get(1);

        if (slow < fast) {
            for (int i = 2; i < nodeValues.size(); i++) {
                slow = fast;
                fast = nodeValues.get(i);
                if (slow > fast) {
                    return false;
                }
            }
        } else {
            for (int i = 2; i < nodeValues.size(); i++) {
                slow = fast;
                fast = nodeValues.get(i);
                if (slow < fast) {
                    return false;
                }
            }
        }

        return true;
    }


    public static List<String> funWithAnagrams(List<String> s) {
        // Write your code here
        TreeSet<String> ans = new TreeSet<>();

        for (String each : s) {
            if (ans.isEmpty()) {
                ans.add(each);
            } else if (!containsAnagram(ans, each)) {
                ans.add(each);
            }
        }

        return new LinkedList<>(ans);
    }


    private static boolean containsAnagram(Set<String> holder, String s) {
        for (String each : holder) {
            if (each.length() != s.length()) {
                return false;
            }
            char[] chars = s.toLowerCase().toCharArray();
            char[] existChars = each.toCharArray();
            Arrays.sort(chars);
            Arrays.sort(existChars);

            if (new String(chars).equals(new String(existChars))) {
                return true;
            }
        }

        return false;
    }




    public static void fizzBuzz(int n) {
        // Write your code here

        for (int i = 1; i <= n; i++) {
            System.out.println(fizzBuxx(i));
        }

        Queue queue = new LinkedList();
    }

    private static String fizzBuxx(int n) {
        if (n % 5 ==  0 && n % 3 == 0) {
            return "FizzBuzz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else {
            return String.valueOf(n);
        }
    }

    public static void main(String[] args) {
        List tree = new ArrayList();
        tree.add(-1);
        tree.add(0);
        tree.add(1);
        tree.add(1);
        tree.add(2);
        tree.add(4);
        System.out.println(tree);
        long ans = sumValues(new HashMap<String, Long>(), tree, 5, 1);
        System.out.println(ans);
//        System.out.print(Thread.currentThread().getName());

//       new Thread (() -> System.out.print(Thread.currentThread().getName())).start();



        /*
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; ++i) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();*/
    }

    /*private static void demoTask() {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; ++i) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }*/

    private void demoLoader() {
        DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
        NetworkConnectionsLoader networkConnectionsLoader = new NetworkConnectionsLoader();
        Thread t1 = new Thread(dataSourcesLoader, "DataSourceThread");
        Thread t2 = new Thread(networkConnectionsLoader, "NetworkConnectionsThread");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
