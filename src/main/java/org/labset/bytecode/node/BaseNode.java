/**
 * LabSET 2014
 */
package org.labset.bytecode.node;

/**
 * @author hasnaer
 *
 */
public abstract class BaseNode {

  private int lineNumber;
  
  public int getLineNumber() {
    return lineNumber;
  }
  
  public void setLineNumber(int pLineNumber) {
    lineNumber = pLineNumber;
  }
  
}