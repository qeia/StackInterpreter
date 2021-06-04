package StackOperation;

import Internal.StackContext;

import java.util.List;
import java.util.Stack;
public interface StackOperation {
  void execute(StackContext st, List params);
}