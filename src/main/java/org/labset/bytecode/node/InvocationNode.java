/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class InvocationNode extends ValueNode {

  private BaseNode node;
  private InvocableNode invoke;
  
  public InvocationNode(BaseNode pNode, InvocableNode pInvoke) {
    super("", "");
    node = pNode;
    invoke = pInvoke;
  }
  
  public BaseNode getNode() {
    return node;
  }
  
  public InvocableNode getInvoke() {
    return invoke;
  }
  
  @Override
  public String toString() {
    return String.format("%s.%s", node, invoke);
  }
}
