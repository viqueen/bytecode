/**
 * LabSET 2014
 */
package org.labset.bytecode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import org.labset.bytecode.attr.AttributeInfo;
import org.labset.bytecode.cp.*;
import org.labset.bytecode.node.Variable;

/**
 * @author hasnaer
 *
 */
public class Utils {

  public static <T extends CPInfo> Optional<T> getCPInfo(
      final CPInfo[] pConstantPool, int pIndex, Class<T> pType) {
    Object constant = pConstantPool[pIndex - 1];
    if (pType.isInstance(constant)) {
      return Optional.of(pType.cast(constant));
    }
    return Optional.empty();
  }

  public static <T extends AttributeInfo> Optional<T> getAttribute(
      final AttributeInfo[] pAttributes, Class<T> pType) {
    for (AttributeInfo attribute : pAttributes) {
      if (pType.isInstance(attribute)) {
        return Optional.of(pType.cast(attribute));
      }
    }
    return Optional.empty();
  }

  public static String getClassName(CPInfo[] pConstantPool, int pIndex) {
    int classNameIndex = getCPInfo(pConstantPool, pIndex, CONSTANT_Class.class)
        .get().getNameIndex();
    return getCPInfo(pConstantPool, classNameIndex, CONSTANT_Utf8.class).get()
        .getValue();
  }

  public static Variable getFieldVariable(CPInfo[] pConstantPool, int pIndex) {
    CONSTANT_NameAndType nameAndType = getCPInfo(pConstantPool,
        pIndex, CONSTANT_NameAndType.class).get();
    String name = getCPInfo(pConstantPool, nameAndType.getNameIndex(),
        CONSTANT_Utf8.class).get().getValue();
    String descritpor = getCPInfo(pConstantPool,
        nameAndType.getDescriptorIndex(), CONSTANT_Utf8.class).get().getValue();

    return Variable.create(descritpor, name, false);
  }

  private static final Pattern descriptors = Pattern
      .compile("(\\[)?(B|C|D|F|I|J|S|Z|V|(L.*?;))");

  public static List<String> getParamTypes(String pDescriptor) {
    List<String> types = new ArrayList<>();
    int startIndex = pDescriptor.indexOf("(");
    int endIndex = pDescriptor.indexOf(")");
    Matcher matcher = descriptors.matcher(pDescriptor.substring(startIndex + 1,
        endIndex));
    while (matcher.find()) {
      types.add(matcher.group());
    }
    return types;
  }
}