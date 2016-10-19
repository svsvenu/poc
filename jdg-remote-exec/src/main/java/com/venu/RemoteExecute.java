package com.venu;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.distexec.DefaultExecutorService;
import org.infinispan.distexec.DistributedExecutorService;
import org.infinispan.distexec.DistributedTask;
import org.infinispan.distexec.DistributedTaskBuilder;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by venusurampudi on 10/16/16.
 */
public class RemoteExecute {


    private static RemoteCacheManager cacheManager;
    private static RemoteCache<String, Object> cache;

    public static void main(String args[]) throws Exception{

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer()
                .host("localhost")
                .port(Integer.parseInt("11372"));
        cacheManager = new RemoteCacheManager(builder.build());
        cache = cacheManager.getCache("teams");
        DistributedExecutorService des = new DefaultExecutorService((Cache<?, ?>) cache);

        DistributedTaskBuilder<Boolean> taskBuilder;

        Callable<Boolean> t = new TestInt(5);

        taskBuilder = des.createDistributedTaskBuilder(t);

        taskBuilder.failoverPolicy(DefaultExecutorService.RANDOM_NODE_FAILOVER);

        DistributedTask<Boolean> distributedTask = taskBuilder.build();
        Future<Boolean> future = des.submit(distributedTask);
        Boolean r = future.get();

     //   for (int i=0; i<100000; i++) {
      //      cache.put("key"+i, "val"+i);
      //  }



    }

    public static class TestInt implements java.util.concurrent.Callable<Boolean>, Serializable{

        public TestInt(Integer val){


        }


        @Override
        public Boolean call() throws Exception {
            return true;
        }
    }
}
