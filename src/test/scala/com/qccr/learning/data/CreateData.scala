package com.qccr.learning.data

import java.io.FileWriter

import com.cloudera.com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test

import scala.util.Random

/**
  * Created by wulin on 16/11/23.
  */
class CreateData {
  val OM = new ObjectMapper()
  val out = new FileWriter("/Users/wulin/Documents/test/sparkdemo/method.text",true)

  @Test
  def create(): Unit ={
    val consumer_apps = List("membercenter","bmarketprod","supersearch")
    val consumer_methods = List("com.toowell.membercenter.facade.service.user.UserAddressFacade.setDefaultAddress"
      ,"com.toowell.membercenter.facade.service.merchant.UserMerchantFacade.login"
      ,"com.toowell.membercenter.facade.service.user.UserFacade.queryByUsername"
      ,"com.qccr.bmarketprod.facade.service.html.HtmlFacade.newUserCoupon"
      ,"com.qccr.bmarketprod.facade.service.html.HtmlFacade.mixUpPromotion"
      ,"com.qccr.bmarketprod.facade.service.activity.ActivityFacade.getCategoryList"
      ,"com.qccr.supersearch.facade.SearchFacade.search"
      ,"com.qccr.supersearch.facade.HotDictFacade.query"
      ,"com.qccr.supersearch.facade.SearchFacade.suggest")
    val provider_apps = List("bmerchantprod","bgoodsprod","borderprod")
    val provider_methods = List("com.qccr.bmerchantprod.facade.user.UserReceiptFacade.insertReceipt"
      ,"com.qccr.bmerchantprod.facade.member.MemberFacade.sendAuthCode"
      ,"com.qccr.bmerchantprod.facade.user.UserFacade.areaAll"
      ,"com.qccr.bgoodsprod.facade.search.GoodsQueryFacade.queryGoodsByFrontCategory"
      ,"com.qccr.bgoodsprod.facade.search.GoodsRecommendFacade.searchIndexRecommendGoods"
      ,"com.qccr.bgoodsprod.facade.app.GoodsQueryForAppFacade.findGoodDetail"
      ,"com.qccr.borderprod.facade.service.order.OrderFacade.orderPay"
      ,"com.qccr.borderprod.facade.service.invoice.InvoiceFacade.queryTraceListByInvoiceId"
      ,"com.qccr.borderprod.facade.service.activity.ActivityFacade.queryActivityInfoByOrderId")
    val day_time = List("2016-11-22","2016-11-23","2016-11-24")

    for(i <- (1 to 100000)){
      val data = new com.qccr.learning.Data(
        consumer_apps(getIntFlag(0,consumer_apps.length-1)),
        consumer_methods(getIntFlag(0,consumer_methods.length-1)),
        provider_apps(getIntFlag(0,provider_apps.length-1)),
        provider_methods(getIntFlag(0,provider_methods.length-1)),
        day_time(getIntFlag(0,day_time.length-1)))

      val line = OM.writeValueAsString(data)
//      println(line)
      out.write(line+"\n")
    }

    out.flush()
    out.close()
  }

  def getIntFlag(a:Int, b:Int): Int ={
    return Random.nextInt(b-a)+a
  }
}
