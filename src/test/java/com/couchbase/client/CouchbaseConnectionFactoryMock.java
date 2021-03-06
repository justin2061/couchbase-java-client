/**
 * Copyright (C) 2009-2013 Couchbase, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */

package com.couchbase.client;

import com.couchbase.client.vbucket.provider.ConfigurationProvider;
import com.couchbase.client.vbucket.CouchbaseNodeOrder;
import com.couchbase.client.vbucket.config.Bucket;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Mocks a connection factory and allows it to pass in an arbitrary
 * configuration provider.
 */
public class CouchbaseConnectionFactoryMock extends CouchbaseConnectionFactory {

  private CouchbaseNodeOrder order;

  public CouchbaseConnectionFactoryMock(final List<URI> baseList,
    final String bucketName, String password, ConfigurationProvider cp, CouchbaseNodeOrder order)
    throws IOException {
    super(baseList, bucketName, password);

    this.configurationProvider = cp;
    this.order = order;
  }

  public CouchbaseConnectionFactoryMock(final List<URI> baseList,
    final String bucketName, String password, ConfigurationProvider cp)
    throws IOException {
    this(baseList, bucketName, password, cp, DEFAULT_STREAMING_NODE_ORDER);
  }

  public Bucket getBucket(String bucketName) {
    return this.configurationProvider.getConfig();
  }

  @Override
  public CouchbaseNodeOrder getStreamingNodeOrder() {
    if (order == null) {
      return super.getStreamingNodeOrder();
    }
    return order;
  }
}
