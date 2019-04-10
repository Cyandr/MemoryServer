package MemoryWorld;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.Resource;

import java.util.HashMap;



public class People extends BaseModel {
    public People() {
        Name = "严新辉";
        gender = GENDER.FEMALE;
        Height = 170;
        Weight = 62;
    }

    GENDER gender;

    @Override
    public OntResource ToResource(OntModel model) {

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


    enum GENDER {
        FEMALE,
        MALE
    }

    String Name;
    float Weight;
    float Height;
    HashMap<String, String> ExtendsProperties = new HashMap<>();


}
