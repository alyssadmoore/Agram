package com.AlyssaMoore;

import java.util.*;

// I'm leaving out the rule to deal players cards in 3s because that doesn't affect gameplay
// It's debatable whether 2s or 3s are left out of the deck, so I chose to leave out 2s.
// Unfortunately, the only garbage input I wasn't able to avoid during user card choice was an integer and then a String (eg. 2 of Hearts)

public class Agram {

    private static Scanner inputScanner = new Scanner(System.in);

    // highest current rank of a suit in play - initially set to a card not included in the deck
    private static Card currentCard = new Card(2, "Clubs");

    // Creating ArrayLists to hold computer's hand, user's hand, and deck
    private static ArrayList<Card> comp1Hand = new ArrayList<>();
    private static ArrayList<Card> comp2Hand = new ArrayList<>();
    private static ArrayList<Card> comp3Hand = new ArrayList<>();
    private static ArrayList<Card> comp4Hand = new ArrayList<>();
    private static ArrayList<Card> playerHand = new ArrayList<>();
    private static ArrayList<Card> deck = new ArrayList<>();

    // Creating boolean userWonRound to check who won the last round and
    // integer goAgain to control whether the game is playing or not
    private static String wonLastRound = "Player";
    private static int goAgain = 1;
    private static int numComps;

