package MemoryWorld;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.Resource;

public class MemoryObject  extends BaseModel
{

    String Name="物体";
    float Mass=200;
    float Volume=1000;
    float Energy=20;

    @Override
    public OntResource ToResource(OntModel model) {
        Resource resource = model.createResource(NetBase.getSubNodeURL(MemoryObject.class))
                .addProperty(model.createProperty(GetURL() + "#Name"), Name)
                .addLiteral(model.createProperty(GetURL() + "#Mass"), Mass)
                .addLiteral(model.createProperty(GetURL() + "#Volume"), Volume)
                .addLiteral(model.createProperty(GetURL() + "#Energy"), Energy);
      ;

        return resource;
    }

    enum CREATUEORNOT
    {
        LIVING,
        UNLIVING
    }



}
