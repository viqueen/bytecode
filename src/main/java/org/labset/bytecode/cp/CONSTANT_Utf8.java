/**
 * LabSET 2014
 */
package org.labset.bytecode.cp;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.annotation.TypePrefix;

/**
 * @author hasnaer
 *
 */
@TypePrefix(value = "1", size = 8)
public class CONSTANT_Utf8 implements CPInfo {

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int length;
  @BoundString(size = "length")
  private String value;

  public String getValue() {
    return value;
  }
}