package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public interface StackOperation {
  void execute(StackContext st, List params);
}