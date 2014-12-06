/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import java.util.List;
import java.util.Optional;

import org.codehaus.preon.annotation.*;
import org.codehaus.preon.annotation.Choices.Choice;
import org.labset.bytecode.attr.*;
import org.labset.bytecode.cp.*;
import org.labset.bytecode.node.Variable;

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

  @Init
  private void init() {
    // for each method initialize the LocalVariableTable attribute
    // if none available then create it by using the method descriptor
    // TODO: once component injection is ready, move this code
    // to MethodInfo.init
    for (MethodInfo method : methods) {
      Utils.getAttribute(method.getAttributes(), Code.class).ifPresent(
          code -> {
            // create a LocalVariableTable if none
            Optional<LocalVariableTable> lvtOpt = Utils.getAttribute(
                code.getAttributes(), LocalVariableTable.class);
            if (lvtOpt.isPresent()) {
              LocalVariableTable lvt = lvtOpt.get();
              init(lvt);
              code.setLocalVariableTable(lvt);
            } else {
              LocalVariableTable localVariableTable = new LocalVariableTable();
              List<String> types = Utils.getParamTypes(Utils
                  .getCPInfo(constantPool, method.getDescriptorIndex(),
                      CONSTANT_Utf8.class).get().getValue());
              code.setLocalVariableTable(localVariableTable);
              int index = 1;
              for (String type : types) {
                localVariableTable.getVariables()
                    .put(
                        index,
                        Variable.create(type, String.format("pArg%s", index), false));
                index++;
              }
              code.setLocalVariableTable(localVariableTable);
            }
          });
    }
  }

  private void init(LocalVariableTable pLvt) {
    for (LocalVariableTable.Entry entry : pLvt.getEntries()) {
      String name = Utils
          .getCPInfo(constantPool, entry.getNameIndex(), CONSTANT_Utf8.class)
          .get().getValue();
      String descriptor = Utils
          .getCPInfo(constantPool, entry.getDescriptorIndex(),
              CONSTANT_Utf8.class).get().getValue();
      pLvt.getVariables().put(entry.getIndex(), Variable.create(descriptor, name, false));
    }
  }
}