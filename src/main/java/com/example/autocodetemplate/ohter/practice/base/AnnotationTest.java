package com.example.autocodetemplate.ohter.practice.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

@MyIF(author = "muyibeyond", desc = "test anotation class")
public class AnnotationTest {
    @MyIF(author = "muyibeyond", desc = "test anotation method")
    public void method() {
        System.out.println("do something");
    }

    public static void main(String[] args) {
        try {
            // 加载类
            Class c = Class.forName("practice.base.AnnotationTest");
            // 获取是否有对应注解
            boolean classAnoIsExist = c.isAnnotationPresent(MyIF.class);

            if (classAnoIsExist) {
                // 拿到注解实例，解析注解
                MyIF d = (MyIF) c.getAnnotation(MyIF.class);
                System.out.println("编写这个类的作者：" + d.author() + "类描述：" + d.desc());
            }

            // 获取类方法
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean meheodAnoIsExist = m.isAnnotationPresent(MyIF.class);
                if (meheodAnoIsExist) {
                    MyIF d = m.getAnnotation(MyIF.class);
                    System.out.println("编写这个方法的作者：" + d.author() + "类描述：" + d.desc());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyIF {
    String author() default "yates";

    String desc() default "";
}

//////////////////////////////////////////////////

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
  @interface DataMasking {

    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;

}

  interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);

}

  enum DataMaskingFunc {

    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingOperation operation;

    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

}

  final class DataMaskingSerializer extends StdScalarSerializer<Object> {
    private final DataMaskingOperation operation;

    public DataMaskingSerializer() {
        super(String.class, false);
        this.operation = null;
    }

    public DataMaskingSerializer(DataMaskingOperation operation) {
        super(String.class, false);
        this.operation = operation;
    }


    public boolean isEmpty(SerializerProvider prov, Object value) {
        String str = (String)value;
        return str.isEmpty();
    }

    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.isNull(operation)) {
            String content = DataMaskingFunc.ALL_MASK.operation().mask((String) value, null);
            gen.writeString(content);
        } else {
            String content = operation.mask((String) value, null);
            gen.writeString(content);
        }
    }

    public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("string", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.visitStringFormat(visitor, typeHint);
    }
}