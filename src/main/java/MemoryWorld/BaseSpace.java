package MemoryWorld;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;

public class BaseSpace extends BaseModel
{


    public   double X;
    public   double Y;
    public  double Z;


    @Override
    public OntResource ToResource(OntModel model) {
        return null;
    }
}
