package com.qccr.learning

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by wulin on 16/11/24.
  */
object MethodDependency {
  val MAPPER = new ObjectMapper()

  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "arch_backend");
    System.setProperty("java.security.krb5.realm", "HADOOP.COM");
    System.setProperty("java.security.krb5.kdc", "192.168.5.171");

    var sparkConf: SparkConf = new SparkConf()
//    sparkConf.setAppName("sparkDemo").setMaster("local[1]")
    var sc: SparkContext = new SparkContext(sparkConf)
//    var lines: RDD[String] = sc.textFile("file:///Users/wulin/Documents/test/sparkdemo/method.text")
    var lines: RDD[String] = sc.textFile("/tmp/logstreaming0613/json/mytest/demo/in")


    val res = lines.map(x=>format(x)).filter(f=>f!=null)
      .map(x=>((x.getConsumer_app, x.getConsumer_method, x.getProvider_app, x.getProvider_method, x.getDay_time),1))
      .reduceByKey((x,y)=>x+y)

//    res.collect().foreach(println)
    res.repartition(1).saveAsTextFile("/tmp/logstreaming0613/json/mytest/demo/out")
  }

  def format(line: String): Data ={
    try{
      val data = MAPPER.readValue(line,classOf[Data])

      return data
    }catch {
      case e: Exception => {e.printStackTrace();return null}
    }
  }

}
