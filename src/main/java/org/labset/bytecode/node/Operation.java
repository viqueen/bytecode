/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class Operation extends ValueNode {

  private ValueNode left, right;
  private Type operator;

  public Operation(ValueNode pLeft, ValueNode pRight, Type pOperator) {
    super("", "");
    left = pLeft;
    right = pRight;
    operator = pOperator;
  }

  public ValueNode getLeft() {
    return left;
  }

  public ValueNode getRight() {
    return right;
  }

  public Type getOperator() {
    return operator;
  }

  public enum Type {
    ADD, SUM, MUL, DIV, REM, NE, EQ, LT, GE, GT, LE, AND, OR, XOR, LSH, RSH;
  }
  
  @Override
  public String toString() {
    return String.format("(%s %s %s)", left, operator, right);
  }
}