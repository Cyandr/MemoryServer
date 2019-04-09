package OntActivity;

import MemoryWorld.*;
import MemoryWorld.MemoryObject;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.HashMap;

import static MemoryWorld.NetBase.*;

public class ConsumeActivity extends OntActivity {





    People Who;
    Movement CostOrMake;
    Currency HowMuch;
    Location Where;
    Time When;
    MemoryObject What;


    public ConsumeActivity(People people, Movement movement, Currency currency, Location location, Time time, MemoryObject what) {
        Who = people;
        CostOrMake = movement;
        HowMuch = currency;
        Where = location;
        When = time;
        What = what;

    }

    HashMap<String, ObjectProperty> RELS = new HashMap<>();

    void initRels(OntModel model) {

        String strCLss = getClassURL(ConsumeActivity.class);
        RELS.put("who", model.createObjectProperty(strCLss + ".who"));
        RELS.put("movement", model.createObjectProperty(strCLss + ".movement"));
        RELS.put("howmuch", model.createObjectProperty(strCLss + ".howmuch"));
        RELS.put("where", model.createObjectProperty(strCLss + ".where"));

        RELS.put("when", model.createObjectProperty(strCLss + ".when"));

        RELS.put("object", model.createObjectProperty(strCLss + ".object"));

    }


    public OntModel generateRdfModel() {

        try {
            OntModel model = ModelFactory.createOntologyModel();
            initRels(model);
            OntClass consumeClass = model.createClass(getClassURL(ConsumeActivity.class));
            // create the resource
            Individual instance = model.createIndividual(getModelURL(ConsumeActivity.class), consumeClass);

            Individual instanceWho = model.createIndividual(getSubNodeURL(People.class), Who.ToResource(model));

            model.add(instance, RELS.get("who"), instanceWho);

            Individual instanceMove = model.createIndividual(getSubNodeURL(Movement.class), CostOrMake.ToResource(model));

            model.add(instance, RELS.get("movement"), instanceMove);

            Individual instanceHowmuch = model.createIndividual(getSubNodeURL(Currency.class), HowMuch.ToResource(model));

            model.add(instance, RELS.get("howmuch"), instanceHowmuch);

            Individual instanceWhere = model.createIndividual(getSubNodeURL(Location.class), Where.ToResource(model));

            model.add(instance, RELS.get("where"), instanceWhere);

            Individual instanceWhen = model.createIndividual(getSubNodeURL(Time.class), When.ToResource(model));

            model.add(instance, RELS.get("when"), instanceWhen);

            Individual instanceWhat = model.createIndividual(getSubNodeURL(MemoryObject.class), What.ToResource(model));

            model.add(instance, RELS.get("object"), instanceWhat);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
