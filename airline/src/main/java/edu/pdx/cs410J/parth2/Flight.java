package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight implements Comparable<Flight>{

  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
  Map names = AirportNames.getNamesMap();
  int flightnum;
  String src, dst;
  Date dprt, arrv;

  @Override
  public int getNumber() {

    return flightnum;
  }

  @Override
  public String getSource() {

    return this.src;
  }

  @Override
  public String getDepartureString() {

    SimpleDateFormat dater = new SimpleDateFormat("MM/dd/yyyy");
    String datestring = dater.format(this.dprt);
    String timestring = formatter.getTimeInstance(DateFormat.SHORT).format(this.dprt);
    return datestring + " " + timestring;
  }

  @Override
  public String getDestination() {
   return dst;
  }

  @Override
  public String getArrivalString() {

    SimpleDateFormat dater = new SimpleDateFormat("MM/dd/yyyy");
    String datestring = dater.format(this.arrv);
    String timestring = formatter.getTimeInstance(DateFormat.SHORT).format(this.arrv);
    return datestring + " " + timestring;
  }

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
      System.out.println("Please check the flight number!");
      System.exit(1);
    }
  }

  public void setSrc(String source) {

    String sourceuppercase = source.toUpperCase();
    if(!names.containsKey(sourceuppercase)){
      System.err.println("The three-letter code for the source is invalid");
      System.exit(1);
    }
    this.src = source;
  }

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

  /**
   * This method ensures that the departure time is before arrival time
   */
  public boolean checkdeparturebeforearrival(){
    if(this.dprt.compareTo(this.arrv) > 0){
      return false;
    }
    else{
      return true;
    }
  }

  /**
   * This method return the arrival date and time of a flight
   * @return the arrival date in the form of a Date object
   */
  @Override
  public Date getArrival() {
    return this.arrv;
  }

  /**
   * This method return the departure date and time of a flight
   * @return the departure date in the form of a Date object
   */
  @Override
  public Date getDeparture() {
    return this.dprt;
  }

  /**
   * This method compares the current object to the specified object.
   * This method is used mainly to sort the flights by their source. If the source is same, it sorts by departure time.
   * @param o the Flight object to compare the current object to
   * @return it returns a positive integer if current object is greater than specified object,
  it returns a negative integer if current object is lesser than specified object,
  it returns 0 integer if current object is equal to specified object
   */
  public int compareTo(Flight o) {
    if(this.getSource().compareToIgnoreCase(o.getSource()) == 0){
      try {
        Date d1 = formatter.parse(this.getDepartureString());
        Date d2 = formatter.parse(o.getDepartureString());
        return d1.compareTo(d2);
      } catch (ParseException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
    return this.getSource().compareToIgnoreCase(o.getSource());
  }
}


