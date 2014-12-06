/**
 * LabSET 2014
 */
package org.labset.bytecode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.labset.bytecode.attr.Code;
import org.labset.bytecode.cp.CONSTANT_Utf8;
import org.labset.bytecode.cp.CPInfo;
import org.labset.bytecode.source.StatementBuilder;
import org.labset.bytecode.node.*;

/**
 * @author hasnaer
 *
 */
public class ClassFileTestCase {

  private Codec<ClassFile> codec;
  private static byte[] byteCode;

  @BeforeClass
  public static void load() throws IOException {
    try (InputStream stream = ClassFileTestCase.class
        .getResourceAsStream("/SampleClass.class")) {
      byteCode = IOUtils.toByteArray(stream);
    }
  }

  @Before
  public void setUp() {
    codec = Codecs.create(ClassFile.class);
  }

  @Test
  public void testDecode() throws DecodingException {
    ClassFile classFile = Codecs.decode(codec, byteCode);
    CPInfo[] constantPool = classFile.getConstantPool();

    Assert.assertEquals(5, classFile.getFields().length);
    Assert.assertEquals(2, classFile.getMethods().length);

    FieldInfo field = classFile.getFields()[0];
    Assert.assertEquals("firstName",
        Utils
            .getCPInfo(constantPool, field.getNameIndex(), CONSTANT_Utf8.class)
            .get().getValue());
  }

  @Test
  public void testDecompileSumMethod() throws Exception {
    ClassFile classFile = Codecs.decode(codec, byteCode);
    CPInfo[] constantPool = classFile.getConstantPool();
    MethodInfo sumMethod = classFile.getMethods()[1];
    Assert.assertEquals(
        "sum",
        Utils
            .getCPInfo(constantPool, sumMethod.getNameIndex(),
                CONSTANT_Utf8.class).get().getValue());
    Code code = Utils.getAttribute(sumMethod.getAttributes(), Code.class).get();
    StatementBuilder builder = new StatementBuilder(constantPool, code);
    List<BaseNode> statements = builder.build();
    Assert.assertEquals(1, statements.size());
    Assert.assertTrue(statements.get(0) instanceof ReturnNode);
  }
}