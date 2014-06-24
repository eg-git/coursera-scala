package recfun
import common._
import java.util

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    balance("(if (zero? x) max (/ 1 x))' is balanced".toList)

    countChange(4,List(1,2))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal (c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  var parenthesis = 0
  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty) true
    else {
      if(chars.head.toString.equals(")")) {
        parenthesis = parenthesis - 1
        if (parenthesis < 0) false
        else balance(chars.tail)
      }
      else {
        if(chars.head.toString.equals("(")) parenthesis = parenthesis + 1
        balance(chars.tail)
      }
    }
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}
