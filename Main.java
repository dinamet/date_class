
class Date {
    int day;
    int month;
    int year;

    // constructor with parameters
    Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // constructor without parameters
    Date() {

    }

    // method for checking leap year
    private boolean isLeapYear ( int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // method for checking validation of date
    private boolean isValidDate(int day, int month, int year) {
        if (year < 1) return false;
        if (month < 1 || month > 12) return false;

        // quantity of days in each month
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }

        return day >= 1 && day <= daysInMonth[month];
    }

    // method for updating date
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

    // Determining day of week using Zeller's formula
    public String getDayOfWeek() {
        int d = day;
        int m = month;
        int y = year;

        // January and February are considered the 13th and 14th months of previous year.
        if (m == 1 || m == 2) {
            m += 12;
            y -= 1;
        }

        int K = y % 100;  // the last two digits of year
        int J = y / 100;  // the first two digits of year

        // Zeller's formula
        int h = (d + (13 * (m + 1)) / 5 + K + (K / 4) + (J / 4) + (5 * J)) % 7;

        // Days of the week (0 = Saturday, 1 = Sunday, 2 = Monday, 3 = Tuesday, 4 = Wednesday, 5 = Thursday, 6 = Friday)
        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        return days[h];
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



    void Display() {
        System.out.println(this.day + " " + this.month + " " + this.year);
    }



}

public class Main {
    public static void main(String[] args) {
        Date d1 = new Date(27,03, 2025);

        System.out.println(d1);

    }
}
