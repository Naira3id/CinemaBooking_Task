import java.util.*;

class Task_CinemaBooking {

    static class Seat {
        final char row;     
        final int number;   

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Seat seat = (Seat) o;
            return row == seat.row && number == seat.number;
        }

        
        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {

        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

       
        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

    
        System.out.println("Who booked C3:      " + bookings.get(new Seat('C', 3)));

        
        System.out.println("Is A2 taken:        " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken:        " + bookings.containsKey(new Seat('E', 6)));

        
        String existing = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (existing == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + existing);
        }

        bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), "Ali");
        System.out.println("After Ali moved:    " + bookings);

        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));
        Seat hanaSeat = new Seat('E', 1);
        if (broken.contains(hanaSeat)) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(hanaSeat, "Hana");
            System.out.println("Hana -> E1:         booked");
        }

    
        System.out.println("Seats booked:       " + bookings.size());

        System.out.println();
        printChart(bookings, broken);
    }

    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}
