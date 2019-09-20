package entry;

import exception.*;
import model.Apartment;
import model.PreSuite;
import model.Property;
import model.RentalSys;
import model.utilities.DateTime;
import model.utilities.RentalStatus;
import java.util.Date;
import java.util.Scanner;

public class MainConsole {

    RentalSys rentalSys;

    public static void main(String[] args) {
        new MainConsole().action();
    }

    public MainConsole() {
        this.rentalSys = new RentalSys();
    }

    private void showMenu() {

        String menu_root =
                "****************************" + "\n" +
                        "*Please select:            *" + "\n" +
                        "*[1] Add New Property      *" + "\n" +
                        "*[2] Rent a Property       *" + "\n" +
                        "*[3] Return a Property     *" + "\n" +
                        "*[4] Perform Maintenance   *" + "\n" +
                        "*[5] Finish Maintenance    *" + "\n" +
                        "*[6] Show All Properties   *" + "\n" +
                        "*[7] Exit System           *" + "\n" +
                        "****************************" + "\n";
        System.out.print(menu_root);
    }

    private void action() {
        while (true) {
            showMenu();
            String selection = input("Please input your choice:");
            switch (selection) {
                case "1":
                    addProperty();
                    break;
                case "2":
                    rentProperty();
                    break;
                case "3":
                    returnProperty();
                    break;
                case "4":
                    performMaintenance();
                    break;
                case "5":
                    finishMaintenance();
                    break;
                case "6":
                    rentalSys.displayAll();
                    break;
                case "7":
                    rentalSys.exit();
                    break;
                default:
                    System.out.println("Please select from menu!");
                    break;
            }
        }
    }

    private void addProperty() {
        String selection = input(
                "Please enter\n" +
                        "[A] to add apartment\n" +
                        "[S] to add premium suite\n");
        Property p = null;
        int stNum;
        int roomNum;
        String stName;
        String suburb;
        switch (selection.toUpperCase()) {
            case "A":
                stNum = inputNum("Please input Street Num:");
                stName = input("Please input Street Name:");
                suburb = input("Please input Suburb:");
                roomNum = inputNum("Please input Room Num:");
                try {
                    p = new Apartment(stNum, stName, suburb, roomNum);
                    rentalSys.addNewP(p);
//                    System.out.println(p.toString());
                } catch (RoomNumberException e) {
                    System.out.println("Num should be between 1 and 3!");
                }
                break;
            case "S":
                stNum = inputNum("Please input Street Num:");
                stName = input("Please input Street Name:");
                suburb = input("Please input Suburb:");
                Date date = inputDate("Please input last Maintenance Day (dd/MM/yyyy):");
                p = new PreSuite(stNum, stName, suburb, date);
                rentalSys.addNewP(p);
//                System.out.println(p.toString());
                break;

            default:
                System.out.println("Please select 'A' or 'S'!");
                break;
        }
        if (p != null) {
            System.out.println("Successfully Added! Info as following:");
            System.out.println(p.showInfo());
        }
    }


    private void finishMaintenance() {
//        int stNum = inputNum("Please input street num:");
//        String stName = input("Please input street name:");
//        String suburb = input("Please input suburb:");
        Property p = checkP(RentalStatus.UNDER_MAINTAINANCE);
        if (p == null) {
            return;
        }
        Date date = inputDate("Please input finish date:");

        try {
            rentalSys.finishMaintenanceP(p, date);
        } catch (StatusUnavailableException e) {
            System.out.println("Not Under Maintenance!");
        } catch (DateNotValidException e) {
            System.out.println("Not a valid date!");
        }
    }

    private void performMaintenance() {
//        int stNum = inputNum("Please input street num:");
//        String stName = input("Please input street name:");
//        String suburb = input("Please input suburb:");
        Property p = checkP(RentalStatus.AVAILABLE);
        if (p == null) {
            return;
        }
        try {
            rentalSys.maintainP(p);
        } catch (StatusUnavailableException e) {
            System.out.println("Not available to perform this action!");
        }

    }

    private void returnProperty() {
//        int stNum = inputNum("Please input street num:");
//        String stName = input("Please input street name:");
//        String suburb = input("Please input suburb:");
        Property p = checkP(RentalStatus.RENTED);
        if (p == null) {
            return;
        }
        Date returnDate = inputDate("Please input return date (dd/MM/yyyy) :");
        try {
            rentalSys.returnP(p, returnDate);
        } catch (StatusUnavailableException e) {
//            e.printStackTrace();
            System.out.println("Not available for renting!");
        } catch (RecordStatusException e) {
//            e.printStackTrace();
            System.out.println("Not record to finish with returning property!");
        }

    }


    private void rentProperty() {
        Property p = checkP(RentalStatus.AVAILABLE);
        if (p == null) {
            return;
        }
        Date start = inputDate("Please input start date (dd/MM/yyyy) :");
        Date end = inputDate("Please input end date (dd/MM/yyyy) :");
        String guest = input("Please input guest name:");
        try {
            rentalSys.rentP(p, guest, start, end);
        } catch (StatusUnavailableException e) {
            System.out.println("Not available for renting!");
        } catch (DateNotValidException e) {
            System.out.println("End date should be after start date!");
        }

        System.out.println("Successfully Rented! Info as following:");
        System.out.println(p.getRecords().get(0).showInfo());
    }

    /**
     * This method reads users input
     * It will keep trying until user typed in something
     *
     * @param msg the hint for asking what to input
     * @return the input String
     */
    private String input(String msg) {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        while (str == null || str.length() < 1) {
            System.out.print(msg);
            str = scanner.nextLine();
            if (str == null || str.length() < 1) {
                System.out.println("Input should not be empty!");
            }
        }
        return str;
    }

    private int inputNum(String msg) {
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        String intStr = scanner.nextLine();
        int k;
        try {
            k = Integer.parseInt(intStr);
        } catch (Exception e) {
            System.out.println("Please input a number!");
            k = inputNum(msg);
        }
        return k;
    }

    private Date inputDate(String msg) {
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        String intStr = scanner.nextLine();
        Date k;
        try {
            k = DateTime.toDate(intStr);
        } catch (Exception e) {
            System.out.println("Please input date in format dd/MM/yyyy !");
            k = inputDate(msg);
        }
        return k;
    }

    private Property checkP(RentalStatus rentalStatus) {
        String id = input("Please input the property ID:");
        Property p = null;
        try {
            p = rentalSys.findProperty(id);
        } catch (PropertyNotFoundException e) {
            System.out.println("Property Not Found!");
        }
        if (p == null) {
            return null;
        }
        if (!p.getStatus().equals(rentalStatus)) {
            System.out.println("Property Is Not Available for renting!");
            return null;
        }
        return p;
    }
}
