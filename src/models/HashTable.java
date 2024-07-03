package models;

import java.util.LinkedList;

public class HashTable {
    private LinkedList<Node>[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void insert(String key, String value, int hashFunction) {
        int index = Math.abs(hash(key, hashFunction) % size);
        System.out.println(index);
        for (Node node : table[index]) {
            if (node.key.equals(key)) {
                node.values.add(value);
                return;
            }
        }
        table[index].add(new Node(key, value));
    }

    public String get(String key, int hashFunction) {
        int hashValue = hash(key, hashFunction);
        int index = Math.abs(hashValue % size);
        System.out.println(index);
        System.out.println("Clave hash utilizada: " + hashValue);
        for (Node node : table[index]) {
            if (node.key.equals(key)) {
                return node.values.toString();
            }
        }
        return null;
    }

    public void displayAll() {
        for (int i = 0; i < size; i++) {
            if (!table[i].isEmpty()) {
                for (Node node : table[i]) {
                    System.out.println("ID=" + node.key + ", Values=" + node.values);
                }
            }
        }
    }

    private int hash(String key, int hashFunction) {
        switch (hashFunction) {
            case 1:
                return hashFunction1(key);
            case 2:
                return hashFunction2(key);
            default:
                throw new IllegalArgumentException("Invalid hash function number");
        }
    }

    private int hashFunction1(String key) {
        int hash = 7;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * 31 + key.charAt(i);
        }
        return hash;
    }

    private int hashFunction2(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash << 5) - hash + key.charAt(i);
        }
        return hash;
    }
}