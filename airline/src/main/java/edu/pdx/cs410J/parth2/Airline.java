package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractFlight;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;

public class Airline extends AbstractAirline<Flight> {
  ArrayList<Flight> arrli = new ArrayList<Flight>();
  private final String name;


  public Airline() {
  }
  public Airline(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    arrli.add(flight);
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<Flight> getFlights() {
    throw new UnsupportedOperationException("This method is not implemented yet");

  }


  /**
   * This method is used to set the name of the airline
   * @param name the name of the airline
   */
  public void setName(String aname) {
    this.name = aname;
  }
}
