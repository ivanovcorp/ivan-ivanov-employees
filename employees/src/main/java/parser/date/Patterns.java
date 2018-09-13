package parser.date;

public enum Patterns {
  PATTERN_1("dd-MM-yyyy"),
  PATTERN_2("MM-dd-yyyy"),
  PATTERN_3("yyyy-dd-MM"),
  PATTERN_4("yyyy-MM-dd"),
  PATTERN_5("yyyy/dd/MM"),
  PATTERN_6("yyyy/MM/dd"),
  PATTERN_7("dd/MM/yyyy"),
  PATTERN_8("MM/dd/yyyy"),
  PATTERN_9("dd.MM.yyyy"),
  PATTERN_10("MM.dd.yyyy"),
  PATTERN_11("yyyy.dd.MM"),
  PATTERN_12("yyyy.MM.dd");


  private String pattern;

  Patterns(String s) {
    this.pattern = s;
  }

  String getPattern() {
    return this.pattern;
  }
}
