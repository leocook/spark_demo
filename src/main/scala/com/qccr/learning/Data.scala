package com.qccr.learning

import scala.beans.BeanProperty

/**
  * Created by wulin on 16/11/24.
  */
class Data extends Serializable{
  @BeanProperty var consumer_app: String = ""
  @BeanProperty var consumer_method: String = ""
  @BeanProperty var provider_app: String = ""
  @BeanProperty var provider_method: String = ""
  @BeanProperty var day_time: String = ""

  def this(consumer_app: String,
           consumer_method: String,
           provider_app: String,
           provider_method: String,
           day_time: String) {
    this()

    this.consumer_app = consumer_app
    this.consumer_method = consumer_method
    this.provider_app = provider_app
    this.provider_method = provider_method
    this.day_time = day_time
  }
}
