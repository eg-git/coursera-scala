package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("c", "a body nexus", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("e", "c body Android", 7)
    val d = new Tweet("d", "d body Galaxy", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
    val set6 = new Empty
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set6.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      println("filter: d on set5")
      assert(size(set5.filter(tw => tw.user == "d")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      println("filter: 20 on set5")
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("most retweeted: set5") {
    new TestSets {
      assert(set5.mostRetweeted.retweets === 20)
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      //assert(!trends.isEmpty)
      //assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  test("google list") {
    new TestSets {
      val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
      assert(size(TweetReader.allTweets filter(x => google.toList exists(y => x.text contains y ))) === 38)
    }
  }
}
