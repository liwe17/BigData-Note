package com.weiliai.chapter03.arithoper

object Exercise01 {

  /**
   *假如还有97天放假，问：xx个星期零xx天
   * 1.搞清楚需求(读题)
   * 2.思路分析
   * (1) 变量保存97
   * (2) 使用/7 得到几个星期
   * (3) 使用%7 得到x天
   * 3.代码实现
   * 定义一个变量保存华氏温度，华氏温度转换摄氏温度的公式为：5/9*(华氏温度-100),请求出华氏温度对应的摄氏温度。[测试：232.5]
   */
  def main(args: Array[String]): Unit = {

    val days = 97
    printf("统计结果是 %d个星期零%d天",days/7,days%7)

    val hs = 232.5
    val ss = 5.0/9*(hs-100)
    println("对应的摄氏温度"+ss.formatted("%.2f"))

  }

}
