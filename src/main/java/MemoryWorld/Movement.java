package MemoryWorld;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public class Movement extends Concept
{

    public Movement()
    {

        Name="Spent";
    }

    String Name;


    @Override
    public Resource ToResource(Model model) {
        Resource resource = model.createResource(NetBase.getSubNodeURL(Movement.class))
                .addProperty(model.createProperty(GetURL() + "#Name"), Name)
                ;

        return resource;
    }
}
