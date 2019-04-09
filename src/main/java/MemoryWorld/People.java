package MemoryWorld;


import com.sun.istack.internal.NotNull;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

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


    enum GENDER {
        FEMALE,
        MALE
    }

    String Name;
    float Weight;
    float Height;
    HashMap<String, String> ExtendsProperties = new HashMap<>();


}
