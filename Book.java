public class Book extends LibraryItem implements Borrowable{
    private final String author;

    public Book(String i, String t, String a) {
        super(i, t);
        author = a;
    }

    @Override
    public int getFinePerDay() {
        return 2;
    }

    @Override
    public String getType() {
        return "BOOK";
    }
}
