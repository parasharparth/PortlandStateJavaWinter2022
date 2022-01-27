package edu.pdx.cs410J.parth2;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;

import java.io.FileWriter;
import java.io.IOException;
/**
 * The main class for the CS410J airline Project
 */
public class Project2 {

    public static void main(String[] args) throws ParserException, IOException {
        int flag = 0;
        //Case when there are no arguments
        if (args.length == 0) {
            System.err.println("No arguments!!");
            printMissingArgsMessage();
        }

        //Case when the number of arguments are less than 8 (bare minimum arguments)
        if(args.length < 8)
        {
            System.err.println("There are some missing arguments");
            printMissingArgsMessage();
        }

        if(args.length == 8)
        {
            if(args[0].equals("-README") || args[1].equals("-README") || args[0].equals("-print") || args[1].equals("-print") || args[0].equals("-textFile") || args[1].equals("textFile") || args[0].contains(".txt") || args[1].contains(".txt"))
            {
                System.err.println("There are some missing arguments or the format was not correct");
                printMissingArgsMessage();
            }
            else {
                flag=0;
                addFlightToAirline(args,flag);
            }
        }

        if(args.length == 9)
        {
            //Here one of Print, ReadMe or textFile must be present.
            if((args[0].equals("-README") && args[1].equals("-print")) || (args[0].equals("-print") && args[1].equals("-README")) ||
                    (args[0].equals("-textFile") || args[1].equals("-textFile")) || (args[0].contains(".txt") || args[1].contains(".txt"))
            )
            {
                //this is a case where both print and README are present making the required arguments as one less than 8
                System.err.println("There are some missing arguments or the format was not correct");
                printMissingArgsMessage();
            }
            else if ((args[0].equals("-README") || args[0].equals("-print")) && (!args[1].equals("-README")) || (!args[1].equals("-print")))
            {
                if(args[0].equals("-README"))
                {
                    flag =1;
                    printReadMe();
                    addFlightToAirline(args,flag);
                }
                else if (args[0].equals("-print"))
                {
                    flag=1;
                    printInfo(args,flag);
                    addFlightToAirline(args,flag);
                }
            }
        }

        //Case when there are 10 arguments
        if(args.length == 10)
        {
            //here both README AND PRINT must be present. If any is missing, print error message
            if((args[0].equals("-README") && args[1].equals("-print")) || (args[0].equals("-print") && args[1].equals("-README")))
            {
                flag=2;
                printReadMe();
                printInfo(args,flag);
                addFlightToAirline(args,flag);
            }
            else if(args[0].equals("-textFile") && args[1].contains(".txt"))
            {
                //Read the airline from the file, then add the flight to the airline and then write it back to the file.

                String filepath = args[1];
                try {
                    int k;
                    String first, second;
                    first = args[0];
                    second = args[1];
                    for(k = 0; k < args.length-2; k++){
                        args[k] = args[k+2];
                    }
                    args[k] = first;
                    args[k+1] = second;
                    Object[] objs = compute(args);
                    TextParser parser = new TextParser();
                    TextDumper dumper = new TextDumper();
                    dumper.setFilename(args[9]);
                    parser.setnames(args[9], args[0]);
                    Airline parsedairline = parser.parse();
                    parsedairline.addFlight((Flight) objs[1]);
                    dumper.dump(parsedairline);
                    System.exit(1);
                }
                catch(Exception e)
                {
                    System.err.println("There is some issue with the file or file writer");
                    System.exit(1);
                }
            }
            else
            {
                System.err.println("There are some missing arguments  or the format was not correct");
                printMissingArgsMessage();
            }
        }

        //Case when there are 11 arguments
        if(args.length == 11) {

            //For print
            if((args[0].equals("-textFile") && args[1].contains(".txt") && args[2].equals("-print")) || (args[0].equals("-print") && args[1].equals("-textFile") && args[2].contains(".txt"))) {

                //The First step is to print the details
                printInfo(args,3);
                int k;
                String first, second, third;
                first = args[0];
                second = args[1];
                third = args[2];
                for(k = 0; k < args.length-3; k++){
                    args[k] = args[k+3];
                }
                args[k] = first;
                args[k+1] = second;
                args[k+2] = third;
                Object[] objs = compute(args);
                TextParser parser = new TextParser();
                TextDumper dumper = new TextDumper();
                if(args[8].equals("-textFile")){
                    dumper.setFilename(args[9]);
                    parser.setnames(args[9], args[0]);
                }
                else {
                    dumper.setFilename(args[10]);
                    parser.setnames(args[10], args[0]);
                }
                Airline parsedairline = parser.parse();
                parsedairline.addFlight((Flight) objs[1]);
                dumper.dump(parsedairline);
                System.out.println(objs[1].toString());
                System.exit(0);
            }
            else if ((args[0].equals("-textFile") && args[1].contains(".txt") && args[2].equals("-README")) || (args[0].equals("-README") && args[1].equals("-textFile") && args[2].contains(".txt")))
            {
                printReadMe();
                int k;
                String first, second, third;
                first = args[0];
                second = args[1];
                third = args[2];
                for(k = 0; k < args.length-3; k++){
                    args[k] = args[k+3];
                }
                args[k] = first;
                args[k+1] = second;
                args[k+2] = third;
                Object[] objs = compute(args);
                TextParser parser = new TextParser();
                TextDumper dumper = new TextDumper();
                if(args[8].equals("-textFile")){
                    dumper.setFilename(args[9]);
                    parser.setnames(args[9], args[0]);
                }
                else {
                    dumper.setFilename(args[10]);
                    parser.setnames(args[10], args[0]);
                }
                Airline parsedairline = parser.parse();
                parsedairline.addFlight((Flight) objs[1]);
                dumper.dump(parsedairline);
                System.out.println(objs[1].toString());
                System.exit(0);
            }
            else {
                System.err.println("Please check the arguments");
                System.exit(1);
            }
        }

        //Case when there are 12 arguments
        if(args.length == 12)
        {
            //There must be each and every argument present here.
            printReadMe();
            printInfo(args,4);
        }

        if(args.length > 12)
        {
            System.err.println("Please check the arguments");
            System.exit(1);
        }
    }


