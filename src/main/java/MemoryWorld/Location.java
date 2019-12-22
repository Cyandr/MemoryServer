package MemoryWorld;


import JcSegEngine.PieceType;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

//位置
public class Location extends BaseModel {

    public Location() {


    }

    public Location(String City) {
        Location = City;

    }

    @Override
    public Resource ToResource(Model model) {

        Resource resource = model.createResource(NetBase.getSubNodeURL(Location.class))
                .addProperty(model.createProperty(GetURL() + "#Location"), Location)
                .addProperty(model.createProperty(GetURL() + "#INSIDE_OR_OUTSIDE"), outer.toString())
                .addLiteral(model.createProperty(GetURL() + "#Latitude"), Latitude)
                .addLiteral(model.createProperty(GetURL() + "#Longtitude"), Longtitude);


        return resource;
    }

    @Override
    public PieceType GetPieceType() {
        return PieceType.Location;
    }

    enum INSIDE_OR_OUTSIDE {
        INSIDE,
        OUTSIDE

    }

    float Latitude = 10;
    float Longtitude = 4;
    String Location = "北京市";
    INSIDE_OR_OUTSIDE outer = INSIDE_OR_OUTSIDE.INSIDE;

}
