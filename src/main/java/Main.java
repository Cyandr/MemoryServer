import JcSegEngine.JcSegTest;
import OntActivity.ConsumeActivity;
import OntActivity.OntActivity;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.tdb.TDBFactory;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

public class Main {


    static String directory = "MyDatabases/Dataset1";
    public static  void InitConsumeActvity()
    {

        // Make a TDB-backed dataset

        makeDir(directory);
        Dataset dataset = TDBFactory.createDataset(directory);

        List<ConsumeActivity> activities = OntActivity.bornData();
        OntModel ontmodel = ModelFactory.createOntologyModel();
        ConsumeActivity.initRels(ontmodel);
        dataset.begin(ReadWrite.WRITE);int i=0;
        for (ConsumeActivity consumeActivity : activities) {
            consumeActivity.generateRdfModel(ontmodel);
        }
        dataset.addNamedModel("http://cyandr.test.com.mymemory", ontmodel);
        dataset.commit();
        dataset.end();

    }
    public static void main(String[] args) {








        // ConsumeActivity consumeActivity = new ConsumeActivity(new People(), new Movement(), new Currency(), new Location(), new Time(), new MemoryObject());



        Dataset dataset = TDBFactory.createDataset(directory);

        dataset.begin(ReadWrite.READ);
        // Get model inside the transaction
        Model model = dataset.getNamedModel("http://cyandr.test.com.mymemory");
        //StmtIterator resIterator = model.listStatements();

       // while (resIterator.hasNext()) {


        //    System.out.println(resIterator.next().toString());

       // }

        System.out.println("query test begins");
        //我三月份在北京花了多少元人民币？
        Query query = QueryFactory.create("SELECT ?acts ?yuan " +
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
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);

        ResultSet results = queryExecution.execSelect();
        List<Var> vars = queryExecution.getQuery().getProjectVars();

        while (results.hasNext()) {
            QuerySolution row = results.next();

            for (Var v : vars) {
                RDFNode thing = row.get(v.getName());// 结果例如 ( ?X =
                String valuestr = thing.toString();
                if (valuestr.contains("^^"))
                    valuestr = valuestr.substring(0, valuestr.indexOf("^^"));
                System.out.println(v.getName() + "---" + valuestr.toString());
            }
        }
        queryExecution.close();

        dataset.end();


        try {
            JcSegTest.init();
            JcSegTest.test("我在三月在北京花费多少人民币？");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setServer() {

        InetSocketAddress addr = new InetSocketAddress(8080);
        HttpServer server = null;
        try {
            server = HttpServer.create(addr, 0);
            server.createContext("/", new MyHandler());
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
            System.out.println("Server is listening on port 8080");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            try {
                if (dirPath.contains("."))
                    file.createNewFile();
                else
                    file.mkdir();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange exchange) throws IOException {

            String requestMethod = exchange.getRequestMethod();
            System.out.println("处理新请求:" + requestMethod);
            if (requestMethod.equalsIgnoreCase("GET")) {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, 0);

                OutputStream responseBody = exchange.getResponseBody();
                Headers requestHeaders = exchange.getRequestHeaders();
                Set<String> keySet = requestHeaders.keySet();
                Iterator<String> iter = keySet.iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    List values = requestHeaders.get(key);
                    String s = key + " = " + values.toString() + "\n";
                    responseBody.write(s.getBytes());
                }
                responseBody.close();
            }
        }
    }
}
