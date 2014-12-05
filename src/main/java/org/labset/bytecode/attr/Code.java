/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.annotation.Choices.Choice;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * @author hasnaer
 *
 */
public class Code extends AttributeInfo {

    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int maxStack;
    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int maxLocals;
    @BoundNumber(size = "32", byteOrder = ByteOrder.BigEndian)
    private int codeLength;
    @BoundList(size = "codeLength")
    private byte[] code;
    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int exceptionTableLength;
    @BoundList(size="exceptionTableLength")
    private Exceptions.Entry[] exceptionTable;
    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int attributesCount;    
    @BoundList(size = "attributesCount", selectFrom = @Choices(prefixSize = 16, byteOrder = BigEndian, alternatives = {
            @Choice(condition = "outer.outer.constantPool[prefix-1].value=='LineNumberTable'", type = LineNumberTable.class),
            @Choice(condition = "outer.outer.constantPool[prefix-1].value=='LocalVariableTable'", type = LocalVariableTable.class)}))
    private AttributeInfo[] attributes;
}
