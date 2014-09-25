package com.commercehub.dropwizard.mongo.config;

import io.dropwizard.Configuration;

public interface MongoConfiguration<T extends Configuration> {

    MongoClientFactory getMongoClientFactory(T configuration);

}
