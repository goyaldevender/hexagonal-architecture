package com.baeldung.adapter.console;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.baeldung.exception.CardHolderNotFoundException;
import com.baeldung.service.CardHolderService;
import com.baeldung.domain.CardHolder;
import com.google.inject.Inject;

public class CmdLineInterfaceImpl implements CmdLineInterface {

    private CardHolderService cardHolderService;

    @Inject
    public CmdLineInterfaceImpl(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    public void list(Scanner scanner) {
        System.out.println("All card holders registered so far");

        List<CardHolder> allCardHolders = cardHolderService.getAllCardHolders();

        allCardHolders.stream()
            .forEach(c -> {

                CmdLineUtils.printCardHolder(c);
                System.out.println("------------------------------------");
            });

    }

    public void register(Scanner scanner) {
        System.out.println("What is the first name ? ");
        String firstName = CmdLineUtils.readString(scanner);

        System.out.println("What is the last name ? ");
        String lastName = CmdLineUtils.readString(scanner);

        CardHolder unregistered = new CardHolder();
        unregistered.setFirstName(firstName.toUpperCase());
        unregistered.setLastName(lastName.toUpperCase());

        Random random = new Random();
        String creditCardNumber = String.format("%04d", random.nextInt(10000)) + " " +
                String.format("%04d", random.nextInt(10000)) + " " +
                String.format("%04d", random.nextInt(10000)) + " " +
                String.format("%04d", random.nextInt(10000));
        unregistered.setCreditCardNumber(creditCardNumber);

        CardHolder registered = cardHolderService.registerCardHolder(unregistered);

        System.out.println("New card holder registered successfully with the following : ");
        CmdLineUtils.printCardHolder(registered);

    }

    public void upgrade(Scanner scanner) {
        System.out.println("What is the card holder Id you want to upgrade? ");
        String cardHolderId = CmdLineUtils.readString(scanner);
        Optional<CardHolder> cardHolderWrapper = cardHolderService.findCardHolderById(Integer.valueOf(cardHolderId));

        if (cardHolderWrapper.isPresent()) {
            System.out.println("The Customer you want to upgrade is as following");
            CmdLineUtils.printCardHolder(cardHolderWrapper.get());
            System.out.println("Do you want to proceed ? Y(es)/N(o)");
            String confirm = CmdLineUtils.readString(scanner);

            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                try {
                    CardHolder customer = cardHolderService.upgradeCardHolder(cardHolderWrapper.get());
                    System.out.println("Customer upgrade successful with the following : ");
                    CmdLineUtils.printCardHolder(customer);
                } catch (CardHolderNotFoundException e) {
                    System.out.println("Customer upgrade failed");
                }

            }
        } else {
            System.out.println("Can't find the customer with supplied Id. Please try again ");
        }

    }

    public void downgrade(Scanner scanner) {
        System.out.println("What is the customer Id you want to downgrade? ");
        String customerId = CmdLineUtils.readString(scanner);
        Optional<CardHolder> customerWrapper = cardHolderService.findCardHolderById(Integer.valueOf(customerId));

        if (customerWrapper.isPresent()) {
            System.out.println("The Customer you want to downgrade is as following");
            CmdLineUtils.printCardHolder(customerWrapper.get());
            System.out.println("Do you want to proceed ? Y(es)/N(o)");
            String confirm = CmdLineUtils.readString(scanner);

            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                try {
                    CardHolder customer = cardHolderService.downgradeCardHolder(customerWrapper.get());
                    System.out.println("Card holder downgrade successful with the following : ");
                    CmdLineUtils.printCardHolder(customer);
                } catch (CardHolderNotFoundException e) {
                    System.out.println("Card holder downgrade failed");
                }

            }
        } else {
            System.out.println("Can't find the card holder with supplied Id. Please try again ");
        }

    }

    public void info() {
        CmdLineUtils.printMainmenu();
    }

}