    public static void printMissingArgsMessage()
    {
        System.out.println("Please enter the arguments in the format given below");
        System.out.println("-print -README \"Alaska Airlines\" 1022 IND 3/15/2022 10:39 USA 3/15/2022 10:42");
        System.out.println("-print :- option for printing the airline and flight entered by the user");
        System.out.println("-README :- option for printing the contents of the README file");
        System.out.println("Alaska Airlines :- Represent the name of the airline");
        System.out.println("1022 :- This is the flight number");
        System.out.println("IND :- 3 letter code for the departure airport");
        System.out.println("3/15/2022 10:39:- Represent the departure time in the 24-hour format");
        System.out.println("USA:- 3 letter code for the arrival airport");
        System.out.println("3/15/2022 10:42 :- Represent the arrival time in the 24-hour format");
        System.exit(1);
    }

    public static Airline addFlightToAirline(String[] args,int flag)
    {


        //if there are both README and print present
        if(flag == 2)
        {
            Flight flight = new Flight();
            Airline airline = new Airline();
            airline.setName(args[2]);
            flight.setFlightnum(args[3]);
            flight.setSrc(args[4]);
            flight.setDepart(args[5], args[6]);
            flight.setDest(args[7]);
            flight.setArrive(args[8], args[9]);
            airline.addFlight(flight);

            System.out.println(airline.toString());
            return airline;
        }
        else if (flag == 1)
        {
            //if there is one of README and print present
            Flight flight = new Flight();
            Airline airline = new Airline();
            airline.setName(args[1]);
            flight.setFlightnum(args[2]);
            flight.setSrc(args[3]);
            flight.setDepart(args[4], args[5]);
            flight.setDest(args[6]);
            flight.setArrive(args[7], args[8]);
            airline.addFlight(flight);
            System.out.println(airline.toString());
            return airline;
        }
        else
        {
            //if only airline and flight details are present, then,
            Flight flight = new Flight();
            Airline airline = new Airline();
            System.out.println("arguments are:- "+args[0]+" "+args[1]+ " "+args[2]+" "+args[3]+ " "+args[4]+" "+args[5]+ " "+args[6]+" "+args[7]);
            airline.setName(args[0]);
            flight.setFlightnum(args[1]);
            flight.setSrc(args[2]);
            flight.setDepart(args[3], args[4]);
            flight.setDest(args[5]);
            flight.setArrive(args[6], args[7]);
            airline.addFlight(flight);
            return airline;
        }

    }

