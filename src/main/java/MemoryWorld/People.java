package MemoryWorld;


import JcSegEngine.PieceType;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.util.HashMap;



public class People extends BaseModel {
    public People() {
        Name = "严新辉";
        gender = GENDER.FEMALE;
        Height = 170;
        Weight = 62;
    }
    public People(String name,GENDER gender,float height,float weight) {
        Name = name;
        gender = gender;
        Height = height;
        Weight = weight;
    }
    GENDER gender=GENDER.FEMALE;

    @Override
    public Resource ToResource(Model model) {

        Resource resource = model.createResource(NetBase.getSubNodeURL(People.class))
                .addProperty(model.createProperty(GetURL() + "#Name"), Name)
                .addProperty(model.createProperty(GetURL() + "#GENDER"), gender.toString())
                .addLiteral(model.createProperty(GetURL() + "#Weight"), Weight)
                .addLiteral(model.createProperty(GetURL() + "#Height"), Height);
        ExtendsProperties.forEach((String key,String value)->{
            resource.addProperty(model.createProperty(GetURL() + "#"+key),value);
        });

        return resource;
    }

    @Override
    public PieceType GetPieceType() {
        return PieceType.People;
    }


    public enum GENDER {
        FEMALE,
        MALE
    }

    String Name;
    float Weight;
    float Height;
    HashMap<String, String> ExtendsProperties = new HashMap<>();


}
