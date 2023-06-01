import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }// Uncomment this class once you've created your Palindrome class.
    @Test
    public void testisPalindrome(){
        boolean actual = palindrome.isPalindrome("aabbaa");
        boolean expected = true;
        assertTrue(expected == actual);

        boolean actual2 = palindrome.isPalindrome("aabbcc");
        assertFalse(expected == actual2);
    }

    @Test
    public void testisPalindromeOffByOne(){
        CharacterComparator cc = new OffByOne();
        boolean actual = palindrome.isPalindrome("flake", cc);
        boolean actual2 = palindrome.isPalindrome("abcd", cc);
        assertFalse(actual2 == true);
        assertTrue(actual == true );
    }
}
