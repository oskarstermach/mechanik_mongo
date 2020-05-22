package pl.ostermach.carservice;

import java.io.IOException;
import java.util.Scanner;


public class AppManager {
    private ApplicationService applicationService;

    public AppManager(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public void controlApplicationFlow() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            showSelectionMenu();
            AppOperation appOperation = handleInput(in.nextLine());
            switch (appOperation) {
                case ADD:
                    applicationService.addNewEntry();
                    break;
                case DELETE:
                    applicationService.deleteEntry();
                    break;
                case UPDATE:
                    applicationService.updateEntry();
                    break;
                case GET:
                    applicationService.getEntries();
                    break;
                case TOP_REPAIRS:
                    applicationService.showTopRepairs();
                case FIND:
                    applicationService.findRepairById();
                    break;
                default:
                    System.out.println("Unrecognized operation!");
            }
        }
    }

    public AppOperation handleInput(String choice) {
        if (choice.matches("[0-9]{1}")) {
            return AppOperation.INVALID;
        } else {
            return choice.toUpperCase().matches("[ADUGTF]") ?
                    AppOperation.get(choice.toUpperCase()) : AppOperation.INVALID;
        }
    }

    private void showSelectionMenu() {
        System.out.println("----------------------------------------\n[A] New Repair\n[D] Delete Repair\n[U] Update Repair\n[G] Get Repairs\n[T] Find Top Repairs\n[F] Find repair by id\n----------------------------------------");
    }
}
