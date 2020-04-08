package interview.template;

class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return curr.isWord;
    }

    /** Search with wildcard "." */
    public boolean searchWithWildcard(String word) {
        return searchWithWildcard(word, 0, root);
    }

    private boolean searchWithWildcard(String word, int idx, TrieNode root) {
        if (idx == word.length()) return root.isWord;
        char ch = word.charAt(idx);
        if (ch == '.') {
            for (TrieNode node : root.children) {
                if (node != null && searchWithWildcard(word, idx + 1, node)) return true;
            }
            return false;
        } else {
            TrieNode next = root.children[ch - 'a'];
            if (next == null) return false;
            return searchWithWildcard(word, idx + 1, next);
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abcd");
        System.out.println(trie.search("abcde"));
        System.out.println(trie.search("abcd"));
        System.out.println(trie.startsWith("abcde"));
        System.out.println(trie.startsWith("abc"));
        System.out.println(trie.searchWithWildcard(".b.d"));
        System.out.println(trie.searchWithWildcard("..bd"));
    }
}

class TrieNode {
    boolean isWord = false;
    TrieNode[] children = new TrieNode[26];
}
