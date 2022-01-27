package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements AirlineParser<Airline> {

  public TextParser() {

  }

  //Now we have to get the filename and airline name from the
  String filename, airlinename;

  public void setnames(String fname, String aname) {
    this.filename = fname;
    this.airlinename = aname;
  }



  @Override
  public Airline parse() throws ParserException {
    Scanner sc = null;
    try {
      sc = new Scanner(new File(this.filename));
    } catch (FileNotFoundException e) {
      System.out.println("Text Dump File with given name does not exist. File created.");
      if(!this.filename.contains("/")){
        PrintWriter out = null;
        try {
          out = new PrintWriter(this.filename);
        } catch (FileNotFoundException ex) {
          System.out.println("File is not present.");
        }
        out.write(this.airlinename);
        out.close();
      }
      else {
        PrintWriter out = null;
        File f = null;
        File f1 = null;
        String v;
        boolean bool = false;
        f = new File(this.filename);
        f1 = f.getParentFile();
        v = f1.getAbsolutePath();
        bool = f1.exists();
        //check if directory exists or not
        if (bool) {
          try {
            out = new PrintWriter(this.filename);
          } catch (FileNotFoundException ex) {
            ex.printStackTrace();
          }
          out.write(this.airlinename);
          out.close();
        } else {
          File folder = new File(v);
          if (folder.mkdir()) {
            try {
              out = new PrintWriter(this.filename);
            } catch (FileNotFoundException ex) {
              ex.printStackTrace();
            }
            out.write(this.airlinename);
            out.close();
          } else {
            System.out.println("Could not create directory");
            System.exit(1);
          }
        }
      }
    }
    try {
      sc = new Scanner(new File(this.filename));
    } catch (FileNotFoundException e) {
      System.out.println("File is not present.");
    }
    ArrayList<String> lines = new ArrayList<String>();
    assert sc != null;
    while (sc.hasNextLine()) {
      lines.add(sc.nextLine());
    }
    Airline airline = new Airline();
    if(lines.size() == 0){
      System.err.println("The textfile is empty!");
      System.exit(1);
    }
    //Checks if the airline name in the file is similar to that in the command line or not
    if(!this.airlinename.equals(lines.get(0))){
      System.err.println("The airline name is different than in the file");
      System.exit(1);
    }

    airline.setName(lines.get(0));

    //This for loop checks each and every detail of every flight in the text file
    for(int i = 1; i < lines.size(); i++) {
      String[] words = lines.get(i).split(" ");
      if(words.length != 12) {
        System.err.println("The text file is not formatted properly.");
        System.exit(1);
      }
      checkFlightnum(words[1]);
      System.out.println("departure time is:- "+words[5]+words[6]);
      System.out.println("arrival  time is:- "+words[10]+words[11]);
      checkdatetime(words[5], words[6]);
      checkdatetime(words[10], words[11]);
      Flight flight = new Flight();
      flight.setFlightnum(words[1]);
      flight.setSrc(words[3]);
      flight.setDepart(words[5], words[6]);
      flight.setDest(words[9]);
      flight.setArrive(words[10], words[11]);
    }


    return airline;

    }

  public static void checkFlightnum(String number) {
    int num = 0;
    try {
      num = Integer.parseInt(number);
    }
    catch(NumberFormatException e) {
      System.err.println("Invalid flightnumbers in the text file!");
      System.exit(1);
    }
    String numeric = "[0-9]+";
    Pattern pattern = Pattern.compile(numeric);
    Matcher matcher = pattern.matcher(Integer.toString(num));
    if (matcher.matches()) {
      return;
    }
    else {
      System.out.println("Invalid flightnumbers in the text file!");
      System.exit(1);
    }
  }



  public static void checkdatetime(String date, String time) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    String finaldatetime = date + " " + time ;
    try{
      Date d = formatter.parse(finaldatetime);
    }
    catch (ParseException e){
      System.err.println("Please verify the format for datetime in the text file");
      System.exit(1);
    }
  }
}

