/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class ReturnNode extends BaseNode {

  private ValueNode returnValue;
  
  public ReturnNode(ValueNode pReturnValue) {
    returnValue = pReturnValue;
  }
  
  public ValueNode getReturnValue() {
    return returnValue;
  }
  
  @Override
  public String toString() {
    return String.format("return %s", returnValue);
  }
}