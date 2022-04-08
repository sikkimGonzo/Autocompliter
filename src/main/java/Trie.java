import java.util.ArrayList;
import java.util.List;

public class Trie {
    TrieNode root;

    public Trie(List<String[]> words){
        root = new TrieNode();
        for(String[] line : words){
            for(String word : line){
                root.insert(word);
            }
        }
    }

    public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr){
        if(root.isWord){
            list.add(curr.toString());
        }

        if(root.children == null || root.children.isEmpty()){
            return;
        }

        for(TrieNode child : root.children.values()){
            suggestHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }

    public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<String>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        suggestHelper(lastNode, list, curr);
        return list;
    }
}
