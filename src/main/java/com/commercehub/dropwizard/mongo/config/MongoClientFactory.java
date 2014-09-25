/*
 * Copyright (C) 2014 Commerce Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
