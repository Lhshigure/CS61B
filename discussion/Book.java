public class Book {
    public String title;
    public static Library library;
    public Book last = null;

    public Book(String name){
        title = name;
        last = this;
        library = null;
    }
    public String lastBookTile(){
        return last.title;
    }
    public String getTitle(){
        return title;
    }
}
