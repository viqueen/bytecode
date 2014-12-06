/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class ArrayReferenceNode extends Reference {

  private String[] count;
  private int dimension;
  
  public ArrayReferenceNode(Reference pReference, String[] pCount) {
    super(pReference.getDescriptor(), pReference.getValue(), false);
    count = pCount;
    dimension = pCount.length;
  }
  
  public String[] getCount() {
    return count;
  }
  
  public int getDimension () {
    return dimension;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (String c : count) {
      builder.append("[").append(c).append("]");
    }
    return String.format("%s%s", super.toString(), builder);
  }
}
