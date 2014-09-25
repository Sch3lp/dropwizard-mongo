package com.commercehub.dropwizard.mongo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClientURI;

import javax.validation.constraints.NotNull;
import java.net.UnknownHostException;

public class MongoClientFactory {

    @NotNull
    private MongoClientURI uri;

    private String dbName;

    public ManagedMongoClient build() throws UnknownHostException {
        return new ManagedMongoClient(uri);
    }

    @JsonProperty
    public MongoClientURI getUri() {
        return uri;
    }

    @JsonProperty
    public void setUri(MongoClientURI uri) {
        this.uri = uri;
    }

    @JsonProperty
    public String getDbName() {
        return dbName;
    }

    @JsonProperty
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

}
