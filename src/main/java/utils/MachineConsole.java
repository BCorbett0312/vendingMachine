package utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class MachineConsole {

    private static MachineConsole machineConsole;

    private Scanner input;
    private PrintStream output;

    private MachineConsole(InputStream in, PrintStream out){
        this.input = new Scanner(in);
        this.output = out;

    }

    public static MachineConsole getInstance(){
        if(machineConsole == null){
            machineConsole = new MachineConsole(System.in, System.out);
        }
        return machineConsole;
    }

    public void print(String val, Object... args){
        output.format(val, args);
    }

    public void println(String val, Object... args){
        output.format(val, args);
    }

    public String getStringInput(String val, Object... args){
        println(val, args);
        return input.nextLine();
    }

    public Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Double doubleInput = Double.parseDouble(stringInput);
            return doubleInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Long longInput = Long.parseLong(stringInput);
            return longInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, args);
        }
    }

    public Integer getIntegerInput(String prompt, Object... args) {
        Integer toReturn = 0;
        while(true){
            toReturn = getLongInput(prompt, args).intValue();
            if(ChoiceValidator.isValidMenuChoice(toReturn)){
                return toReturn;
            }
            println("That is not a valid selection.  Please select a valid option.\n");
        }
    }

    public Integer getDollarsInput(String prompt, Object... args) {
        Integer toReturn = 0;
        while(true){
            toReturn = getLongInput(prompt, args).intValue();
            if(ChoiceValidator.isValidCurrencyAmount(toReturn)){
                return toReturn;
            }
            println("That is not a valid currency amount.  Please enter a valid amount.\n");
        }
    }

    public String getLocationInput(String prompt, Object... args){
        String toReturn = "";
        while(true){
            toReturn = getStringInput(prompt, args);
            if(ChoiceValidator.isValidLocation(toReturn)){
                return toReturn;
            }
            println("That is not a valid item code.  Please try again.\n");
        }
    }


}
