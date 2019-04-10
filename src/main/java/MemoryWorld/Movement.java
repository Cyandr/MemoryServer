package MemoryWorld;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;

public class Movement extends Concept
{

    public Movement()
    {

        Name="Spent";
    }

    String Name;


    @Override
    public OntResource ToResource(OntModel model) {
        OntResource resource = model.createResource(NetBase.getSubNodeURL(Movement.class))
                .addProperty(model.createProperty(GetURL() + "#Name"), Name)
                ;

        return resource;
    }
}
