import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Library library = new Library();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (!line.isEmpty()) {
                execute(line);
            }
        }
        s.close();
    }

    private static void execute(String line) {
        String[] parts = line.split("\\|");
        String cmd = parts[0];

        try {
            switch (cmd) {
                case "ADD_BOOK":
                    library.addItem(new Book(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;
                case "ADD_MAGAZINE":
                    library.addItem(new Magazine(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;
                case "ADD_REFERENCE":
                    library.addItem(new ReferenceBook(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;
                case "ADD_MEMBER":
                    library.addMember(createMember(parts[1], parts[2], parts[3]));
                    System.out.println("OK");
                    break;
                case "BORROW":
                    System.out.println("OK due on day " + library.borrow(parts[1], parts[2]));
                    break;
                case "RETURN":
                    System.out.println(library.returnItem(parts[1]));
                    break;
                case "ADVANCE":
                    System.out.println("OK today is day " + library.advance(Integer.parseInt(parts[1])));
                    break;
                case "INVENTORY":
                    List<LibraryItem> allItems = library.inventory();
                    if (allItems.isEmpty()) {
                        System.out.println("NONE");
                    } else {
                        for (LibraryItem item : allItems) {
                            System.out.println(item.getId() + "|" + item.getType() + "|" + library.getStatus(item));
                        }
                    }
                    break;
            }
        } catch (LibraryException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    private static Member createMember(String id, String name, String type) {
        switch (type) {
            case "STUDENT": return new Student(id, name);
            case "STAFF": return new Staff(id, name);
            case "FACULTY": return new Faculty(id, name);
            default: throw new IllegalArgumentException("Unknown member type: " + type);
        }
    }
}