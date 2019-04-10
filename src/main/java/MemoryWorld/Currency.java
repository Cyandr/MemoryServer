package MemoryWorld;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.Resource;

import javax.swing.plaf.ColorUIResource;

//货币
public class Currency extends BaseModel {


    public Currency() {

        Value = 1.0f;
        currencyType = CurrencyType.YUAN;
    }

    @Override
    public OntResource ToResource(OntModel model) {

        OntClass cc = RegisterClass(model);

        Individual CurrenctIndi = model.createIndividual(NetBase.getSubNodeURL(Currency.class), cc);
        CurrenctIndi.addProperty(model.createProperty(GetURL() + "#currencyType"), currencyType.toString())
                .addLiteral(model.createProperty(GetURL() + "#Value"), Value);



        return CurrenctIndi;
    }

    enum CurrencyType {
        YUAN,
        DOLLAR,
        POUNDS

    }

    CurrencyType currencyType;
    float Value;

}
