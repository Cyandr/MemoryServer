package JcSegEngine;

import java.util.HashMap;
import java.util.Map;

public class StruLag {


    static Map<String,WordType> MapStrType=new HashMap<>();
    static public void Init()
    {
        for (WordType type:WordType.values()) {
          MapStrType.put(type.name(),type);
        }

    }
//282
     static public   WordType findType(String strPara)
    {

            return MapStrType.get(strPara);

    }
}
