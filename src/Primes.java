import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;


class Counter implements Runnable {

    static AtomicIntegerArray prime = new AtomicIntegerArray(Primes.target+1);
    static AtomicInteger index = new AtomicInteger(3);
    static AtomicLong sum = new AtomicLong(2);
    static AtomicInteger numPrimes = new AtomicInteger(1);

    @Override
    public void run() {
      while (index.get() < Primes.target) {
          int cur = index.getAndAdd(2);
          if (isPrime(cur)) {
              if (cur > Primes.target) break;
              prime.set(cur, 1);
              numPrimes.addAndGet(1);
              sum.addAndGet(cur);
          }
      }
    }

    // primality test sourced from GeeksForGeeks
    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i+2) == 0)
                return false;

        return true;
    }
}

public class Primes {
    final static int target = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(new Counter());
//        Thread thread2 = new Thread(new Counter());
//        Thread thread3 = new Thread(new Counter());
//        Thread thread4 = new Thread(new Counter());
//        Thread thread5 = new Thread(new Counter());
//        Thread thread6 = new Thread(new Counter());
//        Thread thread7 = new Thread(new Counter());
//        Thread thread8 = new Thread(new Counter());

        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        thread6.start();
//        thread7.start();
//        thread8.start();

        thread1.join();
//        thread2.join();
//        thread3.join();
//        thread4.join();
//        thread5.join();
//        thread6.join();
//        thread7.join();
//        thread8.join();

        Stack<Integer> stack = new Stack<>();

        for (int i = target - 1; i >= 0; i-=2)
        {
            if (stack.size() == 10) {
                break;
            }
            if (Counter.prime.get(i) == 1) {
                stack.push(i);
            }
        }
        long end = System.currentTimeMillis();

        try {
            File ob = new File("primes.txt");
            FileWriter writer = new FileWriter("primes.txt");
            writer.write("Time:" + String.valueOf((end-start)/1000.0) + " ");
            writer.write("Number of Primes:" +String.valueOf(Counter.numPrimes.get()) + " ");
            writer.write("Sum:" +String.valueOf(Counter.sum.get()) + "\n");

            writer.write("10 Largest: ");
            while (!stack.isEmpty()) {
                writer.write(stack.pop() + " ");
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }




}
