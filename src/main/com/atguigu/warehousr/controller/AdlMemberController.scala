package com.atguigu.warehousr.controller

import com.atguigu.warehousr.service.AdlMemberService
import com.atguigu.warehousr.util._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AdlMemberController {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("adl_member_controller")//.setMaster("local[*]")
    val sparkSession = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
    val ssc = sparkSession.sparkContext
    HiveUtil.openDynamicPartition(sparkSession) //开启动态分区
    HiveUtil.openCompression(sparkSession) //开启压缩
    HiveUtil.useSnappyCompression(sparkSession) //使用snappy压缩
    AdlMemberService.queryDetailApi(sparkSession)
//    AdlMemberService.queryDetailSql(sparkSession)
  }
}
