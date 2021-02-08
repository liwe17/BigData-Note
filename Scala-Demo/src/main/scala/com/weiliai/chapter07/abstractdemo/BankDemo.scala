package com.weiliai.chapter07.abstractdemo

/**
 * @Author: Doug Li
 * @Date 2021/1/31
 * @Describe: 抽象
 */
object BankDemo {

  def main(args:Array[String]):Unit={
    //开卡
    val account = new Account("gh0001",890.4,"11111")
    account.query("11111")
    account.withDraw("11111",100.0)
    account.query("11111")
  }

}

//编写一个Account类
class Account(inAccount:String,inBalance:Double,inPwd:String){
  /**
   * 属性:
   *  账号,余额,密码
   * 方法:
   *  查询,取款,存款
   */
  val accountNo: String = inAccount
  var balance: Double = inBalance
  var pwd: String = inPwd

  //查询
  def query(pwd:String):Unit={
    if(!this.pwd.equals(pwd)){
      println("密码错误")
      return
    }
    printf("账号为%s 当前余额为%.2f\n",this.accountNo,this.balance)
  }

  //取款
  def withDraw(pwd:String,money:Double):Any={
    if(!this.pwd.equals(pwd)){
      println("密码错误")
      return
    }
    //判断money是否合理
    if(this.balance<money){
      println("余额不足")
      return
    }
    this.balance -= money
    money
  }

}