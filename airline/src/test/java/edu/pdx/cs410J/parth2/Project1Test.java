package edu.pdx.cs410J.parth2;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import edu.pdx.cs410J.InvokeMainTestCase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can handle the calls
 * to {@link System#exit(int)} and the like.
 */
class Project1Test extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project2.class, args );
  }

  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project1.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void testnoarguments(){
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("No arguments!"));
  }

  @Test
  public void testreadme() {
    MainMethodResult result = invokeMain(new String[] {"-README"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Please enter the arguments in the format given below"));
  }

  @Test
  public void testprint() {
    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "123", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 123 departs pdx at 03/03/2017 12:00 arrives dbo at 09/09/2017 16:00"));
  }

  @Test
  public void testNoCommandLineArguments(){
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("No arguments!"));
  }

  @Test
  public void missingCommandLineArguments(){
    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "123", "pdx"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("There are some missing arguments"));
  }

  @Test
  public void wrongflightnumber(){
    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "ewrdf2", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Please enter a numeric flight number or there might be some missing arguments!"));
  }

  @Test
  public void filenotexist(){
    File file = new File("xyz.txt");
    MainMethodResult result = null;
    if(file.delete()) {
      result = invokeMain(new String[]{"-textFile", "xyz.txt", "-print", "emirates", "23451", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    }
    else{
      result = invokeMain(new String[]{"-textFile", "xyz.txt", "-print", "emirates", "23451", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    }
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("File with given name does not exist. File created."));
  }

  @Test
  public void toomanyCommandLineArguments(){
    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "123", "pdx", "03/03/2017", "12:00", "dubai", "09/09/2017", "16:00", "dubai"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("There are some missing arguments  or the format was not correct"));
  }

  @Test
  public void invalidarguments(){
    MainMethodResult result = invokeMain(new String[] {"emirates", "-print", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("There are some missing arguments or the format was not correct"));
  }

  @Test
  public void testnewairline(){
    File file = new File("pratik.txt");
    MainMethodResult result = invokeMain(new String[] {"-textFile", "pratik.txt", "emirates", "123", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(1));
    assertTrue(file.exists());
  }

  @Test
  public void testnewairlinewithprint(){
    File file = new File("pratik.txt");
    MainMethodResult result = invokeMain(new String[] {"-textFile", "parth.txt", "-print", "emirates", "123", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Printing the contents of the Flight added to the airline as -print option is mentioned in arguments"));
    assertTrue(file.exists());
  }

  @Test
  public void testinvalidtimeinfile(){
    File file = new File("src/test/resources/edu/pdx/cs410J/parth2/invalidtime.txt");
    MainMethodResult result = invokeMain(new String[] {"-textFile", "src/test/resources/edu/pdx/cs410J/pkadam/invalidtime.txt", "emirates", "123", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("There is some issue with the file or file writer"));
  }

  @Test
  public void testinvalidformatinfile(){
    File file = new File("src/test/resources/edu/pdx/cs410J/parth2/invalidformat.txt");
    MainMethodResult result = invokeMain(new String[] {"-textFile", "src/test/resources/edu/pdx/cs410J/pkadam/invalidformat.txt", "emirates", "123", "pdx", "03/03/2017", "12:00", "dbo", "09/09/2017", "16:00"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("The text file is not formatted properly."));
  }
}
