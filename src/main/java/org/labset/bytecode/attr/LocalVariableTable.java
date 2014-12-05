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
public class LocalVariableTable extends AttributeInfo {

    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int localVariableTableLength;

    @BoundList(size = "localVariableTableLength")
    private Entry[] localVariableTable;

    public static class Entry {
        @BoundNumber(size = "16", byteOrder = BigEndian)
        private int startPc;
        @BoundNumber(size = "16", byteOrder = BigEndian)
        private int length;
        @BoundNumber(size = "16", byteOrder = BigEndian)
        private int nameIndex;
        @BoundNumber(size = "16", byteOrder = BigEndian)
        private int descriptorIndex;
        @BoundNumber(size = "16", byteOrder = BigEndian)
        private int index;
    }
}