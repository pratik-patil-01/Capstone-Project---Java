public class Faculty extends Member {
    
    public Faculty(String i, String n) {
        super(i, n);
    }

    @Override 
    public int getMaxItems() { return 5;}

    @Override 
    public int getLoanPeriod() { return 30;}
}
