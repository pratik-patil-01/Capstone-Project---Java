public class Student extends Member {

    public Student(String i, String n) {
        super(i, n);
    }

    @Override 
    public int getMaxItems() { return 3;}

    @Override 
    public int getLoanPeriod() { return 14;}
}
