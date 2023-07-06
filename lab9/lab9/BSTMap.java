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
       if(key == null){
           throw new IllegalArgumentException("calls get() with a null key ");
       }
        if(p == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        if(cmp < 0){
            return getHelper(key, p.left);
        }else if(cmp > 0){
            return getHelper(key, p.right);
        }else{
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
       if(p == null){
           return new Node(key, value);
       }
       int cmp = key.compareTo(p.key);
       if(cmp > 0){
           p.right = putHelper(key, value, p.right);
       }else if(cmp < 0){
           p.left = putHelper(key, value, p.left);
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
            throw new IllegalArgumentException("calls put() with null key");
        }
        if(!containsKey(key)){
            root = putHelper(key, value, root);
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }
    private int size(Node p){
        if(p == null){
            return 0;
        }else {
            return size;
        }
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet(root);
    }
    private Set<K> keySet(Node p){
        Set<K> keyset = new HashSet<>();
        if(p == null){
            return keyset;
        }
        keySet(p.right);
        keySet(p.left);
        keyset.add(p.key);
        return keyset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if(key == null){
            throw new IllegalArgumentException("calls remove() with null key");
        }
        remove(key, root);
        size --;
        return get(key);
    }
    private Node remove(K key, Node p){
        if(p == null){
            throw new IllegalArgumentException("The key do not exsit in the tree");
        }
        int cmp = key.compareTo(p.key);
        if(cmp < 0){
            p.right = remove(key, p.right);
        }else if(cmp > 0){
            p.left = remove(key, p.left);
        }else{
            // the node has 1 or 0 child
            if(p.right == null){
                return p.left;
            }
            if(p.left == null){
                return p.right;
            }
            p = successor(p);
        }
        return p;
    }
    // has 2 children
    private Node successor(Node p){
        Node successor = p.right;
        Node successorChild = successor.left;
        if(successorChild == null){
            return successor;
        }
        while(successorChild.left != null){
            successor = successor.left;
            successorChild = successorChild.left;
        }
        successor.left = successorChild.right;
        successorChild.right = p.right;
        successorChild.left = p.left;
        return successorChild;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException("calls remove() with null key");
        }
        if(value == get(key)){
            remove(key);
            return get(key);
       }else{
           return null;
       }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}