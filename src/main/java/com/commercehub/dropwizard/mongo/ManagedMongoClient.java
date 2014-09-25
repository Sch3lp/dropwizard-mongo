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

package com.commercehub.dropwizard.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.dropwizard.lifecycle.Managed;

import java.net.UnknownHostException;

public class ManagedMongoClient extends MongoClient implements Managed {

    public ManagedMongoClient(MongoClientURI uri) throws UnknownHostException {
        super(uri);
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        close();
    }

}
