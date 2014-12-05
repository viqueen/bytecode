/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.annotation.Choices.Choice;
import org.labset.bytecode.attr.AttributeInfo;
import org.labset.bytecode.attr.ConstantValue;
import org.labset.bytecode.attr.DeprecatedAttribute;
import org.labset.bytecode.attr.Synthetic;

/**
 * @author hasnaer
 *
 */
public class FieldInfo extends ClassMemberInfo {

  
  @BoundList(size = "attributesCount", 
      selectFrom = @Choices(prefixSize = 16, byteOrder = BigEndian, alternatives = {
          @Choice(condition = "outer.constantPool[prefix-1].value=='ConstantValue'", type = ConstantValue.class),
          @Choice(condition = "outer.constantPool[prefix-1].value=='Synthetic'", type = Synthetic.class),
          @Choice(condition = "outer.constantPool[prefix-1].value=='Deprecated'", type = DeprecatedAttribute.class) 
          }
      ))
  private AttributeInfo[] attributes;
  
  @Override
  public AttributeInfo[] getAttributes() {
    return attributes;
  }
}