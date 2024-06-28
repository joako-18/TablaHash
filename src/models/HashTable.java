package models;

import java.util.LinkedList;

public class HashTable {
    private LinkedList<String[]>[] table;
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
        table[index].add(new String[]{key, value});
    }

    public String get(String key, int hashFunction) {
        int index = Math.abs(hash(key, hashFunction) % size);
        for (String[] pair : table[index]) {
            if (pair[0].equals(key)) {
                return pair[1];
            }
        }
        return null;
    }

    public void displayAll() {
        for (int i = 0; i < size; i++) {
            if (!table[i].isEmpty()) {
                for (String[] pair : table[i]) {
                    System.out.println("ID=" + pair[0] + ", " + pair[1]);
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