package com.knoldus.model

case class File(name: String, location: String)

case class A(symbol: String, price: Int)
case class Trades(a:Array[A])