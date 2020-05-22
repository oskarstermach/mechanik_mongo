package pl.ostermach.carservice;


public class DeleteService extends AbstractService{

    public void deleteOptionSelector() {
        System.out.println("Select option");
    }

    private String readSearchValue() {
        System.out.println("Insert search value for desired field");
        return in.nextLine();
    }

}
