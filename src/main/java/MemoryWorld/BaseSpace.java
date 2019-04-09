package MemoryWorld;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public class BaseSpace extends BaseModel
{


    public   double X;
    public   double Y;
    public  double Z;


    @Override
    public Resource ToResource(Model model) {
        return null;
    }
}
