public class Main {
    public static void main(String args[]) {
        LibraryItem book = new Book("b1", "intro to algo", "cormen");
        LibraryItem mag = new Magazine("m1", "reso", "oct26");
        LibraryItem ref = new ReferenceBook("r1", "mb", "vyas");

        LibraryItem[] items = {book, mag, ref};
        for(LibraryItem i : items) {
            boolean bor = i instanceof Borrowable;
            int fpd = bor ? ((Borrowable) i).getFinePerDay() : 0;
            System.out.println(i.getId() + " " + i.getType() + ": borrowable = " + bor + ", finePerDay = " + fpd );
        }

        Member student = new Student("s1", "A");
        Member staff = new Staff("t1", "R");
        Member faculty = new Faculty("f1", "M");

        Member[] mem = {student, staff, faculty};
        for (Member m : mem) {
            System.out.println(m.getId() + " " + m.getName()
                    + ", maxItems = " + m.getMaxItems()
                    + ", loanPeriod = " + m.getLoanPeriod());
        }
    }
}
