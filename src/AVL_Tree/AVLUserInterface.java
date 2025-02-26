//Name: Cameron Bartlett
//Date: 02/25/2025
//Lab 5 - Data Structures - AVL Trees
//Description: AVL Tree user interface

package AVL_Tree;

import java.util.Scanner;

public class AVLUserInterface {

    // initialize AVL tree
    private AVLTree tree = new AVLTree();
    private AVLTree.AVLNode root = null;

    // scanner for user input
    Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {

        AVLUserInterface calculator = new AVLUserInterface(); // calculator for commands
        calculator.menu();
        calculator.closeInput();

    }

    // method for user interface
    public void menu() {

        boolean menuFlag = false;
        char menuInput = ' ';

        System.out.println("Welcome to the AVL tree user interface.");

        do {

            // terminal menu
            System.out.printf("Please type a command: %n" +
                              "   \"i\" to insert a node.%n" +
                              "   \"d\" to delete a node.%n" +
                              "   \"t\" to perform an inorder traversal.%n" +
                              "   \"o\" to perform a preorder traversal.%n" +
                              "   \"p\" to perform a postorder traversal.%n" +
                              "   \"q\" to quit the program.%n");
            
            // ensure a valid command is entered
            boolean validCommand = false;
            while (!validCommand) {

                if (input.hasNext()) {

                    menuInput = input.next().charAt(0);
                    validCommand = true;
                    
                } else {

                    System.err.println("Invalid input. Please enter a command.");
                    input.nextLine(); // clear buffer and reprompt

                }
            }
            
            // switch case for navigating program fuunctions
            switch (menuInput) {
                
                case 'i':
                    
                    insertNode();
                    break;
                
                case 'd':
                    
                    deleteNode();
                    break;

                case 't':
                    
                    displayInorder();
                    break;
                
                case 'o':
                    
                    displayPreorder();
                    break;
                
                case 'p':

                    displayPostorder();
                    break;

                case 'q':
                    
                    System.out.println("Goodbye.");
                    menuFlag = true;
                    break;
                
                default:
                    
                    System.err.println("Invalid command. Please enter a valid option.");
                    break;
            
            }
        
        } while (!menuFlag);
    }

    // method to insert a new node with exception handling
    public void insertNode() {

        boolean continueInserting = true; // flag to check if user wants to insert again

        while (continueInserting) {

            int value = 0;
            boolean validInput = false; // flag to check if input is valid

            while (!validInput) {

                System.out.print("Enter a number to insert: ");
                if (input.hasNextInt()) {

                    value = input.nextInt();
                    validInput = true;

                } else {

                    System.err.println("Invalid input. Please enter an integer.");
                    input.next(); // clears the invalid input

                }
            }

            root = tree.insert(root, value);
            System.out.println("Node " + value + " added.");

            // ask user if they want to insert another node, ensure valid response
            String choice;
            boolean validChoice = false; // flag to check if choice is valid
            while (!validChoice) {
                System.out.print("Would you like to insert another node? (y/n): ");
                choice = input.nextLine().trim();

                if (choice.equalsIgnoreCase("y")) {
                    
                    validChoice = true; // continue inserting
                
                } else if (choice.equalsIgnoreCase("n")) {
                    
                    validChoice = true;
                    continueInserting = false; // exit insertion loop
                
                } else {
                    
                    System.err.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                
                }
            }
        }
    }

    // method to insert a new node with exception handling
    public void deleteNode() {

        boolean continueDeleting = true; // flag to check if user wants to delete again

        while (continueDeleting) {

            int value = 0;
            boolean validInput = false; // flag to check if input is valid

            while (!validInput) {

                System.out.print("Enter a number to delete: ");
                if (input.hasNextInt()) {

                    value = input.nextInt();
                    validInput = true;

                } else {

                    System.err.println("Invalid input. Please enter an integer.");
                    input.next(); // clears the invalid input

                }
            }

            root = tree.delete(root, value);
            System.out.println("Node " + value + " removed (if it existed).");

            // ask user if they want to delete another node, ensure valid response
            String choice;
            boolean validChoice = false; // flag to check if choice is valid
            while (!validChoice) {
                System.out.print("Would you like to delete another node? (y/n): ");
                choice = input.nextLine().trim();

                if (choice.equalsIgnoreCase("y")) {
                    
                    validChoice = true; // continue inserting
                
                } else if (choice.equalsIgnoreCase("n")) {
                    
                    validChoice = true;
                    continueDeleting = false; // exit insertion loop
                
                } else {
                    
                    System.err.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                
                }
            }
        }
    }

    // method for inorder traversal
    public void displayInorder() {

        if (root == null) {

            System.err.println("Tree is empty.");
            return;

        }

        System.out.println("Inorder traversal: ");
        tree.inorderTraversal(root);
        System.out.println();

    }

    // method for preorder traversal
    public void displayPreorder() {

        if (root == null) {

            System.err.println("Tree is empty.");
            return;

        }

        System.out.println("Preorder traversal: ");
        tree.preorderTraversal(root);
        System.out.println();

    }

    // method for postorder traversal
    public void displayPostorder() {

        if (root == null) {

            System.err.println("Tree is empty.");
            return;

        }

        System.out.println("Postorder traversal: ");
        tree.postorderTraversal(root);
        System.out.println();

    }

    // scanner closing method
    public void closeInput() {input.close();}
}