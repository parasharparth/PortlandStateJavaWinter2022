package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {

//  @Test
//  void validTextFileCanBeParsed() throws ParserException {
//    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
//    assertThat(resource, notNullValue());
//
//    TextParser parser = new TextParser();
//    Airline airline = parser.parse();
//    assertThat(airline.getName(), equalTo("Test Airline"));
//  }
//
//  @Test
//  void invalidTextFileThrowsParserException() {
//    InputStream resource = getClass().getResourceAsStream("empty-airline.txt");
//    assertThat(resource, notNullValue());
//
//    TextParser parser = new TextParser();
//    assertThrows(ParserException.class, parser::parse);
//  }

    @Test
    public void xmldumper() throws IOException {
        Airline airline = new Airline();
        Flight flight1 = new Flight();
        airline.setName("emirates");
        flight1.setFlightnum("42");
        flight1.setSrc("iah");
        flight1.setDepart("10/23/2012", "12:23", "am");
        flight1.setDest("iad");
        flight1.setArrive("12/23/2014", "23:24", "pm");
        airline.addFlight(flight1);

        Flight flight2 = new Flight();
        airline.setName("emirates");
        flight2.setFlightnum("43");
        flight2.setSrc("abe");
        flight2.setDepart("10/23/2012", "12:23", "am");
        flight2.setDest("iad");
        flight2.setArrive("12/23/2014", "23:24", "pm");
        airline.addFlight(flight2);

        assertThat(airline.getFlights().size(), equalTo(2));
        XmlDumper xmlDumper = new XmlDumper("testdumpxml.xml");
        xmlDumper.dump(airline);

        XmlParser parser = new XmlParser("testdumpxml.xml", airline.getName());
        Airline airline2 = parser.parse();
        assertThat(airline.toString(), equalTo(airline2.toString()));
    }

}
