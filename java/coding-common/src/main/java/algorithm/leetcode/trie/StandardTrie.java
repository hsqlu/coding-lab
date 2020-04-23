package algorithm.leetcode.trie;

/**
 * @author Qiannan Lu
 * @date 17/05/2018.
 */
public class StandardTrie {

    class Node {
        Node[] children = new Node[26];
        boolean isLeaf;
    }

    private Node root = new Node();

    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node node) {
        if (node == null) {
            return;
        }
        if (word.length() == 0) {
            node.isLeaf = true;
            return;
        }
        int index = indexForChar(word.charAt(0));
        if (node.children[index] == null) {
            node.children[index] = new Node();
        }
        insert(word.substring(1), node.children[index]);
    }

    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, Node node) {
        if (node == null) {
            return false;
        }
        if (word.length() == 0) {
            return node.isLeaf;
        }
        int index = indexForChar(word.charAt(0));
        return search(word.substring(1), node.children[index]);
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix, root);
    }

    private boolean startsWith(String prefix, Node node) {
        if (node == null) {
            return false;
        }
        if (prefix.length() == 0) {
            return true;
        }
        int index = indexForChar(prefix.charAt(0));
        return startsWith(prefix.substring(1), node.children[index]);
    }


    private int indexForChar(char charIndex) {
        return charIndex - 'a';
    }

    public static void main(String[] args) {

        StandardTrie trie = new StandardTrie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("algorithm");
        trie.search("app");
        trie.search("alex");
        trie.startsWith("a");
    }

}
