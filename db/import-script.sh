#!/bin/bash

container_name="graphdb"

# Copy the .ttl files into the Docker container
docker cp ontology.ttl $container_name:/import/ontology.ttl
docker cp instances.ttl $container_name:/import/instances.ttl

# # Access the Docker container's terminal
# docker exec -it $container_name bash

# # Import the .ttl files using the rdf4j-console.sh script
# /opt/graphdb/bin/rdf4j-console.sh -f /import/ontology.ttl -c <repository_id> -m <base_uri>
# /opt/graphdb/bin/rdf4j-console.sh -f /import/instances.ttl -c <repository_id> -m <base_uri>

# # Exit the Docker container's terminal
# exit
