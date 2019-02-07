package com.pavlyshyn;

import java.util.*;

public class PTreeMap<K extends Comparable<K>,V> implements Map<K,V> {
    Entry<K, V> root;
    int size = 0;
    @Override
    public V get(Object key) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        Entry<K, V> current = root;
        while (k.compareTo(current.key) != 0) {
            if (k.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
            if (current == null)
                return null;
        }
        return current.value;

    }

    @Override
    public V put(K key, V value) {
        // System.out.println(root);
        if (root == null) {
            size++;
            root = new Entry<>(key, value);
        } else {
            Entry<K, V> current = root;
            Entry<K, V> parent;
            while (true) {
                parent = current;
                if (key.compareTo(current.key) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = new Entry<>(key, value);
                        size++;
                        return null;
                    }
                } else if (key.compareTo(current.key) == 0) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                } else
                {
                    current = current.right;
                    if (current == null) {
                        parent.right = new Entry<>(key, value);
                        size++;
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public void print(){
        printInOrder(root);
        System.out.println();
    }
    private void printInOrder(Entry<K, V> localRoot) {
        if (localRoot != null) {
            printInOrder(localRoot.left);
            System.out.print(localRoot + " ");
            printInOrder(localRoot.right);
        }
    }

    @Override
    public V remove(Object key) {

        Comparable<? super K> k = (Comparable<? super K>) key;
        Entry<K, V> current = root;
        Entry<K, V> parent = root;

        boolean isLeftChild = true;
        while (!k.equals(current.key))
        {
            parent = current;
            if (k.compareTo(current.key) < 0)
            {
                isLeftChild = true;
                current = current.left;
            } else
            {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null)
                return null;
        }
        V oldValue = current.value;
        if (current.left == null && current.right == null) {
            if (current == root) {

                root = null;
            }
            else if (isLeftChild) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
        }
        else if (current.right == null) {
            if (current == root)
                root = current.left;
            else if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        }
        else if (current.left == null) {
            if (current == root)
                root = current.right;
            else if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        else{
            Entry<K, V> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            }
            else if (isLeftChild) {
                parent.left = successor;
            }
            else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        size--;
        return oldValue;
    }



    private Entry<K,V> getSuccessor(Entry<K,V> delNode)
    {
        Entry<K,V> successorParent = delNode;
        Entry<K,V> successor = delNode;
        Entry<K,V> current = delNode.right;
        while(current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if(!successor.equals(delNode.right))
        {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        PTreeMap.Entry<K,V> left;
        PTreeMap.Entry<K,V> right;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public K getKey() {
            return key;
        }


        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
         for(Map.Entry<? extends K,? extends V>  kvEntry: m.entrySet()){
             this.put(kvEntry.getKey(),kvEntry.getValue());
         }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    public static void main(String[] args) {
        PTreeMap<String, Integer> pTreeMap = new PTreeMap();
        pTreeMap.put("Sierra",22);
        pTreeMap.put("India",14);
        pTreeMap.put("Yankee",42);
        pTreeMap.put("Foxtrot",57);
        pTreeMap.put("Lima",89);
        pTreeMap.put("Tango",12);
        pTreeMap.put("Zulu",45);
        pTreeMap.put("Tbilisi",88);
        pTreeMap.print();
        System.out.println("get Tbilisi " + pTreeMap.get("Tbilisi"));
        System.out.println("remove Tango " + pTreeMap.remove("Tango"));
        pTreeMap.print();
    }
}

