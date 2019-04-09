package MemoryWorld;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.util.Date;

public class Time extends BaseModel {
    public Time()
    {
        m_date=new Date();
    }

    Date m_date;

    @Override
    public Resource ToResource(Model model) {
        Resource resource = model.createResource(NetBase.getSubNodeURL(Time.class))
                .addProperty(model.createProperty(GetURL() + "#Date"), m_date.toString())
                ;


        return resource;
    }
}
