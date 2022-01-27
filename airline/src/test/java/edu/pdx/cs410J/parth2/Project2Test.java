package edu.pdx.cs410J.parth2;
import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;


public class Project2Test extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

//    @Test
//    public void testnoarguments(){
//        MainMethodResult result = invokeMain();
//        assertThat(result.getExitCode(), equalTo(1));
//        assertThat(result.getTextWrittenToStandardError(), containsString("No arguments!"));
//    }
}
