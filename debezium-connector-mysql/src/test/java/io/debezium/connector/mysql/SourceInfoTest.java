/*
 * Copyright Debezium Authors.
 * 
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.mysql;

import io.confluent.connect.avro.AvroData;

import org.apache.avro.Schema;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SourceInfoTest {
    private static final AvroData avroData;
    private static int avroSchemaCacheSize = 1000;

    static {
        avroData = new AvroData(avroSchemaCacheSize);
    }

    /**
     * When we want to consume SinkRecord which generated by debezium-connector-mysql, it should not
     * throw error "org.apache.avro.SchemaParseException: Illegal character in: server-id"
     */
    @Test
    public void testValidateSourceInfoSchema() {
        org.apache.kafka.connect.data.Schema kafkaSchema = SourceInfo.SCHEMA;
        Schema avroSchema = avroData.fromConnectSchema(kafkaSchema);
        assertTrue(avroSchema != null);
    }

}
