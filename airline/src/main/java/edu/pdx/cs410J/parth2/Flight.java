package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractFlight;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight {
  @Override
  public int getNumber() {
    return 42;
  }

  @Override
  public String getSource() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
  Map names = AirportNames.getNamesMap();
  int flightnum;
  String src, dst;
  Date dprt, arrv;

  public void setFlightnum(String number) {
    int num = 0;
    try {
      num = Integer.parseInt(number);
    }
    catch(NumberFormatException e) {
      System.err.println("Please enter a numeric flightnumber!");
      System.exit(1);
    }
    String numeric = "[0-9]+";
    Pattern pattern = Pattern.compile(numeric);
    Matcher matcher = pattern.matcher(Integer.toString(num));
    if (matcher.matches()) {
      this.flightnum = num;
    }
    else {
      System.out.println("Please check the flightnumber!");
      System.exit(1);
    }
  }
  /**
   * This method sets the source of the flight
   * @param source the three letter code of the source of flight
   */
  public void setSrc(String source) {
    String sourceuppercase = source.toUpperCase();
    if(!names.containsKey(sourceuppercase)){
      System.err.println("The three-letter code for the source is invalid");
      System.exit(1);
    }
    this.src = source;
  }

  /**
   * This method sets the departure date and time of a flight from the source
   * @param date The date when the flight departs
   * @param time The date when the flight arrives
   * @param ampm The time of the day (am or pm)
   */
  public void setDepart(String date, String time, String ampm) {
    String finaldatetime = date + " " + time + " " + ampm;
    try{
      this.dprt = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      System.err.println("Please verify the format for departing date and time");
      System.exit(1);
    }
  }

  /**
   * This method sets the destination of the flight
   * @param dest The three letter code of the destination of the flight
   */
  public void setDest(String dest) {
    String destuppercase = dest.toUpperCase();
    if(!names.containsKey(destuppercase)){
      System.err.println("The three-letter code for the source is invalid");
      System.exit(1);
    }
    String codex = "[a-zA-Z]{3}";
    Pattern pattern = Pattern.compile(codex);
    Matcher matcher = pattern.matcher(dest);
    if (matcher.matches()) {
      this.dst = dest;
    }
    else {
      System.err.println("Please check the code for the destination!");
      System.exit(1);
    }
  }

  /**
   * This method sets the arrival date and time of a flight at the destination
   * @param date The date when the flight arrives
   * @param time The time when the flight arrives
   * @param ampm The time of the day (am or pm)
   */
  public void setArrive(String date, String time, String ampm) {
    String finaldatetime = date + " " + time + " " + ampm;
    try{
      this.arrv = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      System.err.println("Please verify the format for arriving date and time");
      System.exit(1);
    }
  }
 //this code
}


