/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * @author hasnaer
 *
 */
public class Exceptions extends AttributeInfo {

    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int numberOfExceptions;
    @BoundList(size="numberOfExceptions")
    private int[] exceptionIndexTable;
    
    
    public static class Entry {
        @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
        private int startPC;
        @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
        private int endPC;
        @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
        private int handlerPC;
        @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
        private int catchType;
    }
}