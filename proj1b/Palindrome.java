public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> words = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            char character = word.charAt(i);
            words.addLast(word.charAt(i));

        }
        return words;
    }
    public boolean isPalindrome(String word){
        if(word == null || word.length() == 1 ){
            return true;
        }
        Deque<Character> words = wordToDeque(word);
        while(words.size() > 1){
            char word1 = words.removeFirst();
            char word2 = words.removeLast();
            if(word1 != word2){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word == null || word.length() == 1 ){
            return true;
        }
        int len = word.length();
        for(int i = 0; i < len / 2; i++){
            if(!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))){
                return false;
            }
        }
        return true;
    }


}
