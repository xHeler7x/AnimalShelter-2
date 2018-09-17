package me.kwiatkowsky.details;

import com.mongodb.BasicDBObject;

public class Animal extends BasicDBObject {

    public Animal(String name, String type){
        this.append("name", name);
        this.append("type", type);
    }

    public String getName() {
        return this.get("name").toString();
    }

    public void setName(String name) {
        this.replace("name", name);
    }

    public String getType() {
        return this.get("type").toString();
    }

    public void setType(String type) {
        this.replace("type", type);
    }
}
