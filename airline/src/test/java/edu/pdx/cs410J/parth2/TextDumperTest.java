package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

//  @Test
//  void airlineNameIsDumpedInTextFormat() throws IOException {
//    String airlineName = "Test Airline";
//    Airline airline = new Airline();
//    airline.setName(airlineName);
//    StringWriter sw = new StringWriter();
//    TextDumper dumper = new TextDumper();
//    dumper.dump(airline);
//
//    String text = sw.toString();
//    assertThat(text, containsString(airlineName));
//  }
//
//  @Test
//  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
//    String airlineName = "Test Airline";
//    Airline airline = new Airline();
//    airline.setName(airlineName);
//
//    File textFile = new File(tempDir, "airline.txt");
//    TextDumper dumper = new TextDumper();
//    dumper.dump(airline);
//
//    TextParser parser = new TextParser();
//    Airline read = parser.parse();
//    assertThat(read.getName(), equalTo(airlineName));
//  }

//    @Test
//    public void xmldumper() throws IOException {
//        Airline airline = new Airline();
//        Flight flight1 = new Flight();
//        airline.setName("emirates");
//        flight1.setFlightnum("42");
//        flight1.setSrc("iah");
//        flight1.setDepart("10/23/2012", "12:23", "am");
//        flight1.setDest("iad");
//        flight1.setArrive("12/23/2014", "23:24", "pm");
//        airline.addFlight(flight1);
//
//        Flight flight2 = new Flight();
//        airline.setName("emirates");
//        flight2.setFlightnum("43");
//        flight2.setSrc("abe");
//        flight2.setDepart("10/23/2012", "12:23", "am");
//        flight2.setDest("iad");
//        flight2.setArrive("12/23/2014", "23:24", "pm");
//        airline.addFlight(flight2);
//
//        assertThat(airline.getFlights().size(), equalTo(2));
//        XmlDumper xmlDumper = new XmlDumper("testdumpxml.xml");
//        xmlDumper.dump(airline);
//
//        XmlParser parser = new XmlParser("testdumpxml.xml", airline.getName());
//        Airline airline2 = parser.parse();
//        assertThat(airline.toString(), equalTo(airline2.toString()));
//    }
}
