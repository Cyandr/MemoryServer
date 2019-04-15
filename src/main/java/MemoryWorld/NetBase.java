package MemoryWorld;

import java.lang.reflect.Type;
import java.util.HashMap;

public class NetBase {
    private static String ProjectURL = "https://github.com/Cyandr/MemoryServer/";
    private static String BaseURI = ProjectURL;


    static HashMap<String, Integer> ResCount = new HashMap<>();

    static HashMap<String, String> URLTable = new HashMap<>();

    static HashMap<String, Integer> ModelCount = new HashMap<>();

    public static String getClassURL(Type type) {

        String clsStr = type.toString().replace("class ", "");
        return ProjectURL + clsStr;
    }

    public static String getModelURL(Type type) {

        String clsStr = type.toString().replace("class ", "");
        if (!URLTable.containsKey(clsStr)) {
            URLTable.put(clsStr, BaseURI + clsStr);
        }
        BaseURI = URLTable.get(clsStr);

        if (!ModelCount.containsKey(BaseURI)) {
            ModelCount.put(BaseURI, 1);
        } else
            ModelCount.replace(BaseURI, ModelCount.get(BaseURI) + 1);
        return BaseURI + "#" + ModelCount.get(BaseURI);

    }


    public static String getSubNodeURL(Type type) {

        String clsStr = type.toString().replace("class ", "");
        if (!ResCount.containsKey(clsStr)) {
            ResCount.put(clsStr, 1);
        } else
            ResCount.replace(clsStr, ResCount.get(clsStr) + 1);
        return BaseURI + "#" + ModelCount.get(BaseURI) + "." + clsStr;//+ ResCount.get(clsStr);
    }
}
