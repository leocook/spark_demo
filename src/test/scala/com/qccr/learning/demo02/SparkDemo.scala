package com.qccr.learning.demo02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
  * Created by wulin on 16/11/22.
  */
class SparkDemo {
  var sparkConf: SparkConf = new SparkConf()
  sparkConf.setAppName("sparkDemo").setMaster("local[1]")
  var sc: SparkContext = new SparkContext(sparkConf)
  //    var lines: RDD[String] = sc.textFile("file:///Users/wulin/Documents/test/spark_input.text")
  //    var lines: RDD[String] = sc.textFile("/tmp/logstreaming0613/json/mytest/spark_demo")
  var lines: RDD[String] = sc.parallelize(List("So far so good","Not bad"))

  /**
    * spark的map,所有的内容转大写。
    * 一对一
    */
  @Test
  def mapDemo(): Unit ={
    val upcase = lines.map(x => x.toUpperCase)
    upcase.collect().foreach(println)

    println(upcase.count())
  }

  /**
    * spark的flatMap,得到所有字符。
    * 一对多
    */
  @Test
  def flatMapDemo(): Unit ={
    val chars = lines.flatMap(x=>x.toCharArray)
    chars.collect().foreach(println)
  }

  /**
    * spark的filter,过滤满足条件的数据。
    *
    */
  @Test
  def filterDemo(): Unit ={
    val chars = lines.filter(f=>f.contains('t'))
    chars.collect().foreach(println)
  }

  /**
    * 以key为维度去聚合v
    */
  @Test
  def reduceByKeyDemo(): Unit ={
    val chars = lines.flatMap(x=>x.toCharArray)
      .map(x=>(x,1)) //('a',1)
      .reduceByKey((x,y) => x+y)

    chars.collect().foreach(println)
  }

  /**
    * join联合
    */
  @Test
  def joinDemo(): Unit ={
    val rdd1 = sc.parallelize(List("a"->1,"b"->2,"c"->3))
    val rdd2 = sc.parallelize(List("a"->2,"x"->2,"y"->3))

    val res = rdd1.join(rdd2)

    res.collect().foreach(println)
  }

  /**
    * intersection交集
    */
  @Test
  def intersectionDemo(): Unit ={
    val rdd1 = sc.parallelize(List("a","b","c"))
    val rdd2 = sc.parallelize(List("a","x","y"))

    val res = rdd1.intersection(rdd2)
    res.collect().foreach(println)
  }

  /**
    * rdd去重
    */
  @Test
  def distictDemo(): Unit ={
    val rdd = sc.parallelize(List("a","b","c","a","b")).distinct
    rdd.collect().foreach(println)
  }

  /**
    * repartition重分区
    */
  @Test
  def repartitionDemo(): Unit ={
    val rdd = sc.parallelize(List("a","b","c","a","b"))
    println(rdd.getNumPartitions)
    println(rdd.repartition(10).getNumPartitions)
  }

  /**
    * 聚合
    */
  @Test
  def reducePartition(): Unit ={
    val rdd = sc.parallelize(List(1,2,3,4,5))
    val res = rdd.reduce((x,y)=>x+y)
    println(res)
  }
}

