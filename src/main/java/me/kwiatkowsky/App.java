package me.kwiatkowsky;


import com.mongodb.BasicDBObject;
import com.mongodb.client.*;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import me.kwiatkowsky.details.Animal;
import me.kwiatkowsky.manage.CommandManager;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("animaldb");

        //TWORZENIE NOWEJ KOLEKCJI
        //database.createCollection("testCollection");

        MongoCollection<BasicDBObject> collection = database.getCollection("animaldb", BasicDBObject.class);

        /**
        BasicDBObject document = new BasicDBObject("x", 1);
        collection.insertOne(document);
        document.append("x", 2).append("y", 3);

        collection.replaceOne(Filters.eq("_id", document.get("_id")), document);
         **/

        //DODAWANIE DOC DO TABELII
        //BasicDBObject document = new BasicDBObject("x", 99).append("y", 0);
        //collection.insertOne(document);

        //USUWANIE DOC Z TABELII
        //collection.findOneAndDelete(document);
        //collection.deleteOne(new Document("_id", new ObjectId("5b9f9c4c2a26c122e4133d29")));

        //SZUKANIE DOC PO ID
        //collection.find(Filters.eq("_id", "5b9f9c4c2a26c122e4133d29"));

        //Animal animal = new Animal("Adam", "Dog");
        //collection.insertOne(animal);

        CommandManager commandManager = new CommandManager();


        //LISTA WSZYSTKICH ELEMENTOW DANEJ TABELII
        List<BasicDBObject> foundDoc = collection.find().into(new ArrayList<BasicDBObject>());

        //System.out.println(foundDoc.toString());
        while (true) {
            commandManager.onCommand(collection, 4);
            System.out.println(foundDoc.toString());
        }
    }
}
