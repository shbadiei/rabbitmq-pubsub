package com.codechallenge.rabbitpubsub.common;

public enum Branch {
    KhorasanRazavi, Esfahan, Ilam, Mazandaran, Fars, Kerman, Yazd;

    public String getQueueName() {
        return this.name().toLowerCase();
    }

    public String getDLQQueueName() {
        return this.name().toLowerCase() + "_dlq";
    }

    public String getRoutingKey() {
        return this.name().toLowerCase();
    }

    public String getDLQRoutingKey() {
        return this.name().toLowerCase() + "_dlq";
    }
}
