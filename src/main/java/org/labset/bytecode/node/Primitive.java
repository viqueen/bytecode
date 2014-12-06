/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public class Primitive extends Variable {

  private PType ptype;
  
  public Primitive(String pDescriptor, String pValue, boolean pArray) {
    super(pDescriptor, pValue, pArray, Type.PRIMITIVE);
    ptype = PType.valueOf(pDescriptor);
  }
  
  public PType getPType() {
    return ptype;
  }
  
  public enum PType {
    B, //byte
    C, //char
    D, //double
    F, //float
    I, //int
    J, //long
    S, //short
    Z, //boolean
    V; //void
  }
}
