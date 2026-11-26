import java.util.*;

public class Library {
    private final Map<String, LibraryItem> items;
    private final Map<String, Member> members;
    private final Map<String, Loan> loans;
    private final Map<String, Queue<String>> reservations;
    private final int today;

    public Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        loans = new HashMap<>();
        reservations = new HashMap<>();
        today = 0;
    }
}
