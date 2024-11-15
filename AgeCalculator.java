public class AgeCalculator {

    /**
     * Main method to calculate age or date of birth.
     * @param args Command line arguments:
     *             - <DOB/AGE>: A string indicating either "DOB=dd-mm-yyyy" or "AGE=years-months-days".
     *             - <reference_date>: The reference date in the given format.
     *             - <date_format>: The date format (e.g., "DD-MM-YYYY", "MM-DD-YYYY", "YYYY-MM-DD").
     *             - <delimiter>: The delimiter used in the date strings (e.g., "-" or "/").
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java AgeCalculator <DOB/AGE> <reference_date> <date_format> <delimiter>");
            return;
        }

        String inputParam = args[0];  // DOB or AGE
        String referenceDateStr = args[1];  // Reference date
        String dateFormat = args[2];  // Date format (DD-MM-YYYY, MM-DD-YYYY, YYYY-MM-DD)
        String delimiter = args[3];  // Delimiter

        // Parse and validate the reference date
        int[] referenceDate = parseDate(referenceDateStr, dateFormat, delimiter);
        if (referenceDate == null) {
            System.out.println("Error: Invalid reference date.");
            return;
        }

        if (inputParam.startsWith("DOB")) {
            // Calculate age from DOB
            String dobStr = inputParam.split("=")[1];
            int[] dob = parseDate(dobStr, dateFormat, delimiter);
            if (dob == null) {
                System.out.println("Error: Invalid DOB.");
                return;
            }

            // Calculate age
            calculateAge(dob, referenceDate);
        } else if (inputParam.startsWith("AGE")) {
            // Calculate DOB from age
            String ageStr = inputParam.split("=")[1];
            String[] ageParts = ageStr.split(delimiter);

            if (ageParts.length != 3) {
                System.out.println("Error: Invalid age format.");
                return;
            }

            try {
                int years = Integer.parseInt(ageParts[0]);
                int months = Integer.parseInt(ageParts[1]);
                int days = Integer.parseInt(ageParts[2]);

                int[] dob = calculateDob(years, months, days, referenceDate);
                System.out.println("DOB is " + formatDate(dob));
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid age format.");
            }
        } else {
            System.out.println("Error: Invalid input. Please use 'DOB' or 'AGE'.");
        }
    }

    /**
     * Parses a date from a string based on the given date format and delimiter.
     * @param dateStr The date string to parse.
     * @param dateFormat The date format (e.g., "DD-MM-YYYY", "MM-DD-YYYY", "YYYY-MM-DD").
     * @param delimiter The delimiter used in the date string (e.g., "-" or "/").
     * @return An array of integers representing the day, month, and year, or null if invalid.
     */
    private static int[] parseDate(String dateStr, String dateFormat, String delimiter) {
        try {
            // Replace the delimiter with '-'
            String normalizedDate = dateStr.replace(delimiter, "-");

            String[] dateParts = null;
            int day, month, year;

            switch (dateFormat) {
                case "DD-MM-YYYY":
                    dateParts = normalizedDate.split("-");
                    if (dateParts.length != 3) return null;
                    day = Integer.parseInt(dateParts[0]);
                    month = Integer.parseInt(dateParts[1]);
                    year = Integer.parseInt(dateParts[2]);
                    break;

                case "MM-DD-YYYY":
                    dateParts = normalizedDate.split("-");
                    if (dateParts.length != 3) return null;
                    month = Integer.parseInt(dateParts[0]);
                    day = Integer.parseInt(dateParts[1]);
                    year = Integer.parseInt(dateParts[2]);
                    break;

                case "YYYY-MM-DD":
                    dateParts = normalizedDate.split("-");
                    if (dateParts.length != 3) return null;
                    year = Integer.parseInt(dateParts[0]);
                    month = Integer.parseInt(dateParts[1]);
                    day = Integer.parseInt(dateParts[2]);
                    break;

                default:
                    return null;
            }

            // Validate month and day ranges
            if (month < 1 || month > 12) return null;
            if (day < 1 || day > getDaysInMonth(month, year)) return null;

            return new int[] { day, month, year };
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats a date as a string in "DD-MM-YYYY" format.
     * @param date An array of integers representing the day, month, and year.
     * @return A formatted date string.
     */
    private static String formatDate(int[] date) {
        return String.format("%02d-%02d-%04d", date[0], date[1], date[2]);
    }

    /**
     * Calculates and prints the age given the date of birth and reference date.
     * @param dob An array of integers representing the day, month, and year of the date of birth.
     * @param referenceDate An array of integers representing the day, month, and year of the reference date.
     */
    private static void calculateAge(int[] dob, int[] referenceDate) {
        int years = referenceDate[2] - dob[2];
        int months = referenceDate[1] - dob[1];
        int days = referenceDate[0] - dob[0];

        // Adjust for negative months or days
        if (days < 0) {
            days += getDaysInMonth(referenceDate[1] - 1, referenceDate[2]);
            months--;
        }

        if (months < 0) {
            months += 12;
            years--;
        }

        System.out.println("Age is " + years + " years, " + months + " months, " + days + " days");
    }

    /**
     * Calculates the date of birth given the age and reference date.
     * @param years The number of years in the age.
     * @param months The number of months in the age.
     * @param days The number of days in the age.
     * @param referenceDate An array of integers representing the day, month, and year of the reference date.
     * @return An array of integers representing the day, month, and year of the date of birth.
     */
    private static int[] calculateDob(int years, int months, int days, int[] referenceDate) {
        int[] dob = new int[3];

        dob[2] = referenceDate[2] - years;
        dob[1] = referenceDate[1] - months;
        dob[0] = referenceDate[0] - days;

        // Adjust for negative months or days
        if (dob[0] < 1) {
            dob[0] += getDaysInMonth(referenceDate[1] - 1, referenceDate[2]);
            dob[1]--;
        }

        if (dob[1] < 1) {
            dob[1] += 12;
            dob[2]--;
        }

        return dob;
    }

    /**
     * Returns the number of days in a given month, accounting for leap years.
     * @param month The month (1-12).
     * @param year The year (used to check for leap years if the month is February).
     * @return The number of days in the month.
     */
    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeapYear(year)) ? 29 : 28;
            default:
                return 0;
        }
    }

    /**
     * Checks if a given year is a leap year.
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}
