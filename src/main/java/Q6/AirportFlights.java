package Q6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AirportFlights {

    Queue<String> takeOffQueue = new LinkedList<>();
    Queue<String> landingQueue = new LinkedList<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayChoices();

            System.out.println("Enter command: ");
            String command = scanner.nextLine();

            if (command.equals("quit")) {
                System.out.println("Exiting...");
                break;
            }

            processCommand(command);

            displayQueues("\nTakeoff Queue", takeOffQueue);
            displayQueues("\nLanding Queue", landingQueue);
        }
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        String action = commandParts[0].toLowerCase();

        if (action.equals("takeoff")) {
            if (commandParts.length < 2) {
                System.out.println("\nFlight number is missing. Enter a valid command (e.g. takeoff Flight-100)");
            } else {
                takeOffQueue.add(commandParts[1]);
                System.out.println(commandParts[1] + " is added to the takeoff queue");
            }
        } else if (action.equals("land")) {
            if (commandParts.length < 2) {
                System.out.println("\nFlight number is missing. Enter a valid command (e.g. land Flight-200)");
            } else {
                landingQueue.add(commandParts[1]);
                System.out.println(commandParts[1] + " is added to the landing queue");
            }
        } else if (action.equals("next")) {
            if (!landingQueue.isEmpty()) {
                System.out.println("Processing " + landingQueue.remove() + " from landing queue.");
            } else if (!takeOffQueue.isEmpty()) {
                System.out.println("Processing " + takeOffQueue.remove() + " from takeoff queue.");
            } else {
                System.out.println("No flights in either queue to process.");
            }
        } else {
            System.out.println("Invalid command. Please enter a valid command.");
        }

    }

    public void displayChoices() {
        System.out.println("\n------Available Commands------");
        System.out.println("1. takeoff <flightCode> (e.g. takeoff Flight-100)");
        System.out.println("2. land <flightCode> (e.g. land Flight-200)");
        System.out.println("3. next");
        System.out.println("4. quit");
    }

    public void displayQueues(String queueName, Queue<String> queue) {
        System.out.print(queueName + ": ");
        if (queue.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println(queue);
        }
        
        if (!queue.isEmpty()) {
            for (String flight : queue) {
                System.out.println(" - " + flight);
            }
        }
    }

    public static void main(String[] args) {
        AirportFlights airportFlights = new AirportFlights();
        airportFlights.start();
    }

}