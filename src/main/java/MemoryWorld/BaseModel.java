package MemoryWorld;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public abstract class BaseModel {

    public static String BaseURL = "http://com.cyandr.robot//OntActivity";


    public abstract Resource ToResource(Model model);

    public   String GetURL()
    {
      String className=  this.getClass().getName();
        return BaseURL+"."+className;
    }

    @Override
    public String toString() {
        return BaseURL + ID;
    }

    String uRL;
    String ID;


}
