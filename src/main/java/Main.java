import MemoryWorld.*;
import OntActivity.ConsumeActivity;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.VCARD;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {


        // Make a TDB-backed dataset
        String directory = "MyDatabases/Dataset1/";
        makeDir(directory);


        Dataset dataset = TDBFactory.createDataset(directory);


        ConsumeActivity consumeActivity = new ConsumeActivity(new People(), new Movement(), new Currency(), new Location(), new Time(), new MemoryObject());

        Model model = consumeActivity.generateRdfModel();





        dataset.begin(ReadWrite.WRITE);

        dataset.addNamedModel("http://cyandr.test.com.mymemory", model);
        dataset.commit();
        dataset.end();


        dataset.begin(ReadWrite.READ);
        // Get model inside the transaction
        model = dataset.getNamedModel("http://cyandr.test.com.mymemory");
        ResIterator resIterator= model.listSubjects();
        while (resIterator.hasNext())
        {


            System.out.println(resIterator.next().toString());

        }
        //我在清华大学食堂花了多少钱


        Query queryHowmuchCostinTsinghua = QueryFactory.create("SELECT ?x\n" +
                "WHERE { ?x  rdf:type  }");


        Query query = QueryFactory.create("SELECT ?x\n" +
                "WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  \"Xinhui Yan2\" }");
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);

        ResultSet results = queryExecution.execSelect();
        List<Var> vars = queryExecution.getQuery().getProjectVars();

        while (results.hasNext()) {
            QuerySolution row = results.next();

            for (Var v : vars) {
                RDFNode thing = row.get(v.getName());// 结果例如 ( ?X =
                System.out.println(v.getName() + "---" + thing.toString());
            }
        }
        queryExecution.close();
        Iterator<String> srts = dataset.listNames();
        while (srts.hasNext()) {

            System.out.println(srts.next());

        }
        dataset.end();

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
                if (dirPath.contains(".")) {
                    if (!file.createNewFile())
                        throw new IOException("can not create file");
                }
                else {
                    if (!file.mkdirs())
                        throw new IOException("can not create dirs");
                }
            } catch (Exception e) {
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