    public static void printReadMe()
    {
        System.out.println("README for Project 1\nName: Parth Parashar");
        System.out.println("Project 1 : Designing an Airline Application");
        System.out.println("The goal of this project was to extend classes that I did not write and perform more complex command line parsing.");
        System.out.println("The Airline class extends AbstractAirline and the Flight class extends AbstractFlight");
        System.out.println("Airline class - it has a name and consists of multiple flights.");
        System.out.println("Flight class - it consists of details like flight number, source, departure time, destination, arrival time.");
        System.out.println("Project1 class - it consists of the main method that parses the command line, creates an Airline and a FLight as specified.\nIt adds the Flight to the Airline and optionally prints a description of the flight.");
    }

    public static void printInfo(String[] args,int flag)
    {
        if(flag == 1) {
            System.out.println("Printing the contents of the Flight added to the airline as -print option is mentioned in arguments");
            System.out.println("Airline Name :- " + args[1]);
            System.out.println("The flight Number is:- " + args[2]);
            System.out.println("The source of the flight is:- " + args[3]);
            System.out.println("The departure time for the flight is:- " + args[4] + " " + args[5]);
            System.out.println("The Destination of the flight is:- " + args[6]);
            System.out.println("The arrival time for the flight is:- " + args[7] + " " + args[8]);
        }
        if (flag == 2)
        {
            System.out.println("Printing the contents of the Flight added to the airline as -print option is mentioned in arguments");
            System.out.println("Airline Name :- " + args[2]);
            System.out.println("The flight Number is:- " + args[3]);
            System.out.println("The source of the flight is:- " + args[4]);
            System.out.println("The departure time for the flight is:- " + args[5] + " " + args[6]);
            System.out.println("The Destination of the flight is:- " + args[7]);
            System.out.println("The arrival time for the flight is:- " + args[8] + " " + args[9]);
        }

        if (flag == 3)
        {
            System.out.println("Printing the contents of the Flight added to the airline as -print option is mentioned in arguments");
            System.out.println("Airline Name :- " + args[3]);
            System.out.println("The flight Number is:- " + args[4]);
            System.out.println("The source of the flight is:- " + args[5]);
            System.out.println("The departure time for the flight is:- " + args[6] + " " + args[7]);
            System.out.println("The Destination of the flight is:- " + args[8]);
            System.out.println("The arrival time for the flight is:- " + args[9] + " " + args[10]);
        }

        if(flag == 4)
        {
            System.out.println("Printing the contents of the Flight added to the airline as -print option is mentioned in arguments");
            System.out.println("Airline Name :- " + args[4]);
            System.out.println("The flight Number is:- " + args[5]);
            System.out.println("The source of the flight is:- " + args[6]);
            System.out.println("The departure time for the flight is:- " + args[7] + " " + args[8]);
            System.out.println("The Destination of the flight is:- " + args[9]);
            System.out.println("The arrival time for the flight is:- " + args[10] + " " + args[11]);
        }
    }

    public static Object[] compute(String[] args) {
        Flight flight = new Flight();
        Airline airline = new Airline();
        airline.setName(args[0]);
        flight.setFlightnum(args[1]);
        flight.setSrc(args[2]);
        flight.setDepart(args[3], args[4]);
        flight.setDest(args[5]);
        flight.setArrive(args[6], args[7]);
        airline.addFlight(flight);

        //return flight;
        Object[] objs = new Object[2];
        objs[0] = airline;
        objs[1] = flight;
        return objs;
    }
}