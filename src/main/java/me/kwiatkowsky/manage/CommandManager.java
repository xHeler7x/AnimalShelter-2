package me.kwiatkowsky.manage;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import me.kwiatkowsky.details.Animal;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandManager {

    private String[] cmd;

    public boolean onCommand(MongoCollection<BasicDBObject> collection, int sizeOfShelter){

        Scanner scanner = new Scanner(System.in);
        cmd = scanner.nextLine().split(" ");
        List<BasicDBObject> animals = collection.find().into(new ArrayList<BasicDBObject>());

        if (cmd[0].equalsIgnoreCase("status")){

            if (animals.size() == 0){
                errorMessage("Nie ma rzadnego zwierzaka w schronisku.");
                return false;
            }
            else if (animals.size() == sizeOfShelter){
                errorMessage("W schronisku nie ma miejsc.");
                return false;
            } else {
                errorMessage("W schronisku jest " + (sizeOfShelter - animals.size())
                        + " wolnych miejsc.");
            }
            errorMessage("");
            for (BasicDBObject a : animals){
                System.out.println(a.get("name"));
            }

            return true;
        }

        if (cmd[0].equalsIgnoreCase("add")){

            if (animals.size() == sizeOfShelter){
                errorMessage("W schronisku nie ma miejsc!");
                return false;
            }
            collection.insertOne(new Animal(cmd[1], cmd[2]));
            errorMessage("Dodano nowe zwierze do schroniska!");
            return true;
        }

        if (cmd[0].equalsIgnoreCase("remove")){

            collection.deleteOne(new Document("_id", new ObjectId(cmd[1])));
            errorMessage("Usunięto zwierzaka.");
            return true;
        }

        errorMessage("Nie ma takiej komendy!");
        return true;
    }

    private void errorMessage(String message){
        System.out.println("[AnimalShelter Manager] : " + message);
    }
}
