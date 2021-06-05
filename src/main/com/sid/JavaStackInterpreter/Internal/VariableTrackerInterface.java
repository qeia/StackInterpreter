package main.com.sid.JavaStackInterpreter.Internal;

//API to keep track of the variables with shadowing and GC
public interface VariableTrackerInterface {

     Integer findVariableValue (String variable);

     void addNewLevel ();

     void addVariable (String var, Integer value);

     void removeCurrentLevel ();
}
