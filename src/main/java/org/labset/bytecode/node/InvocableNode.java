/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public abstract class InvocableNode extends ValueNode {

  private String className;

  public InvocableNode(String pDescriptor, String pName, String pClassName) {
    super(pDescriptor, pName);
    className = pClassName;
  }

  public String getClassName() {
    return className;
  }
}
