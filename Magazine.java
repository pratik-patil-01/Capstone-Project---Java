public class Magazine extends LibraryItem implements Borrowable {
    private final String issue;
    
    public Magazine(String i, String t, String iss) {
        super(i, t);
        issue = iss;
    }
    
    @Override
    public int getFinePerDay() {
        return 1;
    }

    @Override 
    public String getType() {
        return "MAGAZINE";
    }
}
