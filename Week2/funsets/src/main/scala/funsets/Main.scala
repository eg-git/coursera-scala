package funsets

object Main extends App {
  import FunSets._
  printSet(singletonSet(1))
  println(contains(singletonSet(1), 2))

  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  val s4 = singletonSet(4)
  val s5 = singletonSet(5)
  val s = union(union(union(union(s1,s2), s3), s4), s5)

  printSet(filter(singletonSet(1), x => x == (x + x)))
  println(forall(singletonSet(1), x => x == (x * x)))

  println("forall: " + forall(s, x => x == x ))

  println("exists: " + exists(s, x => x == (x * x)))

  printSet(s)
  printSet(map(s, x => x + 3))
}
