package pl.ostermach.carservice;

import pl.ostermach.carservice.models.Car;
import pl.ostermach.carservice.models.Client;
import pl.ostermach.carservice.models.Repair;

import java.util.Scanner;

public class Builder {
    private static final Scanner in = new Scanner(System.in);

    public static Car buildCar() {
        Car car = new Car();
        System.out.println("Building car..");
        car.setId((int) (Math.random() * 500) + 1);
        System.out.print("Enter car manufacturer:  ");
        car.setManufacturer(in.nextLine());
        System.out.print("Enter car model:  ");
        car.setModel(in.nextLine());
        return car;
    }

    public static Client buildClient() {
        Client client = new Client();
        System.out.print("Enter client first name:  ");
        client.setFirstName(in.nextLine());
        System.out.print("Enter client last name:  ");
        client.setFirstName(in.nextLine());
        client.setId((int) (Math.random() * 500) + 1);
        return client;
    }


    public static Repair buildRepair() {
        System.out.println("Building repair..");
        Repair repair = new Repair();
        repair.setCar(buildCar());
        repair.setClient(buildClient());
        repair.setId((int) (Math.random() * 500) + 1);
        System.out.print("Enter repair cost:  ");
        repair.setCost(Double.parseDouble(in.nextLine()));

        return repair;
    }




}
