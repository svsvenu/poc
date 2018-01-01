## Standalone camel rest application

The idea is to expose a simple restful webservice using camel without a container, containers are fast
approaching the fate of plastic bags at the local grocery stores, you can use them but brace yourself for
a stink eye. This "uber jar" type application can easily be ambulated into docker images, higher environments.

## The listener

Camel has a rest DSL which supports the configuration of rest end points, these method calls are a facade to
a healthy and often over whelming choice of transport mechanisms such as netty, undertow, restlet yada yada.
Camel seems to pick up the one in the class path, to test it, The pom.xml file attached has two dependencies,
one for netty (camel-netty4-http) and the other for restlet (camel-restlet ). The usage of either one is set
in the code below

{% highlight c++ %}

some code here

{% endhighlight %}

