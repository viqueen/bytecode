/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;

/**
 * @author hasnaer
 *
 */
public class LineNumberTable extends AttributeInfo {
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int lineNumberTableLength;
  @BoundList(size = "lineNumberTableLength")
  private Entry[] lineNumberTable;

  public static class Entry {
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int startPC;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int lineNumber;
  }
}
