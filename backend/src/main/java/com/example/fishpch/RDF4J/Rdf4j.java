package com.example.fishpch.RDF4J;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryProvider;
import org.eclipse.rdf4j.rio.RDFParseException;

public class Rdf4j {

    public static void main(String[] args) {
        String pre1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n",
                pre2 = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n",
                pre3 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n",
                pre4 = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n",
                pre5 = "PREFIX cnt: <http://www.semanticweb.org/tkubik/ontologies/2016/2/untitled-ontology-16#>\n",
                pre6 = "PREFIX fishing: <http://example.org/fishpch/0.1#>\n";

        // String rule = pre1+pre2+pre3+pre4+pre5+"CONSTRUCT {?s a cnt:Average} WHERE {SELECT ?s (count(?p) AS ?numBrothers) WHERE {  ?s cnt:hasBrother ?p} GROUP BY ?s HAVING (?numBrothers>=3 && ?numBrothers<=4)}";
        // String match = "";//pre1+pre2+pre3+pre4+pre5 + "CONSTRUCT {?s a cnt:Person}";

//        String rdf4jServer = "http://localhost:8080/rdf4j-server/";
//        String repositoryID = "repo";

        String rdf4jServer = "http://localhost:7200";
        String repositoryID = "repo";

        Repository rep1 = new HTTPRepository(rdf4jServer, repositoryID);
        RepositoryManager repositoryManager = RepositoryProvider.getRepositoryManager(rdf4jServer);
        Repository rep = repositoryManager.getRepository("repo");

        try (RepositoryConnection conn = rep.getConnection()) {
            String[] queries = {
                    pre6 + "SELECT ?person WHERE { ?person fishing:memberOf/fishing:name \"Wody Polskie\" . }",
                    pre6 + "SELECT ?person WHERE { ?person fishing:gender ?gender . FILTER (?gender = \"Female\")}",
                    pre6 + "SELECT ?fish (count(?fish) as ?count) WHERE { ?person fishing:favouriteFish/fishing:name ?fish ." +
                            " } GROUP BY ?fish ORDER BY DESC(?count)",
                    pre6 + "SELECT ?person ?name ?lastName ?knows WHERE { ?person fishing:name ?name ." +
                            " ?person fishing:lastName ?lastName . OPTIONAL { ?person fishing:knows/fishing:name ?knows } }",
                    pre6 + "SELECT (concat(?name, ?lastName) as ?fullName) ?org ?rodName ?account " +
                            "WHERE { ?person fishing:name ?name . " +
                            "?person fishing:lastName ?lastName . ?person fishing:memberOf/fishing:name ?org . " +
                            "?person fishing:uses/fishing:name ?rodName ." +
                            " OPTIONAL { ?person fishing:has/fishing:name ?account } . } ORDER BY (?rodName)"
            };
            for (int i = 0; i < 5; i++) {
                System.out.println(queries[i]);
                TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queries[i]);
                try (TupleQueryResult result = tupleQuery.evaluate()) {
                    while (result.hasNext()) { // iterate over the result
                        BindingSet bindingSet = result.next();
                        String res = "";
                        if (i == 0 || i == 1 || i == 3) {
                            Value valuePerson = bindingSet.getValue("person");
                            res += valuePerson + " ";
                        }
                        if (i == 2) {
                            Value valueFish = bindingSet.getValue("fish");
                            Value valueCount = bindingSet.getValue("count");
                            res += valueFish + " " + valueCount;
                        }
                        if (i == 3) {
                            Value valueName = bindingSet.getValue("name");
                            Value valueLastName = bindingSet.getValue("lastName");
                            Value valueKnows = bindingSet.getValue("knows");
                            res += valueName + " " + valueLastName + " " + valueKnows;
                        }
                        if (i == 4) {
                            Value valueFullName = bindingSet.getValue("fullName");
                            Value valueOrg = bindingSet.getValue("org");
                            Value valueRodName = bindingSet.getValue("rodName");
                            Value valueAccount = bindingSet.getValue("account");
                            res += valueFullName + " " + valueOrg + " " + valueRodName + " " + valueAccount;
                        }
                        System.out.println("xd1" + res + "\n\n");
                    }
                }
            }

        } catch (RDFParseException | RepositoryException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}