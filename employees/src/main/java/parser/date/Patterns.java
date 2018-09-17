package parser.date;

public enum Patterns {
  PATTERN_1("dd-MM-yyyy"),
  PATTERN_2("MM-dd-yyyy"),
  PATTERN_3("yyyy-MM-dd"),
  PATTERN_4("yyyy-dd-MM"),
  PATTERN_5("yyyy/MM/dd"),
  PATTERN_6("yyyy/dd/MM"),
  PATTERN_7("dd/MM/yyyy"),
  PATTERN_8("MM/dd/yyyy"),
  PATTERN_9("dd.MM.yyyy"),
  PATTERN_10("MM.dd.yyyy"),
  PATTERN_11("yyyy.MM.dd"),
  PATTERN_12("yyyy.dd.MM");


  private String pattern;

  Patterns(String s) {
    this.pattern = s;
  }

  String getPattern() {
    return this.pattern;
  }
}
