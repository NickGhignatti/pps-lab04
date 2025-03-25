package u04lab
import u03.Sequences.*
import u03.Optionals.*
import Sequence.*
import u03.Optionals.Optional.Just

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a data structure T[A]
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  trait Traversable[T[_]]:
    def consume[A](t: T[A])(consumer: A => Unit): Unit

  given Traversable[Sequence] with
    override def consume[A](t: Sequence[A])(consumer: A => Unit): Unit = t match
      case Cons(h, t) => consumer(h); consume(t)(consumer)
      case _ => ()

  given Traversable[Optional] with
    override def consume[A](t: Optional[A])(consumer: A => Unit): Unit = t match
      case Just(a: A) => consumer(a)
      case _ => ()

  def log[A](a: A): Unit = println("The next element is: " + a)

  val s: Sequence[Int] = Cons(10, Cons(20, Nil()))

  @main def tryTraversable(): Unit =
  summon[Traversable[Sequence]].consume(s)(log)

  
