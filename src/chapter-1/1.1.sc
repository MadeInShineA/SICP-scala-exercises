import scala.annotation.tailrec
// Exercise 1.1
// This is exercises is specific to lisp so I will just comment my solution

/*
10 Evaluates to 10

(+ 5 3 4) evaluates to 12

(- 9 1) evaluates to 8

(/ 6 2) evaluates to 3

(+ (* 2 4) ( - 4 6)) evaluates to (+ 8 -2) => 6

(define a 3)

(define b (+ a 1)

( + a b (* a b)) evaluates to ( + a b 12) => 19

(= a b) evaluates to false

(if (and (> b a) (< b (* a b)))
  b
  a)
evaluates to b => 4

(cond ((= a 4) 6)
      ((= b 4) (+ 6 7 a))
      (else 25))
evaluates to (+ 6 7 a) => 16

(+ 2 (if (> b a) b a)) evaluates to (+ 2 b) => 6

(* (cond ((> a b) a)
         ((< a b) b)
         (else -1))
   (+ a 1))
evaluates to (* b (+ a 1) => (* b 4) => 16
 */


// Exercise 1.2
// This is exercises is specific to lisp so I will just comment my solution

/*
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5))))) (* 3 (- 6 2) (- 2 7))
 */


// Exercise 1.3

def square(x: Double) = x * x

@tailrec
def sumRec(numbers: Seq[Double], depth: Int = 0, current_sum: Double = 0): Double = {
  if (depth >= numbers.length)
    current_sum
  else
    sumRec(numbers, depth + 1, current_sum + numbers(depth))
}

// This function computes the sum of all the double numbers passed as argument
def sum(numbers: Double*): Double = {
  if (numbers.nonEmpty)
    sumRec(numbers)
  else throw new IllegalArgumentException("The numbers parameter is an empty array")
}

def ex_1_3(a: Double, b: Double, c: Double): Double= {
  if(c < a && c < b) sum(square(a), square(b))
  else if (b < a && b < c) sum(square(a), square(c))
  else if (a < b && a < c) sum(square(b), square(c))

  // If the 2 smallest numbers or the 3 numbers are the same, return the sum of the 3 numbers squared
  else sum(square(a), square(b), square(c))
}

ex_1_3(5.0, 5.0, 3.0)
ex_1_3(5.0, 3.0, 3.0)
ex_1_3(5.0, 3.0, 1.0)


// Exercise 1.4
// This is exercises is specific to lisp so I will just comment my solution

/*
(define (a-plus-abs-b a b)
    ((if ( > b 0) + -) a b))

This procedure takes 2 numbers a and b as arguments
It will then check if b is greater then 0
    if b > 0 then it will just add it to a
    if b < 0 it will add it's negation (so - -b which is just +b) to a
So in the end it will just evaluate a + |b|
 */


// Exercise 1.5
// This is exercises is specific to lisp so I will just comment my solution

/*
(define (p) (p))

(define (test x y)
    (if (= x 0)
    0
    y))

(test 0 (p))

If the interpreter is using applicative order evaluation:
    It will loop indefinitely, since p is an endless recursive function

If the interpreter is using normal-order evaluation:
    It will evaluate to 0 since p is never used, it will never get evaluated,
    therefor never loop endlessly
 */


// Exercise 1.6

/*
(define (new-if predicate
                then-clause
                else-clause)
  (cond (predicate then-clause)
        (else else-clause)))

Because of how the interpreter works, the new-if method will always evaluate it's else-clause,
which would result here in an infinite loops.
The normal if on the other hand only evaluates it's else-clause if it's needed,
Solving the infinite loop problem
 */


// Exercise 1.7
// This is exercises is specific to lisp so I will just comment my solution


/*
(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.001))

For small numbers:
    The square of a small number is an even smaller number.
    Making it possible for our function to stop earlier than expected.

For big numbers
    The square of a big number, the newton's method will be very slow,
    since it only improves the guess gradually
 */

// Exercise 1.8

/*
x³ = n_0
x³ - n_0 = 0

x_n+1 = x_n - (x_n)³ - n_0) / 3 * x_n²
 */

def absolute(value: Double): Double = {
    if (value > 0) value else -value
}

def computeEpsilon(lastX: Double, currentX: Double): Double = {
    absolute(currentX - lastX)
}

def isGoodEnough(epsilon: Double): Boolean = {
    if(epsilon < 0.0001) true else false
}

def improve(lastX: Double, cubicRootTarget: Double): Double = {
    lastX - ((lastX * lastX * lastX) - cubicRootTarget) / (3 * lastX * lastX)
}

@tailrec
def dirtyCubicRoot(x: Double, lastX: Double, squareRootTarget: Double) : Double = {
    if (isGoodEnough(computeEpsilon(x,lastX))) x else dirtyCubicRoot(improve(x, squareRootTarget), x, squareRootTarget)
}

def cubicRoot(x: Double): Double = {
    dirtyCubicRoot(x, 100, x)
}





