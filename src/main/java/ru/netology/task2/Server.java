package ru.netology.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8977;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    out.println("Adults only 18+. Enter your age:");
                    int age = 0;
                    while (true) {
                        String input = in.readLine();
                        try {
                            age = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            out.println("Неверный формат! Введите возраст цифрами!");
                            continue;
                        }
                        break;
                    }

                    if (age < 18) {
                        out.println("Sorry, you're too young for our content. Please leave our site - enter \"exit\"");
                        in.readLine();
                        out.printf("See you in %d years!", 18 - age);
                        continue;
                    }

                    out.println("To continue, enter your bank card details. Card number:");
                    long cardNumber = 0;
                    while (true) {
                        String input = in.readLine();
                        try {
                            cardNumber = Long.parseLong(input);
                        } catch (NumberFormatException e) {
                            out.println("Неверный формат! Введите номер карты только цифрами!");
                            continue;
                        }
                        break;
                    }

                    out.println("Be careful! There are scammers all around! So... What's the CCV on your bank card?");
                    int ccv = 0;
                    while (true) {
                        String input = in.readLine();
                        try {
                            ccv = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            out.println("Неверный формат! Введите CCV цифрами!");
                            continue;
                        }
                        break;
                    }

                    out.println("Perfectly! Soon the gates to the world of dreams will open! What's your name?");
                    String name = in.readLine();

                    out.println("The order is paid! Your personal link: popcorno.hub/" + name + ". Enter \"follow\"");

                    while (true) {
                        if ("follow".equalsIgnoreCase(in.readLine())) {
                            out.println("Hello, cowboy (.V.)");
                            break;
                        } else {
                            out.println("Enter exit or follow. And we will continue to have fun with your bank card =]");
                        }
                    }
                }
            }
        }
    }
}
