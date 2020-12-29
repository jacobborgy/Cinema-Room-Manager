import java.util.*;

public class Cinema {

    public static char[][] getSeating() {
        Scanner scanner = new Scanner(System.in);

        // Gets # of rows
        System.out.print("Enter the number of rows:\n> ");
        int size1 = scanner.nextInt();

        // Gets # of seats per row
        System.out.print("Enter the number of seats in each row:\n> ");
        int size2 = scanner.nextInt();
        System.out.println();

        // Creates a 2d char array
        char[][] seating = new char[size1][size2];

        // Fills 2d array with 'S'
        for (char[] row: seating) {
            Arrays.fill(row, 'S');
        }

        // Returns a 2d char array
        return seating;
    }

    public static void menu(char[][] arr) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
        int num = scanner.nextInt();
        System.out.println();
        boolean check = false;

        do {
            if (check) {
                System.out.print("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
                num = scanner.nextInt();
                System.out.println();
            }

            switch (num) {
                case 1:
                    display(arr);
                    check = true;
                    break;
                case 2:
                    calculate(arr);
                    check = true;
                    break;
                case 3:
                    statistics(arr);
                    check = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Not an option");
                    check = true;
            }
        } while (true);
    }

    public static void display(char[][] seating) {
        System.out.println("Cinema:");
        System.out.print(" ");

        for (int i = 1; i < seating[0].length+1; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        int rowCt = 1;
        for (char[] chars : seating) {
            System.out.print(rowCt);
            for (int j = 0; j < seating[0].length; j++) {
                System.out.print(" " + chars[j]);
            }
            rowCt++;
            System.out.println();
        }
        System.out.println();
    }

    public static void calculate(char[][] seating) {
        Scanner scanner = new Scanner(System.in);
        int rows;
        int seats;


        System.out.print("Enter a row number:\n> ");
        rows = scanner.nextInt();
        System.out.print("Enter a seat number in that row:\n> ");
        seats = scanner.nextInt();



        int size1 = seating.length;
        int size2 = seating[0].length;

        if (rows < 0 || rows > size1 || seats < 0 || seats > size2) {
            System.out.println("\nWrong input!\n");
            calculate(seating);
            return;
        }

        int numOfSeats = size1 * size2;

        if (seating[rows-1][seats-1] == 'B') {
            System.out.println("\nThat ticket has already been purchased!\n");
            calculate(seating);
            return;
        }

        if (numOfSeats <= 60) {

            seating[rows-1][seats-1] = 'B';
            System.out.println("\nTicket price: $" + 10);
            System.out.println();


        } else {
            int frontHalf = size1/2;

            if (rows <= frontHalf) {
                seating[rows-1][seats-1] = 'B';
                System.out.println("\nTicket price: $" + 10);
                System.out.println();
            } else {
                seating[rows-1][seats-1] = 'B';
                System.out.println("\nTicket price: $" + 8);
                System.out.println();
            }

        }
    }

    public static void statistics(char[][] arr) {

        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        double rows = arr.length;
        double columns = arr[0].length;
        int frontHalf = arr.length/2;
        int backHalf;
        if (arr.length % 2 == 0) {
            backHalf = frontHalf;
        } else {
            backHalf = frontHalf + 1;
        }



        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                //System.out.println("J: " + j);
                if (arr[i][j] == 'B') {
                    purchasedTickets++;
                    if ((rows * columns) >= 60 && i+1 >= backHalf) {
                        currentIncome += 8;
                    } else {
                        currentIncome += 10;
                    }

                }
                if ((rows * columns) >= 60 && i+1 >= backHalf) {
                    totalIncome += 8;
                } else {
                    totalIncome += 10;
                }



            }
        }

        double percentage = (purchasedTickets * 100) / (rows * columns);

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] seating = getSeating();
        menu(seating);
    }

}
