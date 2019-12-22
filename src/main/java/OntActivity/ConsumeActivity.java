package OntActivity;

import JcSegEngine.PieceType;
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
        if (Type_RELS.size() > 0) return;
        Type_RELS.put(PieceType.People, PieceType.People.toString());
        Type_RELS.put(PieceType.Activity, PieceType.Activity.toString());
        Type_RELS.put(PieceType.Cost, PieceType.Cost.toString());
        Type_RELS.put(PieceType.Location, PieceType.Location.toString());
        Type_RELS.put(PieceType.Time,PieceType.Time.toString());
        Type_RELS.put(PieceType.RelatedObjects, PieceType.RelatedObjects.toString());
    }

    private static HashMap<String, ObjectProperty> RELS = new HashMap<>();
    public static HashMap<PieceType, String> Type_RELS = new HashMap<>();

    public static void initRels(OntModel model) {
        if (RELS.size() != 0) return;
        String strCLss = getClassURL(ConsumeActivity.class);
        RELS.put(PieceType.People.toString(), model.createObjectProperty(strCLss + "."+PieceType.People.toString()));
        RELS.put(PieceType.Activity.toString(), model.createObjectProperty(strCLss + "."+PieceType.Activity.toString()));
        RELS.put(PieceType.Cost.toString(), model.createObjectProperty(strCLss + "."+PieceType.Cost.toString()));
        RELS.put(PieceType.Location.toString(), model.createObjectProperty(strCLss + "."+PieceType.Location.toString()));

        RELS.put(PieceType.Time.toString(), model.createObjectProperty(strCLss + "."+PieceType.Time.toString()));

        RELS.put(PieceType.RelatedObjects.toString(), model.createObjectProperty(strCLss + "."+PieceType.RelatedObjects.toString()));

    }


    public OntModel generateRdfModel(OntModel model) {

        try {

            OntClass consumeClass = model.createClass(getClassURL(ConsumeActivity.class));
            // create the resource
            Individual instance = model.createIndividual(getModelURL(ConsumeActivity.class), consumeClass);

            Individual instanceWho = model.createIndividual(getSubNodeURL(People.class), Who.ToResource(model));

            model.add(instance, RELS.get(PieceType.People.toString()), instanceWho);

            Individual instanceMove = model.createIndividual(getSubNodeURL(Movement.class), CostOrMake.ToResource(model));

            model.add(instance, RELS.get(PieceType.Activity.toString()), instanceMove);

            Individual instanceHowmuch = model.createIndividual(getSubNodeURL(Currency.class), HowMuch.ToResource(model));

            model.add(instance, RELS.get(PieceType.Cost.toString()), instanceHowmuch);

            Individual instanceWhere = model.createIndividual(getSubNodeURL(Location.class), Where.ToResource(model));

            model.add(instance, RELS.get(PieceType.Location.toString()), instanceWhere);

            Individual instanceWhen = model.createIndividual(getSubNodeURL(Time.class), When.ToResource(model));

            model.add(instance, RELS.get(PieceType.Time.toString()), instanceWhen);

            Individual instanceWhat = model.createIndividual(getSubNodeURL(MemoryObject.class), What.ToResource(model));

            model.add(instance, RELS.get(PieceType.RelatedObjects.toString()), instanceWhat);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
