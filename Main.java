import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;

class Date implements Comparable<Date> {
    int day;
    int month;
    int year;

    // Constructor with parameters
    Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Constructor without parameters
    Date() {

    }

    // Method for checking leap year
    private boolean isLeapYear ( int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Method for checking validation of date
    public boolean isValidDate(int day, int month, int year) {
        if (year < 1) return false;
        if (month < 1 || month > 12) return false;

        // Quantity of days in each month
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }

        return day >= 1 && day <= daysInMonth[month];
    }

    // Method for updating date
    public void updateDate(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
            System.out.println("Updated date" + this);
        }
        else {
            System.out.println("Error: Invalid date");
        }
    }

    // Method for determining the day of the week
    public String getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // In Calendar, months start at 0 (January = 0)

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Get day of week (1 = Sunday, 2 = Monday, etc.)

        return days[dayOfWeek - 1];
    }

    // Method for calculating the number of days
    private int toDays() {
        int totalDays = 0;

        // Adding days for full years
        for (int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }

        // Number of days in each month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // If the year is a leap year, then February = 29 days
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        // Adding days for full months
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        // Adding days for the current month
        totalDays += day;

        return totalDays;
    }

    // Method to calculate the difference between two dates
    public int calculateDifference(Date otherDate) {
        return Math.abs(this.toDays() - otherDate.toDays());
    }

    // Method for displaying date
    public void printDate() {
        String[] monthNames = {
                "", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        System.out.println(monthNames[month] + " " + day + ", " + year);
    }


    // Method to convert object to string
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    // Method for comparing dates (sorting)
    @Override
    public int compareTo(Date other) {
        // Comparing years
        if (this.year != other.year) {
            return this.year - other.year;
        }
        // if years are same, comparing months
        if (this.month != other.month) {
            return this.month - other.month;
        }
        // If months are same, comparing days
        return this.day - other.day;
    }

}


public class Main {
    public static void main(String[] args) {
        // 1.Creating valid and invalid Date objects
        Date d1 = new Date(27, 3, 2025);
        Date d2 = new Date(22, 7, 2024);
        Date d3 = new Date(7, 9, 2023);

        Date d4 = new Date(29, 2, 2025);
        Date d5 = new Date(0, 4, 2025);
        Date d6 = new Date(17, 13, 2024);

        // 2.Checking correct and incorrect dates
        System.out.println("Valid Dates:");
        d1.printDate();
        d2.printDate();
        d3.printDate();

        System.out.println();

        System.out.println("Invalid Dates (Should print error messages):");
        // Checking for incorrect dates
        if (!d4.isValidDate(d4.day, d4.month, d4.year)) {
            System.out.println("Invalid date: " + d4);
        }
        if (!d5.isValidDate(d5.day, d5.month, d5.year)) {
            System.out.println("Invalid date: " + d5);
        }
        if (!d6.isValidDate(d6.day, d6.month, d6.year)) {
            System.out.println("Invalid date: " + d6);
        }

        System.out.println();

        // 3.Date update (attempt to update incorrect date)
        System.out.println("Updating Dates: ");
        d4.updateDate(28, 2, 2025);
        d5.updateDate(1, 4, 2025);
        d6.updateDate(17, 12, 2024);

        System.out.println();

        // Displaying updated dates
        System.out.println("Updated Dates: ");
        d4.printDate();
        d5.printDate();
        d6.printDate();

        System.out.println();

        // 4.Print day of the week
        System.out.println("Day of the Week for d1:");
        System.out.println(d1.getDayOfWeek());

        System.out.println();

        // 5.Difference between dates
        Date d7 = new Date(1, 5, 2024); // Create another date to calculate the difference
        System.out.println("Difference between d1 and d7 in days:");
        System.out.println(d1.calculateDifference(d7));

        // 6.Sorting the Date list
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(d1);
        dates.add(d2);
        dates.add(d3);
        dates.add(d4);
        dates.add(d5);
        dates.add(d6);

        // Sorting the list of dates
        Collections.sort(dates);

        System.out.println();

        // 7.Printing sorted dates
        System.out.println("Sorted Dates:");
        for (Date date : dates) {
            date.printDate(); // Sorted dates by year, month and day
        }
    }
}
