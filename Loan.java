public class Loan {
    private final Member member;
    private final LibraryItem item;
    private final int dueDay;

    public Loan(Member m, LibraryItem l, int d) {
        member = m;
        item = l;
        dueDay = d;
    }

    public Member getMember() { return member;}
    public LibraryItem getItem() { return item;}
    public int getDueDay() { return dueDay;}

}
