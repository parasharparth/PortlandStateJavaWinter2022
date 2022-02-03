package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;

public class TextDumper implements AirlineDumper<Airline> {
  String filename;

  public TextDumper() {}

  @Override
  public void dump(Airline airline) throws IOException {
    ArrayList arrli = (ArrayList) airline.getFlights();
  String[] flights = new String[arrli.size()];
  File f = new File(filename);
  PrintWriter out = new PrintWriter(filename);
        out.write("");
        out.write(airline.getName());

        Collections.sort(arrli);

        for(int i=0; i < arrli.size(); i++){
    flights[i] = arrli.get(i).toString();
    out.write("\n");
    out.write(flights[i]);
  }
        out.close();
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}
