public abstract class LibraryItem {
    private final String id;
    private final String title;

    public LibraryItem(String i, String t) {
        id = i;
        title = t;
    }

    public String getId() { return id;}
    public String getTitle() { return title;}
    public abstract String getType();

}