    public static void main(String[] args) {

        // Creating randomizer to simulate shuffling of cards
        Random randomizer = new Random();

        // Start of game, explaining rules only when the game starts the first time
        System.out.println("Welcome to Agram! Here is how to play:\nYou and the computer each get 6 cards. Since the computer always deals, you always start the game.\n" +
                "The 2s, face cards, and ace of spades are removed for a total of 35 cards in the deck. There are six rounds. Each round, the first player puts down any \n" +
                "card. The second player can only win the round if they put down a card of the same suit and a higher rank. If they put down a card of a higher rank but\n" +
                "different suit, they lose, as well as same suit but lower rank. The player who wins a round gets to go first the next round. The player who wins the last\n" +
                "round is the winner - no other round winnings dictate winner. No drawing of cards and no points.\n\nYou can play with 1-4 computers. How many opponents " +
                "would you like? Enter 1, 2, 3, or 4.");

        // Asking user to enter number of computer players with error handling
        try {
            numComps = inputScanner.nextInt();
            if (numComps > 4 || numComps < 1) {
                System.out.println("Please enter 1, 2, 3, or 4. You have two more tries.");
                try {
                    numComps = inputScanner.nextInt();
                    if (numComps > 4 || numComps < 1) {
                        System.out.println("Please enter 1, 2, 3, or 4. You have one more try.");
                        try {
                            numComps = inputScanner.nextInt();
                            if (numComps > 4 || numComps < 1) {
                                System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                                System.exit(0);
                            }
                        } catch (InputMismatchException ime5) {
                            System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                            System.exit(0);
                        }
                    }
                } catch (InputMismatchException ime4) {
                    System.out.println("Please enter 1, 2, 3, or 4. You have one more try.");
                    inputScanner.next();
                    try {
                        numComps = inputScanner.nextInt();
                        if (numComps > 4 || numComps < 1) {
                            System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                            System.exit(0);
                        }
                    } catch (InputMismatchException ime7) {
                        System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                        System.exit(0);
                    }
                }
            }
        } catch (InputMismatchException ime1) {
            System.out.println("Please enter 1, 2, 3, or 4. You have two more tries.");
            inputScanner.next();
            try {
                numComps = inputScanner.nextInt();
                if (numComps > 4 || numComps < 1) {
                    System.out.println("Please enter 1, 2, 3, or 4. You have one more try.");
                    try {
                        numComps = inputScanner.nextInt();
                        if (numComps > 4 || numComps < 1) {
                            System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                            System.exit(0);
                        }
                    } catch (InputMismatchException ime6) {
                        System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException ime2) {
                System.out.println("Please enter 1, 2, 3, or 4. You have one more try.");
                inputScanner.next();
                try {
                    numComps = inputScanner.nextInt();
                    if (numComps > 4 || numComps < 1) {
                        System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                        System.exit(0);
                    }
                } catch (InputMismatchException ime3) {
                    System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                    System.exit(0);
                }
            }
        }

        // While loop contains each whole game played
        while (goAgain == 1) {
            // A new deck is created each time the game starts up
            // Deck contents: face cards, 2s, and ace of spades removed, 1 = Ace, 35 total cards
            deck.add(new Card(1, "Clubs"));
            deck.add(new Card(3, "Clubs"));
            deck.add(new Card(4, "Clubs"));
            deck.add(new Card(5, "Clubs"));
            deck.add(new Card(6, "Clubs"));
            deck.add(new Card(7, "Clubs"));
            deck.add(new Card(8, "Clubs"));
            deck.add(new Card(9, "Clubs"));
            deck.add(new Card(10, "Clubs"));

            // (Ace of spades left out)
            deck.add(new Card(3, "Spades"));
            deck.add(new Card(4, "Spades"));
            deck.add(new Card(5, "Spades"));
            deck.add(new Card(6, "Spades"));
            deck.add(new Card(7, "Spades"));
            deck.add(new Card(8, "Spades"));
            deck.add(new Card(9, "Spades"));
            deck.add(new Card(10, "Spades"));

            deck.add(new Card(1, "Hearts"));
            deck.add(new Card(3, "Hearts"));
            deck.add(new Card(4, "Hearts"));
            deck.add(new Card(5, "Hearts"));
            deck.add(new Card(6, "Hearts"));
            deck.add(new Card(7, "Hearts"));
            deck.add(new Card(8, "Hearts"));
            deck.add(new Card(9, "Hearts"));
            deck.add(new Card(10, "Hearts"));

            deck.add(new Card(1, "Diamonds"));
            deck.add(new Card(3, "Diamonds"));
            deck.add(new Card(4, "Diamonds"));
            deck.add(new Card(5, "Diamonds"));
            deck.add(new Card(6, "Diamonds"));
            deck.add(new Card(7, "Diamonds"));
            deck.add(new Card(8, "Diamonds"));
            deck.add(new Card(9, "Diamonds"));
            deck.add(new Card(10, "Diamonds"));

            /* for loop adds 6 random cards from the deck to the computer
              and users' hands, removing each drawn card from the deck */
            for (int x = 0; x < 6; x++) {
                int cardNum = randomizer.nextInt(deck.size());
                comp1Hand.add(deck.get(cardNum));
                deck.remove(cardNum);
                cardNum = randomizer.nextInt(deck.size());
                playerHand.add(deck.get(cardNum));
                deck.remove(cardNum);
                if (numComps >= 2) {
                    cardNum = randomizer.nextInt(deck.size());
                    comp2Hand.add(deck.get(cardNum));
                    deck.remove(cardNum);
                }
                if (numComps >= 3) {
                    cardNum = randomizer.nextInt(deck.size());
                    comp3Hand.add(deck.get(cardNum));
                    deck.remove(cardNum);
                }
                if (numComps == 4) {
                    cardNum = randomizer.nextInt(deck.size());
                    comp4Hand.add(deck.get(cardNum));
                    deck.remove(cardNum);
                }
            }
            // for loop plays next turn depending on who won the last round, for a total of 6 rounds
            for (int y = 0; y < 6; y++) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Round " + (y + 1) + " of 6");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                nextTurn();
            }

            // End of game, winner is whoever won the last round. Asking user if they want to go again
            // Congratulatory message if the player wins, generic message if a computer wins
            if (wonLastRound.equals("Player")) {
                System.out.println("Congratulations! You're the final winner. Would you like to play again? 1 for yes or 2 for no.");
                try {
                    goAgain = inputScanner.nextInt();
                } catch (Exception exe1) {
                    System.out.println("Sorry, I didn't quite catch that. Would you like to play again? 1 for yes or 2 for no. One more try remaining.");
                    inputScanner.next();
                    try {
                        goAgain = inputScanner.nextInt();
                    } catch (Exception exe2) {
                        System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                        System.exit(0);
                    }
                }
            } else {
                try {
                    System.out.println(wonLastRound + " is the final winner! Would you like to play again? 1 for yes or 2 for no.");
                    goAgain = inputScanner.nextInt();
                } catch (Exception exe3) {
                    System.out.println("Sorry, I didn't quite catch that. Would you like to play again? 1 for yes or 2 for no. One more try remaining.");
                    inputScanner.next();
                    try {
                        goAgain = inputScanner.nextInt();
                    } catch (Exception exe4) {
                        System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                        System.exit(0);
                    }
                }
            }
        }
    }

    // nextTurn sets order of plays in a round - player, then computers in ascending order, then loop back around
    private static void nextTurn() {
        if (numComps == 1) {
            if (wonLastRound.equals("Player")) {
                System.out.println(wonLastRound + " starts this round.");
                userTurn();
                compTurn(comp1Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");

            } else {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp1Hand);
                userTurn();
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            }
        } else if (numComps == 2) {
            if (wonLastRound.equals("Player")) {
                System.out.println(wonLastRound + " starts this round.");
                userTurn();
                compTurn(comp1Hand);
                compTurn(comp2Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 1")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                userTurn();
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 2")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp2Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            }
        } else if (numComps == 3) {
            if (wonLastRound.equals("Player")) {
                System.out.println(wonLastRound + " starts this round.");
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 1")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                userTurn();
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 2")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 3")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp3Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            }
        } else if (numComps == 4) {
            if (wonLastRound.equals("Player")) {
                System.out.println("You won the last round! You start this round.");
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                System.out.println("Computer 4's turn. Thinking...");
                compTurn(comp4Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 1")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                System.out.println("Computer 4's turn. Thinking...");
                compTurn(comp4Hand);
                userTurn();
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 2")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                System.out.println("Computer 4's turn. Thinking...");
                compTurn(comp4Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 3")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp3Hand);
                System.out.println("Computer 4's turn. Thinking...");
                compTurn(comp4Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            } else if (wonLastRound.equals("Computer 4")) {
                System.out.println(wonLastRound + " starts this round. Thinking...");
                compTurn(comp4Hand);
                userTurn();
                System.out.println("Computer 1's turn. Thinking...");
                compTurn(comp1Hand);
                System.out.println("Computer 2's turn. Thinking...");
                compTurn(comp2Hand);
                System.out.println("Computer 3's turn. Thinking...");
                compTurn(comp3Hand);
                System.out.println(wonLastRound + " won this round!");
                // reset current card for the next turn
                currentCard = new Card(2, "Clubs");
            }
        }
    }

    // userTurn shows user their hand and lets them choose a card to play
    private static void userTurn() {
        System.out.println("Here are your cards:");
        for (Card c : playerHand) {
            System.out.println((playerHand.indexOf(c) + 1) + ": " + c.getRank() + " of " + c.getSuit());
        }
        System.out.print("\n");

        // Asking user to play a card
        System.out.println("What card do you want to play? Enter number in front of card name (eg. 1, 2, 3, etc.):");

        // This try tree represents every combination (8 total) of 2 possible errors with 3 possible tries
        // Possible exceptions: InputMismatchException (non-integer values) and
        // IndexOutOfBoundException (integers that don't work with the rest of the code)
        try {
            userChoice();
        } catch (IndexOutOfBoundsException ioobe1) {
            System.out.println("Please enter the index number in front of your desired card. You have two more tries.");
            try {
                userChoice();
            } catch (IndexOutOfBoundsException ioobe2) {
                System.out.println("Please enter the index number in front of your desired card. You only have one more try.");
                try {
                    userChoice();
                    // Once the user has failed a third time, type of error does not matter - no more chances given
                } catch (Exception ex3) {
                    System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                    System.exit(0);
                }
            } catch (InputMismatchException ime2) {
                System.out.println("Please enter the index number in front of your desired card. You only have one more try.");
                inputScanner.next();
                try {
                    userChoice();
                    // Once the user has failed a third time, type of error does not matter - no more chances given
                } catch (Exception ex3) {
                    System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                    System.exit(0);
                }
            }
        } catch (InputMismatchException ime1) {
            System.out.println("Please enter the index number in front of your desired card. You have two more tries.");
            inputScanner.next();
            try {
                userChoice();
            } catch (IndexOutOfBoundsException ioobe2) {
                System.out.println("Please enter the index number in front of your desired card. You only have one more try.");
                try {
                    userChoice();
                    // Once the user has failed a third time, type of error does not matter - no more chances given
                } catch (Exception ex3) {
                    System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                    System.exit(0);
                }
            } catch (InputMismatchException ime2) {
                System.out.println("Please enter the index number in front of your desired card. You only have one more try.");
                inputScanner.next();
                try {
                    userChoice();
                    // Once the user has failed a third time, type of error does not matter - no more chances given
                } catch (Exception ex3) {
                    System.out.println("Sorry, you've overstayed your welcome. Come again soon!");
                    System.exit(0);
                }
            }
        }
    }

    // Part of userTurn taken out for error handling
    private static void userChoice() {
        int choice;
        choice = inputScanner.nextInt();
        // Logic to see if the user's card trumps the current card
        // If the current card has a rank of 2, it is the first turn and the user's card is put down
        if (currentCard.getRank() == 2) {
            currentCard = playerHand.get(choice - 1);
            // If the user's card is the same suit and a higher rank than the current card, user wins this round
        } else {
            if (playerHand.get(choice - 1).getSuit().equals(currentCard.getSuit())) {
                if (playerHand.get(choice - 1).getRank() > currentCard.getRank()) {
                    currentCard = playerHand.get(choice - 1);
                    wonLastRound = "Player";
                }
            }
        }
        // Whatever card the user played, confirm choice and remove the card from user's hand
        System.out.println("You played a " + playerHand.get(choice - 1).getRank() + " of " + playerHand.get(choice - 1).getSuit() + "\n");
        playerHand.remove(choice - 1);
    }

    // Logic for how each computer chooses its card
    private static Card compTurn(ArrayList<Card> hand) {
        // lowestNum and highestNum for calculating lowest value and highest value cards
        int lowestNum = 11;
        int highestNum = 0;
        // compChoice must be initialized, but is set to an impossible event to either return false or be changed in program
        Card compChoice = new Card(2, "Clubs");
        // toRemove is an arrayList containing the card to be taken out of the computer's deck
        ArrayList<Card> toRemove = new ArrayList<>();
        // possibleChoices for when a computer has multiple cards that could trump the current card
        ArrayList<Card> possibleChoices = new ArrayList<>();

        // Logic for if the computer is the first to play
        // No matter the suit, play the card with the highest rank
        if (currentCard.getRank() == 2) {
            for (Card e : hand) {
                if (e.getRank() > highestNum) {
                    highestNum = e.getRank();
                    currentCard = e;
                    compChoice = e;
                }
            }
        }

        // Logic for if computer plays second (or later) and has a card that can beat the current card
        // for each card in the computer's hand, check if the suit is the same as the current card's suit
        for (Card c : hand) {
            // for each card with the same suit, check if the rank is higher than the current card's rank
            if (c.getSuit().equals(currentCard.getSuit())) {
                // if the card is the same suit and has a higher rank than the current card, add the card to the list
                // of possible cards, make it the current card for better comparisons, make it compChoice in case there
                // are no other cards that could trump current card, and wonLastRound set to NOT player
                if (c.getRank() >= currentCard.getRank()) {
                    possibleChoices.add(c);
                    compChoice = c;
                    currentCard = c;
                    wonLastRound = "";
                }
            }
        }

        // Logic for if computer plays second (or later) and does NOT have a
        // card that can beat the current card. For each card, check the rank
        if (!hand.contains(compChoice)) {
            for (Card d : hand) {
                // the card with the lowest number is played
                if (d.getRank() < lowestNum) {
                    lowestNum = d.getRank();
                    compChoice = d;
                }
            }
        }

        // If there is more than one card of the same suit and a higher rank
        // than the current card, choose the card with the lowest rank
        if (possibleChoices.size() > 1) {
            int maxPossible = 11;
            for (Card e : possibleChoices) {
                if (e.getRank() < maxPossible) {
                    compChoice = e;
                    currentCard = e;
                }
            }
        }

        // If a computer won, check which by checking which hand contains the currentCard
        if (!wonLastRound.equals("Player")) {
            if (comp1Hand.contains(currentCard)) {
                wonLastRound = "Computer 1";
            } else if (comp2Hand.contains(currentCard)) {
                wonLastRound = "Computer 2";
            } else if (comp3Hand.contains(currentCard)) {
                wonLastRound = "Computer 3";
            } else if (comp4Hand.contains(currentCard)) {
                wonLastRound = "Computer 4";
            }
        }
        // No matter what card is chosen, tell the player which card it is and remove the card from the computer's hand
        // If the computer won, make the played card the new current card
        System.out.println("Computer played a " + compChoice.getRank() + " of " + compChoice.getSuit() + "\r\n");
        toRemove.add(compChoice);
        hand.removeAll(toRemove);
        return compChoice;
    }
}