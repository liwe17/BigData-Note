package com.weiliai.chapter06.oop

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 快速入门
 */
object CatDemo {

  def main(args: Array[String]): Unit = {
    val cat = new Cat
    println(cat.name+cat.color)
  }


  /**
   * 定义一个类Cat
   * 一个class Cat对应的字节码文件只有一个Cat.class,默认是public
   *
   * 说明:
   * 1. 当我们声明了var name:String时,在底层对应了private name
   * 2. 同时会生成两个public方法
   *    name()<=>getter
   *    name_$eq()<=>setter
   *
   * 反编译结果:
   * public static class Cat{
   *    private String name = "";
   *    private int age;
   *    private String color;
   *
   *    public String name()
   *    {
   *    return this.name; }
   *    public void name_$eq(String x$1) { this.name = x$1; }
   *    public int age() { return this.age; }
   *    public void age_$eq(int x$1) { this.age = x$1; }
   *    public String color() { return this.color; }
   *    public void color_$eq(String x$1) { this.color = x$1;
   *    }
   * }
   */
  class Cat{
    //定义/声明是哪个属性
    var name:String = ""
    var age:Int = _ //表示给age默认值,如果Int默认0
    var color:String = _//默认值为null
  }


}
