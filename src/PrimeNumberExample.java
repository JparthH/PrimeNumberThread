import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumberExample {
    private static int limit;
    private static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> a = new ArrayList<>();
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("Please pass valid number to print prime numbers");
        } else {
            try {
                limit = Integer.parseInt(args[0]);
                printInstructions();
            } catch (Exception e) {
                System.out.println("Error, that value is too large.");
                System.out.println("What value would you like to use?");
                limit = sc.nextInt();
                System.out.println("Parameter set");
                printInstructions();

                //e.printStackTrace();
            }
        }
    }

    private static void printInstructions() {
        System.out.println("Please choose an option");
        System.out.println("1) Single threaded");
        System.out.println("2) Multi threaded");
        System.out.println("3) Change parameter");
        System.out.println("4) Exit");
        int ans = sc.nextInt();
        printPrimes(ans);
    }

    private static void printPrimes(int ans) {
        if (ans == 1) {
            //get input till which prime number to be printed
            calculateAndDisplayPrime();
            printInstructions();
        } else if (ans == 2) {
            //Compute primes using multi-thread
            Prime prime=new Prime(limit);
            prime.run();
            printInstructions();
        } else if (ans == 3) {
            limit = sc.nextInt();
            System.out.println("Parameter set");
            printInstructions();
        } else if (ans == 4) {
            System.out.println("Good bye!");
        }else{
            System.out.println("Invalid options, Please Choose proper one!");
            printInstructions();

        }
    }

    private static void calculateAndDisplayPrime() {
        long startTime = System.nanoTime();
        if (limit > 1) {
            System.out.println("Computing Primes...");
            for (int number = 2; number <= limit; number++) {
                //print prime numbers only
                if (isPrime(number)) {
                    a.add(number);
                }
            }
        }
        final long duration = System.nanoTime() - startTime;
        System.out.println("That took "+ duration);
        System.out.println("Primes are " + a.toString().replace("[","").replace("]",""));
        a.clear();
    }

    /*
     * Prime number is not divisible by any number other than 1 and itself
     * @return true if number is prime
     */
    private static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false; //number is divisible so its not prime
            }
        }
        return true; //number is prime
    }

    static class Prime implements Runnable {
        int limit;

        public Prime(int limit) {
            this.limit = limit;
        }

        @Override
        public void run() {
            //get input till which prime number to be printed
            calculateAndDisplayPrime();
        }
    }
}