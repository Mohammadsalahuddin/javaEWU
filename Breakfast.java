public class Breakfast {
    private String name;        // Name of the breakfast place
    private String[] menu;      // Array to store menu items
    private int menuSize;       // Current size of the menu
    private static int totalOrders = 0;  // Static variable to track total orders

    // Constructor to initialize the Breakfast object with a given name and maximum menu size
    public Breakfast(String name, int maxMenuSize) {
        this.name = name;
        this.menu = new String[maxMenuSize];   // Initialize the menu array with the specified size
        this.menuSize = 0;                      // Initialize the menu size to zero
    }

    // Method to add a new item to the menu
    public void addToMenu(String item) {
        // Check if there is space available in the menu array
        if (menuSize < menu.length) {
            menu[menuSize] = item;   // Add the item to the next available position in the array
            menuSize++;              // Increment the menu size
        } else {
            System.out.println("Menu is full. Cannot add more items.");
        }
    }

    // Method to remove an item from the menu
    public void removeFromMenu(String item) {
        boolean found = false;
        // Iterate through the menu array to find the item
        for (int i = 0; i < menuSize; i++) {
            if (menu[i].equals(item)) {
                found = true;
                // Shift the subsequent items to fill the gap left by the removed item
                for (int j = i; j < menuSize - 1; j++) {
                    menu[j] = menu[j + 1];
                }
                // Set the last element to null and decrement the menu size
                menu[menuSize - 1] = null;
                menuSize--;
                break;
            }
        }
        if (!found) {
            System.out.println(item + " is not found in the menu.");
        }
    }

    // Method to display the current menu
    public void displayMenu() {
        System.out.println("Menu for " + name + ":");
        // Iterate through the menu array and print each item
        for (int i = 0; i < menuSize; i++) {
            System.out.println("- " + menu[i]);
        }
    }

    // Static method to place an order
    public static void placeOrder() {
        totalOrders++;
        System.out.println("New order placed. Total orders: " + totalOrders);
    }

    // Main method for testing the Breakfast class
    public static void main(String[] args) {
        // Create a new Breakfast object with the name "Morning Delight" and maximum menu size of 5
        Breakfast breakfast = new Breakfast("Morning Delight", 5);

        // Add some items to the menu
        breakfast.addToMenu("Eggs Benedict");
        breakfast.addToMenu("Pancakes");
        breakfast.addToMenu("Bacon and Eggs");
        breakfast.addToMenu("Solid Ham");
        breakfast.addToMenu("Fish Sandwich");

        // Display the initial menu
        breakfast.displayMenu();

        // Place an order (static method)
        Breakfast.placeOrder();

        // Remove an item from the menu
        breakfast.removeFromMenu("Pancakes");
        System.out.println("\nAfter removing Pancakes:");

        // Display the updated menu
        breakfast.displayMenu();
    }
}
