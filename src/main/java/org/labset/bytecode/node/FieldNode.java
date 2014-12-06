/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class FieldNode extends InvocableNode {

  private boolean isStatic;

  public FieldNode(String pDescriptor, String pValue, String pClassName,
      boolean pIsStatic) {
    super(pDescriptor, pValue, pClassName);
    isStatic = pIsStatic;
  }

  public boolean isStatic() {
    return isStatic;
  }
  
  @Override
  public String toString() {
    return String.format("%s", getValue());
  }
}
