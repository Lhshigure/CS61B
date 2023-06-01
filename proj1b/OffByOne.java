public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char c1, char c2){
        return Math.abs(c1 - c2) == 1;
    }
}
