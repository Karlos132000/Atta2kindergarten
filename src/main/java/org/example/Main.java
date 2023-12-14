package org.example;

import org.example.DAO.ChildDao;
import org.example.DI.BeanFactory;
import org.example.db.DatabaseManager;
import org.example.models.Child;
import org.example.models.Group;
import org.example.services.ChildService;
import org.example.services.GroupService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String basePackage = "org.example";

        BeanFactory beanFactory = new BeanFactory();

        beanFactory.scanAndInstantiate(basePackage);

        ChildService childService = beanFactory.getBean(ChildService.class);
        GroupService groupService = beanFactory.getBean(GroupService.class);


        // Create tables
        DatabaseManager.createTables();

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("1. Add a Child");
            System.out.println("2. Remove a Child");
            System.out.println("3. Edit a Child");
            System.out.println("4. Add a Group");
            System.out.println("5. Remove a Group");
            System.out.println("6. Edit a Group");
            System.out.println("7. Display Information by Group");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a child
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();

                    // Create a new Child object without using resultSet
                    Child child = new Child(0, fullName, gender, age);
                    childService.addChild(child);
                    childService.addChild(child);
                    // No need to commit explicitly here; it will be done implicitly
                    break;

                case 2:
                    // Remove a child
                    System.out.print("Enter full name of the child to remove: ");
                    String childFullNameToRemove = scanner.nextLine();
                    childService.removeChild(childFullNameToRemove);
                    break;

                case 3:
                    // Edit a child
                    System.out.print("Enter full name of the child to edit: ");
                    String childFullNameToEdit = scanner.nextLine();
                    Child childToEdit = childService.getChildByFullName(childFullNameToEdit);
                    if (childToEdit != null) {
                        System.out.print("Enter new first name: ");
                        childToEdit.setFullName(scanner.nextLine());
                        System.out.print("Enter new last name: ");

                        childToEdit.setGender(scanner.nextLine());
                        System.out.print("Enter new age: ");
                        childToEdit.setAge(scanner.nextInt());
                    } else {
                        System.out.println("Child not found!");
                    }
                    break;

                case 4:
                    // Add a group
                    System.out.print("Enter group name: ");
                    String groupName = scanner.nextLine();
                    System.out.print("Enter group number: ");
                    int groupNumber = scanner.nextInt();

                    // Create a new Group object without using resultSet
                    Group group = new Group(0, groupName, groupNumber);
                    groupService.addGroup(group);
                    groupService.addGroup(group);
                    // No need to commit explicitly here; it will be done implicitly
                    break;

                case 5:
                    // Remove a group
                    System.out.print("Enter group name to remove: ");
                    String groupNameToRemove = scanner.nextLine();
                    groupService.removeGroup(groupNameToRemove);
                    break;

                case 6:
                    // Edit a group
                    System.out.print("Enter group name to edit: ");
                    String groupNameToEdit = scanner.nextLine();
                    Group groupToEdit = groupService.getGroupByName(groupNameToEdit);
                    if (groupToEdit != null) {
                        System.out.print("Enter new group name: ");
                        groupToEdit.setName(scanner.nextLine());
                        System.out.print("Enter new group number: ");
                        groupToEdit.setNumber(scanner.nextInt());
                    } else {
                        System.out.println("Group not found!");
                    }
                    break;

                // Inside the switch case or wherever you are using resultSet
                case 7:
                    // Display information by group
                    System.out.print("Enter group name to display information: ");
                    String groupNameToDisplay = scanner.nextLine();
                    Group groupToDisplay = groupService.getGroupByName(groupNameToDisplay);
                    if (groupToDisplay != null) {
                        System.out.println("Group Information:");
                        System.out.println("Name: " + groupToDisplay.getName());
                        System.out.println("Number: " + groupToDisplay.getNumber());
                        System.out.println("Children in the group:");

                        // Get the children from the database using ChildDao
                        ChildDao childDao = new ChildDao();
                        List<Child> childrenInGroup = childDao.getAllChildren();
                        for (Child c : childrenInGroup) {
                            System.out.println("  " + c.getFullName() + " - " + c.getAge() + " years old");
                        }
                    } else {
                        System.out.println("Group not found!");
                    }
                    break;


                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // Close the scanner
        scanner.close();
    }
}
