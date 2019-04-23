package JcSegEngine;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SparqlGenerator {




    Query GenerateSparql(HashMap<WordType, String> words) {
        Set<WordType> wordSet = words.keySet();


        //谁 什么时间 什么地点  动作  物体  耗费
        for (WordType wordType:wordSet) {


        }

       /* NR___李幸
          ND___在
          NS___北京
          V___待
          I___了
          Q___3个月
          U___的
          N___时间
          W___，
          V___花费
          M___30000
          A___块
          N___人民币
          V___娶
          I___了
          A___个
          NZ___媳妇儿*/
        Query query =   QueryFactory.create("SELECT ?acts ?yuan " +
                "WHERE { " +
                "?peple  <http://com.cyandr.robot//OntActivity.MemoryWorld.People#Name>  \"Anod 05th\" ." +
                "?acts <https://github.com/Cyandr/MemoryServer/OntActivity.ConsumeActivity.who> ?peple ." +
                "?time <http://com.cyandr.robot//OntActivity.MemoryWorld.Time#Date> \"3\" ." +
                "?acts <https://github.com/Cyandr/MemoryServer/OntActivity.ConsumeActivity.when>  ?time ." +
                "?place <http://com.cyandr.robot//OntActivity.MemoryWorld.Location#Location>  \"北京\" ." +
                "?acts <https://github.com/Cyandr/MemoryServer/OntActivity.ConsumeActivity.where>  ?place ." +
                "?spent <http://com.cyandr.robot//OntActivity.MemoryWorld.Movement#Name> \"Spent\" ." +
                "?acts <https://github.com/Cyandr/MemoryServer/OntActivity.ConsumeActivity.movement>  ?spent ." +
                "?money <http://com.cyandr.robot//OntActivity.MemoryWorld.Currency#currencyType> \"YUAN\" ." +
                "?acts <https://github.com/Cyandr/MemoryServer/OntActivity.ConsumeActivity.howmuch> ?money ." +
                "?money <http://com.cyandr.robot//OntActivity.MemoryWorld.Currency#Value> ?yuan ." +
                "}");

        return query;
    }
}
