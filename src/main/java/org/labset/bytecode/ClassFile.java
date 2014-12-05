/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.*;
import org.codehaus.preon.annotation.Choices.Choice;
import org.labset.bytecode.attr.AttributeInfo;
import org.labset.bytecode.attr.DeprecatedAttribute;
import org.labset.bytecode.attr.SourceFile;
import org.labset.bytecode.cp.*;

/**
 * @author hasnaer
 *
 */
public class ClassFile {

  @BoundBuffer(match = { (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe })
  private byte[] magic;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int minorVersion;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int majorVersion;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int constantPoolCount;

  @BoundList(size = "constantPoolCount - 1", types = { CONSTANT_Class.class,
      CONSTANT_Fieldref.class, CONSTANT_Methodref.class,
      CONSTANT_InterfaceMethodref.class, CONSTANT_String.class,
      CONSTANT_Integer.class, CONSTANT_Float.class, CONSTANT_Long.class,
      CONSTANT_Double.class, CONSTANT_NameAndType.class, CONSTANT_Utf8.class,
      CONSTANT_MethodHandle.class, CONSTANT_MethodType.class,
      CONSTANT_InvokeDynamic.class })
   private CPInfo[] constantPool;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int accessFlags;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int thisClass;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int superClass;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int interfacesCount;

  @BoundList(size = "interfacesCount")
  private int[] interfaces;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int fieldsCount;

  @BoundList(size = "fieldsCount")
  private FieldInfo[] fields;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int methodsCount;

  @BoundList(size = "methodsCount")
  private MethodInfo[] methods;

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int attributeCount;

  @BoundList(size = "attributeCount", selectFrom = @Choices(prefixSize = 16, byteOrder = BigEndian, alternatives = {
      @Choice(condition = "constantPool[prefix-1].value=='SourceFile'", type = SourceFile.class),
      @Choice(condition = "constantPool[prefix-1].value=='Deprecated'", type = DeprecatedAttribute.class) }))
  private AttributeInfo[] attributes;

  public FieldInfo[] getFields() {
    return fields;
  }

  public MethodInfo[] getMethods() {
    return methods;
  }
  
  public CPInfo[] getConstantPool() {
    return constantPool;
  }
  
}