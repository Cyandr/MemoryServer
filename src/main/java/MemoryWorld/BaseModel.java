package MemoryWorld;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;

public abstract class BaseModel {

    public static String BaseURL = "http://com.cyandr.robot//";


    public abstract OntResource ToResource(OntModel model);

    public String GetURL() {
        String className = this.getClass().getName();
        return BaseURL + "." + className;
    }

    public OntClass RegisterClass(OntModel model) {

        String className = this.getClass().getName();
        return model.createClass(BaseURL + className);
    }

    @Override
    public String toString() {
        return BaseURL + ID;
    }

    String uRL;
    String ID;


}
