/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class Reference extends Variable {

  public Reference(String pDescriptor, String pValue, boolean pArray) {
    super(pDescriptor, pValue, pArray, Type.REFERENCE);
  }

}
