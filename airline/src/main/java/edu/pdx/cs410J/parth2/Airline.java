package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;

public class Airline extends AbstractAirline<Flight> {
  private final String name;

  public Airline(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<Flight> getFlights() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  ArrayList<Flight> arrli = new ArrayList<Flight>();
  String air_name;

  /**
   * This method is used to set the name of the airline
   * @param aname the name of the airline
   */
  public void setName(String aname) {
    this.air_name = aname;
  }

  /**
   * This method is used to retrieve the name of the airline
   * @return This returns the name of the airline
   */
  @Override
  public String getName() {
    return this.air_name;
  }

  /**
   * This method is used to add the Flight to the Airline
   * @param abstractFlight The object of the class AbstractFlight
   */
  @Override
  public void addFlight(AbstractFlight abstractFlight) {
    arrli.add((Flight)abstractFlight);
  }

  /**
   * This method is used to retrieve the flights added to the airline
   * @return This returns a list of flights that have been added to the airline
   */
  @Override
  public ArrayList<Flight> getFlights() {
    Collections.sort(arrli);
    return arrli;
  }
}
