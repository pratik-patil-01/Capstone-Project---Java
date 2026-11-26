public abstract class Member {
    private final String id;
    private final String name;

    public Member(String i, String n) {
        id = i;
        name = n;
    }

    public String getId() { return id;}
    public String getName() { return name;}
    
    public abstract int getMaxItems();
    public abstract int getLoanPeriod();
}
