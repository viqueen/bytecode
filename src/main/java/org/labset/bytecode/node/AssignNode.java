/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class AssignNode extends BaseNode {

  private ValueNode variable;
  private ValueNode value;
  private Type type;

  public AssignNode(ValueNode pVariable, ValueNode pValue, Type pType) {
    variable = pVariable;
    value = pValue;
    type = pType;
  }

  public enum Type {
    FIRST, NONFIRST;
  }
  
  public ValueNode getVariable() {
    return variable;
  }
  
  public ValueNode getValue() {
    return value;
  }
  
  public Type getType() {
    return type;
  }
  
  @Override
  public String toString() {
    return String.format("%s = %s", variable, value);
  }
}