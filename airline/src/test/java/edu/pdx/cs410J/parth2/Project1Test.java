package edu.pdx.cs410J.parth2;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import edu.pdx.cs410J.InvokeMainTestCase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
  @Test
  public void testreadme() {
    MainMethodResult result = invokeMain(new String[] {"-README"});
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Name: Parth Parashar"));
  }

//  @Test
//  public void testprint() {
//    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "123", "pdx", "03/03/2022", "12:00", "dbo", "09/09/2022", "16:00"});
//    assertThat(result.getExitCode(), equalTo(0));
//    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 123 departs pdx at 03/03/2022 12:00 arrives dbo at 09/09/2022 16:00"));
//  }


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
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
  public void toomanyCommandLineArguments(){
    MainMethodResult result = invokeMain(new String[] {"-print", "emirates", "123", "pdx", "03/03/2022", "12:00", "dubai", "09/09/2022", "16:00", "dubai"});
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Please check the arguments"));
  }

  @Test
  public void testnewairline(){
    MainMethodResult result = invokeMain(new String[] {"-textFile", "parth.txt", "emirates", "123", "pdx", "03/03/2022", "12:00", "dbo", "09/09/2022", "16:00"});
    assertThat(result.getExitCode(), equalTo(0));
  }
}
