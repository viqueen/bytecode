/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import java.util.List;
import java.util.Optional;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.annotation.Choices.Choice;
import org.codehaus.preon.annotation.Init;
import org.labset.bytecode.attr.*;
import org.labset.bytecode.cp.CONSTANT_Utf8;
import org.labset.bytecode.node.Variable;

/**
 * @author hasnaer
 *
 */
public class MethodInfo extends ClassMemberInfo {

  @BoundList(size = "attributesCount", selectFrom = @Choices(prefixSize = 16, byteOrder = BigEndian, alternatives = {
      @Choice(condition = "outer.constantPool[prefix-1].value=='Code'", type = Code.class),
      @Choice(condition = "outer.constantPool[prefix-1].value=='Exceptions'", type = Exceptions.class),
      @Choice(condition = "outer.constantPool[prefix-1].value=='Synthetic'", type = Synthetic.class),
      @Choice(condition = "outer.constantPool[prefix-1].value=='Deprecated'", type = DeprecatedAttribute.class) }))
  private AttributeInfo[] attributes;

  @Override
  public AttributeInfo[] getAttributes() {
    return attributes;
  }

  @Override
  @Init
  protected final void init() {
    super.init();
    Utils.getAttribute(attributes, Code.class).ifPresent(code -> {
   // create a LocalVariableTable if none
      Optional<LocalVariableTable> lvtOpt = Utils.getAttribute(
          code.getAttributes(), LocalVariableTable.class);
      if (lvtOpt.isPresent()) {
        LocalVariableTable lvt = lvtOpt.get();
        init(lvt);
        code.setLocalVariableTable(lvt);
      } else {
        LocalVariableTable localVariableTable = new LocalVariableTable();
        List<String> types = Utils.getParamTypes(getDescriptor());
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