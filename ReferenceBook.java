public class ReferenceBook extends LibraryItem {
    private final String author;

    public ReferenceBook(String i, String t, String a) {
        super (i, t);
        author = a;
    }

    @Override 
    public String getType() {
        return "REFERENCE";
    }
}
