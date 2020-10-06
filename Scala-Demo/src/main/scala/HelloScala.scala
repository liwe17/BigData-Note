/**
 * scala的第一个程序helloWord
 * 1. object 表示一个伴生对象,这里我们可以简单理解就是一个对象
 * 2. HelloScala就是对象名字,它底层真正对应的类名是HelloScala$,对象是这个HelloScala$类型的一个静态对象MODULE$
 * 3. 当我们编写一个object HelloScala底层会生成两个.class文件,分别为HelloScala和HelloScala$
 * 4. scala在运行时,流程如下:
 * (1) 先从HelloScala的main开始执行
 * public static void main(String[] paramArrayOfString)
 * {
 *     HelloScala$.MODULE$.main(paramArrayOfString);
 * }
 * (2) 然后调用HelloScala$类的方法,HelloScala$.main,即执行了如下代码
 * public void main(String[] args)
 * {
 *     Predef..MODULE$.println("hello scala~~");
 * }
 */
object HelloScala {

  /**
   * 1. def表示是一个方法,是一个关键字
   * 2. main表示方法名字,表示程序入口
   * 3. args: Array[String] 表示形参,scala的特点是参数名在前,类型在后
   * 4. Array[String]表示类型数组
   * 5. Unit = 表示返回值为空
   */
  def main(args: Array[String]): Unit = {
    printf("hello scala...")
  }
}
