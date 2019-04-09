package MemoryWorld;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

//货币
public class Currency extends BaseModel
{


    public Currency()
    {

        Value=1.0f;
        currencyType=CurrencyType.YUAN;
    }

    @Override
    public Resource ToResource(Model model) {
        Resource resource = model.createResource(NetBase.getSubNodeURL(Currency.class))
                .addProperty(model.createProperty(GetURL() + "#currencyType"), currencyType.toString())
                .addLiteral(model.createProperty(GetURL() + "#Value"), Value)
                ;


        return resource;
    }

    enum CurrencyType
    {
        YUAN,
        DOLLAR,
        POUNDS

    }

    CurrencyType currencyType;
    float Value;

}
