package parser.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateParser {

  private static final String DATE_PARSE_EXCEPTION_MESSAGE = "Can not parse date. Not supported format.";

  private DateParser() {}

  public static LocalDate parseDate(String dateToParse) {
    if (dateToParse.equals("NULL")) {
      return LocalDate.now();
    }
    return parseDateRecursive(dateToParse, 0);
  }

  private static LocalDate parseDateRecursive(String dateToParse, int count) {
    DateTimeFormatter formatter = null;
    try {
      formatter = DateTimeFormatter.ofPattern(Patterns.values()[count].getPattern());
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new UnsupportedOperationException(DATE_PARSE_EXCEPTION_MESSAGE);
    }

    LocalDate date = null;

    try {
      date = LocalDate.parse(dateToParse, formatter);
      return date;
    } catch (DateTimeParseException e) {
      return parseDateRecursive(dateToParse, ++count);
    }
  }
}
