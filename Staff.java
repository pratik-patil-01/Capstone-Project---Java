public class Staff extends Member {
    
    public Staff(String i, String n) {
        super(i, n);
    }

    @Override 
    public int getMaxItems() { return 4;}

    @Override 
    public int getLoanPeriod() { return 21;}
}
