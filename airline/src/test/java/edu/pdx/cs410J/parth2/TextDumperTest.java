package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

  @Test
  void airlineNameIsDumpedInTextFormat() throws IOException {
    String airlineName = "Test Airline";
    Airline airline = new Airline();
    airline.setName(airlineName);
    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper();
    dumper.dump(airline);

    String text = sw.toString();
    assertThat(text, containsString(airlineName));
  }

  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    String airlineName = "Test Airline";
    Airline airline = new Airline();
    airline.setName(airlineName);

    File textFile = new File(tempDir, "airline.txt");
    TextDumper dumper = new TextDumper();
    dumper.dump(airline);

    TextParser parser = new TextParser();
    Airline read = parser.parse();
    assertThat(read.getName(), equalTo(airlineName));
  }
}
