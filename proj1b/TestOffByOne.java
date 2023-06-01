import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    static CharacterComparator offByOne = new OffByOne();
    //Your tests go here.
    @Test
    public void testEqualChars(){
        OffByOne obo = new OffByOne();
        boolean actual1 = obo.equalChars('a', 'b');
        boolean actual2 = obo.equalChars('r', 'q');
        boolean expected = true;
        assertTrue(expected == actual1);
        assertTrue(expected == actual2);

        boolean actual3 = obo.equalChars('a', 'e');
        assertFalse(expected == actual3);

    }


    /*Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/


}
