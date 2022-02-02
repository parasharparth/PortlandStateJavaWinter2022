package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.lang.model.element.Element;
import java.io.*;
import java.util.*;

public class TextDumper implements AirlineDumper<Airline> {
  String filename;

  public TextDumper() {}

  @Override
  public void dump(Airline airline) throws IOException {
    ArrayList arrli = (ArrayList) airline.getFlights();
    for(int i=0;i<arrli.size();i++)
    {
      System.out.println("The contents of the arraylist are: -"+arrli.get(i).toString());
    }
    String[] flights = new String[arrli.size()];
    FileWriter fw = new FileWriter(filename,true);
    PrintWriter out = new PrintWriter(fw);
    out.write("");
//    out.write(airline.getName());
    out.write("\n");
    out.write(arrli.get(0).toString());
//    out.write("");
//    out.write(airline.getName());
//    Collections.sort(arrli);
//    for(int i=0; i < arrli.size(); i++){
//      flights[i] = arrli.get(i).toString();
//      out.write("\n");
//      out.write(flights[i]);
//    }
    out.close();
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}
