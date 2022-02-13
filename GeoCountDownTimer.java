import java.io.*;
import java.util.Scanner;

/**********************************************************************
 *@author Gavin Eschtruth
 *Posada CS 163 01
 *
 **********************************************************************/

public class GeoCountDownTimer {

    //month of the date
    private int month;
    // year of the date
    private int year;

    //day of the date
    private int day;

    //minimum year that a date can have
    private static final int MIN_YEAR = 2022;

    //days in a month in a regular year
    private static final int[] REG_YEAR =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //days in a month in a leap year
    private static final int[] LEAP_YEAR =
            {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //names of months in a year
    private static final String[] MONTHS = {"", "January", "February", "March"
            , "April", "May", "June", "July", "August", "September", "October"
            , "November", "December"};

    /**
     * constructor for if no input
     */
    public GeoCountDownTimer() {
        this(MIN_YEAR, 1, 1);
    }

    /**
     * Constructor for separated date
     *
     * @param year  year of date
     * @param month month of date
     * @param day   day of date
     * @throws IllegalArgumentException if date is not valid
     */
    public GeoCountDownTimer(int year, int month, int day) {
        if (isDateValid(year, month, day)) throw new IllegalArgumentException();
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Constructor for date in mm/dd/yyyy format and
     * Sets parameter date to global variables
     *
     * @param geoDate date
     */
    public GeoCountDownTimer(String geoDate) {
        String[] date = geoDate.split("/", 3);
        this.month = Integer.parseInt(date[0]);
        this.day = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        if (isDateValid(this.year, this.month, this.day)) throw new IllegalArgumentException();
    }

    /**
     * check if two dates are equal
     *
     * @param other Object
     * @return if the dates match each other
     */
    public boolean equals(Object other) {
        GeoCountDownTimer date = (GeoCountDownTimer) other;
        return this.year == date.year && this.month == date.month &&
                this.day == date.day;
    }

    /**
     * compares two dates to see greater than/less-than/equal too
     *
     * @param other other
     * @return if other is greater than/less-than/ or equal to THIS
     */
    public int compareTo(GeoCountDownTimer other) {
        if (this.year == other.year && this.month == other.month &&
                this.day == other.day) {
            return 0;
        } else if (this.year >= other.year && this.month >= other.month &&
                this.day >= other.day) {

            return 1;
        } else if (this.year > other.year)
            return 1;
        else if (this.year == other.year && this.month > other.month)
            return 1;
        else return -1;
    }

    /**
     * checks if the inputted date is a valid date
     *
     * @param year  year of date
     * @param month month of date
     * @param day   day of date
     * @return boolean if the date is valid
     */
    public boolean isDateValid(int year, int month, int day) {
        if (leapYear(year)) {
            return year < 2022 || month < 1 || month > 12 ||
                    day < 1 || day > LEAP_YEAR[month];
        } else {
            return year < 2022 || month < 1 || month > 12 ||
                    day < 1 || day > REG_YEAR[month];
        }
    }

    /**
     * Determines if year is a leap year
     *
     * @param year year of date
     * @return boolean of if leap year or not
     */
    private boolean leapYear(int year) {
        if ((year % 100 == 0) && (year % 400 == 0)) {
            return true;
        } else return year % 4 == 0;
    }

    /**
     * Decreases date by one day
     */
    public void dec() {
        if (this.day == 1) {
            if (this.month == 1) {
                this.year--;
                this.month = 12;
                this.day = 31;
            } else if (leapYear(this.year) && this.month == 3) {
                this.day = 29;
                this.month = 2;
            } else {
                this.month--;
                this.day = REG_YEAR[this.month];
            }
        } else {
            this.day--;
        }
    }

    /**
     * Decreases date by certain number of days
     *
     * @param days number of days to decrease by
     */
    public void dec(int days) {
        for (int i = 0; i < days; i++) {
            dec();
        }
    }

    /**
     * Increases date by one day
     */
    public void inc() {
        if (this.month == 2 && leapYear(this.year)) {
            if (this.day == 29) {
                this.month++;
                this.day = 1;
            } else this.day++;
        } else {
            if (this.day == REG_YEAR[this.month]) {
                if (this.month == 12) {
                    this.month = 1;
                    this.year++;
                } else {
                    this.month++;
                }
                this.day = 1;
            } else this.day++;
        }
    }

    /**
     * Increases date by certain number of days
     *
     * @param days number of days to increase by
     */
    public void inc(int days) {
        for (int i = 0; i < days; i++) {
            inc();
        }
    }

    /**
     * formats date
     *
     * @return string in format of (MonthName Day, Year) of date
     */
    public String toString() {
        return MONTHS[this.month] + " " + this.day + ", " + this.year;
    }

    /**
     * formats date
     *
     * @return string in format of (MonthName Day, Year) of date
     */
    public String toDateString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * How many days to go from a certain date
     *
     * @param fromDate future date to count to
     * @return number of days to get to given date
     */
    public int daysToGo(String fromDate) {
        String[] date = fromDate.split("/", 3);
        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        int days = Math.abs(day - this.day);


        if (this.year == year) {
            for (int i = month; i < this.month; i++) {
                days += REG_YEAR[i];
            }
            if (leapYear(this.year)) {
                days++;
            }
        } else {
            for (int x = month; x <= 12; x++) {
                days += REG_YEAR[x];
            }
            for (int x = year; x < this.year; x++) {
                if (leapYear(x)) {
                    days += 366;
                } else {
                    days += 365;
                }
            }
            for (int h = 1; h < this.month; h++) {
                days += REG_YEAR[h];
            }
        }
        return days;
    }

    /**
     * takes number of days and gives you the future date
     *
     * @param daysFuture number of days
     * @return new GeoCountDownTimer for future date
     */
    public GeoCountDownTimer daysInFuture(int daysFuture) {
        GeoCountDownTimer other = new GeoCountDownTimer();

        other.month = this.month;
        other.day = this.day;
        other.year = this.year;

        for (int i = 0; i < daysFuture; i++) {
            other.day++;
            if (!leapYear(other.year)) {
                if (other.day > REG_YEAR[other.month]) {
                    if (other.month != 12) {
                        other.month++;
                        other.day = 0;
                    } else {
                        other.month = 0;
                        other.year++;
                    }
                }
            } else {
                if (other.day > LEAP_YEAR[other.month]) {
                    if (other.month != 12) {
                        other.month++;
                        other.day = 0;
                    } else {
                        other.month = 0;
                        other.year++;
                    }
                }
            }
        }
        return other;
    }

    /**
     * saves the current file, dates, and information
     *
     * @param fileName name of file
     */
    public void save(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println(this.month + " " + this.day + " " + this.year);
        out.close();
    }

    /**
     * loads information from saved files
     *
     * @param fileName name of file
     */
    public void load(String fileName) {

        try {
            Scanner fileReader = new Scanner(new File(fileName));

            this.month = fileReader.nextInt();
            this.day = fileReader.nextInt();
            this.year = fileReader.nextInt();
            System.out.println(toDateString());
        } catch (Exception error) {
            System.out.println("File not found ");
        }
    }
}