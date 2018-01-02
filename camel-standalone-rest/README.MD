## Standalone camel rest application

The idea is to expose a simple restful webservice using camel without a container, containers are fast
approaching the fate of plastic bags at the local grocery stores, you can use them but brace yourself for
a stink eye. This "uber jar" type application can easily be ambulated into docker images, higher environments.
This blog is "inspired" by the camel documentation

## The listener

Camel has a rest DSL which supports the configuration of rest end points, these method calls are a facade to
a healthy and often over whelming choice of transport mechanisms such as netty, undertow, restlet yada yada.
Camel seems to pick up the one in the class path, to test it, The pom.xml file attached has two dependencies,
one for netty (camel-netty4-http) and the other for restlet (camel-restlet ). The usage of either one is set
in the code below

```java
// Configure as restlet
restConfiguration().component("restlet")

// Configure as netty4
restConfiguration().component("netty4-http")

```

## The routes

Each of the url map is deployed as a new route, this can be corroborated by inspecting the JMX console.

![Alt text](/camel-standalone-rest/jconsole_routes.png?raw=true "Routes")

The two routes which are named getUserRoute and sendUserRoute are the ones that we created normally,
the other two routes route1 and route2 are deployed by "camel", one processes the get and the other
addresses the post messages.

## The startup

The standalone camel application with a couple of routes starts up pretty light running on about 25Mb
of heap space.

![Alt text](/camel-standalone-rest/standalone_heap.png?raw=true "Heap")

## Performance

The standalone app seems to hold up pretty good for a load of 10 threads, the heap spikes but recovers
as expected

![Alt text](/camel-standalone-rest/heap_on_load.png?raw=true "Heap on load")