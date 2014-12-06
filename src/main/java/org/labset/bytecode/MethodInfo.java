/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.annotation.Choices.Choice;
import org.labset.bytecode.attr.*;

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

}