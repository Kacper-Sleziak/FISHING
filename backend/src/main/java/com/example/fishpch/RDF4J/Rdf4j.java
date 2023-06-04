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
                pre6 = "PREFIX fishing: <http://example.org/fishing/0.1#>\n";

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
                    pre6 + "SELECT ?fish WHERE { ?fish a fishing:Fish } ",
                    pre6 + "SELECT (COUNT(DISTINCT ?gender) AS ?genderCount) WHERE { ?gender rdf:type fishing:Gender .}",
                    pre6 + "SELECT ?user\n" +
                            "WHERE {\n" +
                            "  ?user a fishing:User ;\n" +
                            "        fishing:wasOn fishing:FishingSpot2 ;\n" +
                            "        fishing:gender fishing:MaleGender .\n" +
                            "}",
                    pre6 + "SELECT ?orgName (COUNT(?org) AS ?orgCount) (GROUP_CONCAT(?userName; separator=\", \") AS ?userNames)\n" +
                            "WHERE {\n" +
                            "  ?org a fishing:Organization ;\n" +
                            "       rdfs:label ?orgName .\n" +
                            "  OPTIONAL {\n" +
                            "    ?user fishing:memberOf ?org ;\n" +
                            "          fishing:name ?userName .\n" +
                            "  }\n" +
                            "}\n" +
                            "GROUP BY ?orgName\n",
                    pre6 + "SELECT ?friend (COUNT(?femaleFriend) AS ?femaleCount) (COUNT(?maleFriend) AS ?maleCount)\n" +
                            "WHERE {\n" +
                            "  fishing:John fishing:hasFriend ?friend .\n" +
                            "  \n" +
                            "  OPTIONAL {\n" +
                            "    ?friend fishing:gender fishing:FemaleGender .\n" +
                            "    BIND(?friend AS ?femaleFriend)\n" +
                            "  }\n" +
                            "  \n" +
                            "  OPTIONAL {\n" +
                            "    ?friend fishing:gender fishing:MaleGender .\n" +
                            "    BIND(?friend AS ?maleFriend)\n" +
                            "  }\n" +
                            "}\n" +
                            "GROUP BY ?friend"
            };

            for (int i = 0; i < 5; i++) {
                System.out.println('*');
                System.out.println(queries[i]);
                TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queries[i]);
                try (TupleQueryResult result = tupleQuery.evaluate()) {
                    while (result.hasNext()) { // iterate over the result
                        BindingSet bindingSet = result.next();
                        String res = "";
                        if (i == 0) {
                            Value valueFish = bindingSet.getValue("fish");
                            res += valueFish + " ";
                        }

                        if (i == 1) {
                            Value countGender = bindingSet.getValue("genderCount");
                            res += countGender + " ";
                        }

                        if (i == 2) {
                            Value valueUser= bindingSet.getValue("user");
                            res += valueUser;
                        }
                        if (i == 3) {
                            Value valueFullName = bindingSet.getValue("orgCount");
                            Value userNamesValue = bindingSet.getValue("userNames");
                            res += valueFullName + " " + userNamesValue;
                        }
                        if (i == 4) {
                            Value valueFriend = bindingSet.getValue("friend");
                            Value valueFemaleCount = bindingSet.getValue("femaleCount");
                            Value valueMaleCount = bindingSet.getValue("maleCount");

                            res += valueFriend + " " + valueFemaleCount + " " + valueMaleCount;
                        }
                        System.out.println(res+"\n\n");
                    }
                }
            }

        } catch (RDFParseException | RepositoryException e) {
            e.printStackTrace();
        }
    }
}
