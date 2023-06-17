package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        V result;
        if(cmp < 0){
            result = getHelper(key, p.left);
        }else if(cmp > 0){
            result = getHelper(key, p.right);
        }else{
            result = p.value;
        }
        return result;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if(key == null){
            throw new IllegalArgumentException("key is null!");
        }
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
       if(p == null){
           size += 1;
           return new Node(key, value);
       }
       int cmp = key.compareTo(p.key);
       if(cmp < 0){
           p.left = putHelper(key, value, p.left);
       } else if (cmp > 0) {
           p.right = putHelper(key, value, p.right);
       }else{
            p.value = value;
       }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        if(value == null){
            remove(key);
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySethelper(root, new HashSet<>());
    }
    private Set<K> keySethelper(Node p,HashSet<K> keyset){
        if(p == null){
            return keyset;
        }
        keySethelper(p.left, keyset);
        keyset.add(p.key);
        keySethelper(p.right, keyset);
        return keyset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node removeHelper(K key, Node p){
        if(p == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        if(cmp < 0){
            p.left = removeHelper(key, p.left);
        } else if(cmp > 0){
            p.right = removeHelper(key, p.right);
        }else{
            // 0 or 1 child
            if(p.left == null){
                return p.right;
            }
            if(p.right == null){
                return p.left;
            }
            // 2 children, right's condition
            Node successor = successorHelper(key, p);
            p.key = successor.key;
            p.value = successor.value;
        }
        return p;
    }
    // 找右边里面最左边的tree
    private Node successorHelper(K key, Node p ){
       Node successor = p.left;
       while(successor != null){
           successor = successor.left;
       }
       return successor;
    }

    @Override
    public V remove(K key) {
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        V remoTovalue = get(key);
        root = removeHelper(key, root);
        return remoTovalue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        V removTovalue = get(key);
        if(removTovalue != value){
            return null;
        }
        root = removeHelper(key, root);
        return removTovalue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
    }

}
