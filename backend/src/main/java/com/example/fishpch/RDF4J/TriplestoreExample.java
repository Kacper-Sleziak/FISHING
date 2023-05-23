package com.example.fishpch.RDF4J;



import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;

import java.util.Random;

public class TriplestoreExample {
    public static void main(String[] args) {
        Repository repository = new SailRepository(new MemoryStore());
        repository.init();

        try (RepositoryConnection connection = repository.getConnection()) {
            // Utwórz model RDF do pracy
            Model model = connection.getModel();

            // Dodaj 10 losowych trójek do modelu
            Random random = new Random();
            for (int i = 1; i <= 10; i++) {
                String subject = "http://example.org/subject/" + i;
                String predicate = "http://example.org/predicate";
                String object = "http://example.org/object/" + random.nextInt(100);

                Resource subjectResource = model.createResource(subject);
                IRI predicateIRI = model.createIRI(predicate);
                Resource objectResource = model.createResource(object);

                Statement statement = model.createStatement(subjectResource, predicateIRI, objectResource);
                model.add(statement);
            }

            // Zapisz zmiany
            connection.commit();

            // Wczytaj trójki z modelu
            try (RepositoryConnection readConnection = repository.getConnection()) {
                Model readModel = readConnection.getStatements(null, null, null)
                        .stream()
                        .collect(Models.toModel());

                StmtIterator iterator = readModel.listStatements();
                while (iterator.hasNext()) {
                    Statement statement = iterator.nextStatement();
                    Resource subject = statement.getSubject();
                    IRI predicate = statement.getPredicate();
                    Value object = statement.getObject();

                    System.out.println("Subject: " + subject);
                    System.out.println("Predicate: " + predicate);
                    System.out.println("Object: " + object);
                    System.out.println();
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            // Zamknij repozytorium
            repository.shutDown();
        }
