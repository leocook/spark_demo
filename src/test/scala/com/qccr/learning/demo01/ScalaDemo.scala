package com.qccr.learning.demo01

import org.junit.Test

/**
  * Created by wulin on 16/11/21.
  */
class ScalaDemo {

  /**
    * 集合的创建和遍历
    */
  @Test
  def makeCollect(): Unit ={
    val array = Array("aaa", "bbb", "ccc")
    //使用下标访问
    for(i <- (0 until array.length)){
      println(array(i))
    }

    val list = List("aaa", "bbb", "ccc")
    //直接遍历
    list.foreach(x => println(x))

    val map1 = Map("aaa"->1, "bbb"->2, "ccc"->3)
    map1.foreach(x => println((x._1+" -> "+x._2)))

    val map2 = Map(("aaa",1), ("bbb",2), ("ccc",3))
    map1.foreach(x => println((x._1+" -> "+x._2)))
  }

  /**
    * 元祖
    */
  @Test
  def makeTuple(): Unit ={
    //二元组
    val t1 = ("Jack",18)
    println(t1._1)

    val t2 = new Tuple2("Leo",18)
    println(t2)


    val t3 = ("jack", 18, "boy")
    println(t3._3)
  }

  /**
    * 过滤器测试
    */
  @Test
  def filterDemo(): Unit ={
    val list = List(1,2,3,4,5)

    val sum = list.reduce((x,y)=>x+y)

    println(sum)
  }

  /**
    * 映射操作
    * 一对一
    */
  @Test
  def mapDemo(): Unit ={
    val list = List(1,2,3)
    val list2 = list.map(x => x*2)

    println(list.sum)
    println(list2.sum)
  }

  /**
    * 映射操作
    * 一对多
    */
  @Test
  def flatMapDemo(): Unit ={
    val list = List("a1b","c1d","e1f")
    val res = list.flatMap(x=>x.split("1"))

    println(res)
  }

  /**
    * 聚合操作
    * 多对一
    */
  @Test
  def reduceDemo(): Unit ={
    val list = List(1,2,3)

    val sum = list.reduce((x,y) => x+y)
    println(sum)
  }
}
