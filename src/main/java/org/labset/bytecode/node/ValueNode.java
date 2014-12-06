/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public abstract class ValueNode extends BaseNode {

  private String descriptor;
  private String value;
  
  public ValueNode(String pDescriptor, String pValue) {
    descriptor = pDescriptor;
    value = pValue;
  }
  
  public String getValue() {
    return value;
  }
  
  public String getDescriptor() {
    return  descriptor;
  }
}
