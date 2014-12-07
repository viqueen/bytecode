/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.Init;
import org.codehaus.preon.annotation.Inject;
import org.labset.bytecode.attr.AttributeInfo;
import org.labset.bytecode.cp.CONSTANT_Utf8;
import org.labset.bytecode.cp.CPInfo;

/**
 * @author hasnaer
 *
 */
abstract class ClassMemberInfo {
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int accessFlags;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int nameIndex;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int descriptorIndex;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int attributesCount;

  @Inject(name = "constantPool")
  protected CPInfo[] constantPool;

  private String name;
  private String descriptor;

  public abstract AttributeInfo[] getAttributes();

  public int getAccessFlags() {
    return accessFlags;
  }

  public int getNameIndex() {
    return nameIndex;
  }

  public int getDescriptorIndex() {
    return descriptorIndex;
  }

  public String getName() {
    return name;
  }

  public String getDescriptor() {
    return descriptor;
  }

  @Init
  protected void init() {
    name = Utils.getCPInfo(constantPool, nameIndex, CONSTANT_Utf8.class).get()
        .getValue();
    descriptor = Utils
        .getCPInfo(constantPool, descriptorIndex, CONSTANT_Utf8.class).get()
        .getValue();
  }
}
