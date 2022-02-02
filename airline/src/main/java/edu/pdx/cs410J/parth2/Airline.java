package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;
import java.util.ArrayList;


public class Airline extends AbstractAirline<Flight> {
  ArrayList<Flight> arrli = new ArrayList<Flight>();
  private String name;

  @Override
  public String getName() {
    return this.name;
  }

  public void setName(String aname) {
    this.name = aname;
  }


  @Override
  public void addFlight(Flight flight) {
    arrli.add(flight);
    //System.out.println(arrli.toString());
  }

  @Override
  public Collection<Flight> getFlights() {
    return arrli;
  }
}
