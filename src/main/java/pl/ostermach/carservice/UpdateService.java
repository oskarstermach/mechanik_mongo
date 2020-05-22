package pl.ostermach.carservice;


public class UpdateService extends AbstractService{

    public void updateSelection() {
        System.out.println("Select option");

    }

    private String readSearchValue() {
        System.out.println("Insert search value for desired field");
        return in.nextLine();
    }

    public void findAndUpdate(String fieldName, String value) {
    }
}
