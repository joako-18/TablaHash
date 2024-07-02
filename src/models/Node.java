package models;

import java.util.LinkedList;

public class Node {
    String key;
    LinkedList<String> values;

    public Node(String key, String value) {
        this.key = key;
        this.values = new LinkedList<>();
        this.values.add(value);
    }
}

