package com.example.autocodetemplate.ohter.practice.base;

/**
 * clone方法只是浅度clone，如需深度clone实现cloneable，重写clone方法
 */
public class CloneTest {
    public static void main(String[] args) throws Exception {
        ClnoeClass clnoeClass = new ClnoeClass();
        ClnoeClass innerClnoeClass = new ClnoeClass();
        clnoeClass.setInnerClnoeClass(innerClnoeClass);
        clnoeClass.setAge(22);
        clnoeClass.setName("yates");
        ClnoeClass clnoeClass2 = (ClnoeClass)clnoeClass.clone();
        System.out.println(clnoeClass2.toString() );
    }
}

class ClnoeClass implements Cloneable{
    private String name;
    private Integer age;
    private ClnoeClass innerClnoeClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ClnoeClass getInnerClnoeClass() {
        return innerClnoeClass;
    }

    public void setInnerClnoeClass(ClnoeClass innerClnoeClass) {
        this.innerClnoeClass = innerClnoeClass;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Object的clone方法只是浅度克隆
        return super.clone();
    }

    @Override
    public String toString() {
        return "ClnoeClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", innerClnoeClass=" + innerClnoeClass +
                '}';
    }
}
