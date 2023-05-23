package com.example.fishpch.RDF4J;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebResourceOntology {

    private final Repository repository;

    @Autowired
    public WebResourceOntology(Repository repository) {
        this.repository = repository;
    }

    public void createOntology() {
        try (RepositoryConnection connection = repository.getConnection()) {
            ModelBuilder modelBuilder = new ModelBuilder();
            IRI resource = modelBuilder.createIRI("http://example.com/ontology/Resource");
            IRI url = modelBuilder.createIRI("http://example.com/ontology/url");
            IRI title = modelBuilder.createIRI("http://example.com/ontology/title");
            IRI description = modelBuilder.createIRI("http://example.com/ontology/description");
            IRI publisher = modelBuilder.createIRI("http://example.com/ontology/publisher");

            modelBuilder.subject(resource)
                    .add(url, "http://example.com/resource1")
                    .add(title, "Resource 1")
                    .add(description, "Description of Resource 1")
                    .add(publisher, "Publisher 1");

            modelBuilder.subject(resource)
                    .add(url, "http://example.com/resource2")
                    .add(title, "Resource 2")
                    .add(description, "Description of Resource 2")
                    .add(publisher, "Publisher 2");

            Model model = modelBuilder.build();
            connection.add(model);
        }
    }
}
