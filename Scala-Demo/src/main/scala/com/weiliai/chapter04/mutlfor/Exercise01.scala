package com.weiliai.chapter04.mutlfor

import scala.io.StdIn

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 嵌套循环
 */
object Exercise01 {

  def main(args:Array[String]):Unit={
    /**
     * 1.统计三个班成绩情况,每个班有5名同学,求各个班的平均分和所有班级的平均分[学生的成绩从键盘输入]
     * 2. 统计三个班及格人数,每个班5名同学
     * 3. 打印99乘法表
     */
    val classNum=3 //班级数
    val stuNum=5 //学生数
    var score=0.0 //分数
    var classScore=0.0 //班级总分
    var totalScore=0.0 //年级总分
    for(i <- 1 to classNum){
      for(j<- 1 to stuNum){
        printf("请输入第%d个班,第%d个同学的成绩",i,j)
        score=StdIn.readDouble()
        classScore+=score
      }
      printf("第%d个班的平均分为%.2f",classNum,classScore/stuNum)
      totalScore+=classScore;
      classScore=0
    }
    println(s"所有班级的平均分为${totalScore/(classNum*stuNum)}")


  }


}
