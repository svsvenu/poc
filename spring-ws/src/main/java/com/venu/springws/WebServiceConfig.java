package com.venu.springws;

/**
 * Created by venusurampudi on 7/8/16.
 */

        import org.springframework.boot.context.embedded.ServletRegistrationBean;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.core.io.ClassPathResource;
        import org.springframework.ws.config.annotation.EnableWs;
        import org.springframework.ws.config.annotation.WsConfigurerAdapter;
        import org.springframework.ws.server.EndpointInterceptor;
        import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
        import org.springframework.ws.transport.http.MessageDispatcherServlet;
        import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
        import org.springframework.xml.xsd.SimpleXsdSchema;
        import org.springframework.xml.xsd.XsdSchema;

        import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {

        // register global interceptor
        interceptors.add(new CustomEndPointInterceptor());

        // register endpoint specific interceptor
        interceptors.add(new PayloadRootSmartSoapEndpointInterceptor(
                new CustomEndPointInterceptor(),
                "http://spring.io/guides/gs-producing-web-service",
                "getCountryRequest"));
    }


}