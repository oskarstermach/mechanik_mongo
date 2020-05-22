package pl.ostermach.carservice;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import pl.ostermach.carservice.models.Repair;

import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.out;
import static com.mongodb.client.model.Aggregates.sort;

public class ApplicationService extends AbstractService {

    public void addNewEntry() {
        Repair repair = Builder.buildRepair();
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);
        repairMongoCollection.insertOne(repair);
        System.out.println("Added new repair: " + repair.toString());
    }

    public void deleteEntry() {
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);
        System.out.println("Enter  repair id: ");
        long repairId = Long.parseLong(in.nextLine());
        Bson filter = Filters.eq("_id", repairId);
        Repair repair = repairMongoCollection.find(filter).first();

        if(repair != null){
            repairMongoCollection.deleteOne(filter);
            System.out.println("Deleted repair with id:" + repairId);
        }
    }

    public void findRepairById(){
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);
        System.out.println("Enter  repair id: ");
        long repairId = Long.parseLong(in.nextLine());
        Bson filter = Filters.eq("_id", repairId);
        Repair repair = repairMongoCollection.find(filter).first();

        if(repair != null){
            System.out.println(repair.toString());
        }
    }

    public void getEntries() {
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);
        repairMongoCollection.find().forEach((Block<? super Repair>) System.out::println);
    }

    public void updateEntry() {
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);
        System.out.println("Enter  repair id: ");
        long repairId = Long.parseLong(in.nextLine());
        Bson filter = Filters.eq("_id", repairId);
        Repair repair = repairMongoCollection.find(filter).first();

        if(repair != null){
            Repair newRepair = Builder.buildRepair();
            newRepair.setId(repairId);
            repairMongoCollection.deleteOne(filter);
            repairMongoCollection.insertOne(newRepair);
            System.out.println("Updated repair with id:" + repairId);
        }
    }

    public void showTopRepairs() {
        MongoCollection<Repair> repairMongoCollection = MongoDBInstance.getMongoDB().getCollection("Repair", Repair.class);

        repairMongoCollection.aggregate(Arrays.asList(
                sort(Sorts.descending("cost")),
                limit(3),
                out("largest_seven"))).toCollection();

        MongoCollection<Document> largestSeven = MongoDBInstance.getMongoDB().getCollection("largest_seven");
        System.out.println("Printing top 3 repairs: ");
        largestSeven.find().forEach((Block<? super Document>) System.out::println);
    }

}
