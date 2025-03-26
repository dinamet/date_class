
class Date {
    int day;
    int month;
    int year;

    Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    void Display() {
        System.out.println(this.day + " " + this.month + " " + this.year);
    }



}



public class Main {
    public static void main(String[] args) {
        Date d = new Date(27,03, 2025);

        System.out.println(d);


    }
}
