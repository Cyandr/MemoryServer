package MemoryWorld;


import JcSegEngine.PieceType;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.text.DateFormat;
import java.util.Date;

public class Time extends BaseModel {
    public Time()
    {
        m_date="";
    }
    public Time(String date)
    {
        m_date= date;
    }
    String m_date;

    @Override
    public Resource ToResource(Model model) {
        Resource resource = model.createResource(NetBase.getSubNodeURL(Time.class))
                .addProperty(model.createProperty(GetURL() + "#Date"), m_date.toString())
                ;


        return resource;
    }

    @Override
    public PieceType GetPieceType() {
        return PieceType.Time;
    }
}
