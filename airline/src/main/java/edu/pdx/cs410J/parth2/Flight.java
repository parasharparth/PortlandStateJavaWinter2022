package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight {

  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
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

    String strDate = formatter.format(dprt);
    return strDate;
  }

  @Override
  public String getDestination() {
   return dst;
  }

  @Override
  public String getArrivalString() {

    String strDate = formatter.format(arrv);
    return strDate;
  }

  public void setFlightnum(String number) {
    int num = 0;
    try {
      num = Integer.parseInt(number);
    }
    catch(NumberFormatException e) {
      System.err.println("Please enter a numeric flight number or there might be some missing arguments!");
      System.exit(1);
    }
    String numeric = "[0-9]+";
    Pattern pattern = Pattern.compile(numeric);
    Matcher matcher = pattern.matcher(Integer.toString(num));
    if (matcher.matches()) {
      this.flightnum = num;
    }
    else {
      System.out.println("Please check the flight number or there might be some missing arguments!");
      System.exit(1);
    }
  }

  public void setSrc(String source) {
    this.src = source;
  }

  public void setDepart(String date, String time, String ampm) {
    String finaldatetime = date + " " + time + " " + ampm;
    try{
      this.dprt = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      System.err.println("Please verify the format for departing date and time or there might be some missing arguments");
      System.exit(1);
    }
  }

  public void setDest(String dest) {
      this.dst = dest;
    }


  public void setArrive(String date, String time, String ampm) {
    String finaldatetime = date + " " + time + " " + ampm;
    try{
      this.arrv = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      System.err.println("Please verify the format for arriving date and time or there might be some missing arguments");
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
}


