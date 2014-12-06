/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public abstract class Variable extends ValueNode {

  private String value; // variable name or constant value
  private String descriptor;
  private Type type;
  private boolean array;
  
  public Variable(String pDescriptor, String pValue, boolean pArray, Type pType) {
    descriptor = pDescriptor;
    value = pValue;
    type = pType;
    array = pArray;
  }
  
  public String getValue() {
    return value;
  }
  
  public String getDescriptor() {
    return  descriptor;
  }
  
  public boolean isArray() {
    return array;
  }
  
  public Type getType () {
    return type;
  }
  
  public static Variable create(String pDescriptor, String pValue, boolean pArray) {
    if (pDescriptor.startsWith("[")) {
      return create(pDescriptor.substring(1), pValue, true);
    }
    if (pDescriptor.startsWith("L")) {
      return new Reference(pDescriptor.substring(1), pValue, pArray);
    }
    return new Primitive(pDescriptor, pValue, pArray);
  }
  
  enum Type {
    PRIMITIVE, REFERENCE;
  }
  
  @Override
  public String toString() {
    return String.format("[%s:%s]", value, descriptor);
  }
}