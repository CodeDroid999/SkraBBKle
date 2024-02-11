package pij.main;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public void addChild(char c) {
        children.put(c, new TrieNode());
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public boolean containsChild(char c) {
        return children.containsKey(c);
    }
}
