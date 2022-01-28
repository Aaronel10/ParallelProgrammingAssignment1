Aaronel Haro
Assignment 1 COP 4520

Summary:
My approach was to use Atomic variables to ensure that no values were misread or assigned incorrectly.
By using getAndAdd in java it let me get a current number for this thread and increment it in 1 atomic step.
I then used this in conjuction with a primality testing method I got from geeksforGeeks. Because addition is commutative,
the order in which I process these numbers doesnt matter as long as they're counted once, which is guarenteed by the atomic
property of my variables.


Instructions:
compile Primes.java with javac command
run it with the java command
