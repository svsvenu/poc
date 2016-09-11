package com.venu.jdg;

import org.infinispan.Cache;
import org.infinispan.context.Flag;
import org.infinispan.manager.DefaultCacheManager;

/**
 * Created by venusurampudi on 7/11/16.
 */
/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */


import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class LookupDistributed {

    private static final BasicLogger log = Logger.getLogger(LibraryMode.class);

    private final boolean useXmlConfig;
    private final String cacheName;
    private final String nodeName;
    private volatile boolean stop = false;

    public LookupDistributed(boolean useXmlConfig, String cacheName, String nodeName) {
        this.useXmlConfig = useXmlConfig;
        this.cacheName = cacheName;
        this.nodeName = nodeName;
    }

    public static void main(String[] args) throws Exception {
        boolean useXmlConfig = false;
        String cache = "dist";
        String nodeName = "E";


        new LookupDistributed(useXmlConfig, cache, nodeName).run();
    }

    public void run() throws IOException, InterruptedException {

        EmbeddedCacheManager cacheManager = createCacheManager();

        System.out.println("created manager");

        final Cache<String, String> cache = cacheManager.getCache("dist");

        System.out.printf("Cache %s started on %s, cache members are now %s\n", cacheName, cacheManager.getAddress(),
                cache.getAdvancedCache().getRpcManager().getMembers());

        // Add a listener so that we can see the puts to this node
        cache.addListener(new LoggingListener());

        for (int i=0 ;i<1000; i++) {

            System.out.println("value of key is " + cache.get("key65"));

            Thread.sleep(1000l);

        }
      //  printCacheContents(cache);

        System.exit(0);


    }

    /**
     * {@link org.infinispan.Cache#entrySet()}
     */
    private void printCacheContents(Cache<String, String> cache) {
        System.out.printf("Cache contents on node %s\n", cache.getAdvancedCache().getRpcManager().getAddress());


        ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(cache.getAdvancedCache().withFlags(Flag.SKIP_REMOTE_LOOKUP).entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, String> e : entries) {
            System.out.printf("\t%s = %s\n", e.getKey(), e.getValue());
        }
        System.out.println();
    }

    private EmbeddedCacheManager createCacheManager() throws IOException {
        if (useXmlConfig) {
            return createCacheManagerFromXml();
        } else {
            return createCacheManagerProgrammatically();
        }
    }

    private EmbeddedCacheManager createCacheManagerProgrammatically() {
        System.out.println("Starting a cache manager with a programmatic configuration");
        DefaultCacheManager cacheManager = new DefaultCacheManager(
                GlobalConfigurationBuilder.defaultClusteredBuilder()
                        .transport().nodeName(nodeName).addProperty("configurationFile", "jgroups-tcp.xml")
                        .build(),
                new ConfigurationBuilder()
                        .clustering()
                        .cacheMode(CacheMode.DIST_SYNC)
                        .hash().numOwners(2)
                        .build()
        );

        log.infof("Cache being set to dsit");

        //The only way to get the "repl" cache to be exactly the same as the default cache is to not define it at all
        cacheManager.defineConfiguration("dist", new ConfigurationBuilder()
                        .clustering()
                        .cacheMode(CacheMode.DIST_SYNC)
                                //      .hash().numOwners(1)
                        .build()
        );
        return cacheManager;
    }

    private EmbeddedCacheManager createCacheManagerFromXml() throws IOException {
        System.out.println("Starting a cache manager with an XML configuration");
        System.setProperty("nodeName", nodeName);
        return new DefaultCacheManager("infinispan.xml");
    }

}