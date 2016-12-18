package camel;

import com.HolidayRequest;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by venusurampudi on 10/27/16.
 */
@Named
public class CustomSplitter implements Iterator{

    private static Logger LOG = Logger.getLogger(CustomSplitter.class);

    private XMLInputFactory  factory = null;

    private XMLStreamReader reader = null;

    public CustomSplitter()  {

        try {

            LOG.info("\t2-\t Created Splitter");


             factory = XMLInputFactory.newInstance();

             reader = factory.createXMLStreamReader(new FileInputStream(new File("/Users/venusurampudi/Desktop/temp/.camel/employee.txt")));

         //   reader.nextTag();

         //   while(!reader.getLocalName().equals("HolidayRequest")) {
         //       reader.nextTag();
         //   }

        }
        catch (Exception e){

            e.printStackTrace();
        }


    }

    public Iterator foo(File file) {
        return new CustomSplitter();
    }

    public boolean hasNext() {

        boolean retVal = false;

        LOG.info(" Called hasNext() " );

        try {
            retVal = reader.hasNext();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    public Object next() {

        LOG.info(" Called next() " );

        Object object = null;

       // HolidayRequest hr = new HolidayRequest();

        HolidayRequest hr = null;

        try{

            while(!reader.isStartElement()){

                reader.next();
            }

            if (reader.isEndElement()){

                return object;

            }


            if (reader.isStartElement() && reader.getLocalName().equals("HolidayRequest")) {

                JAXBContext jc = JAXBContext.newInstance(HolidayRequest.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                JAXBElement<HolidayRequest> jb = unmarshaller.unmarshal(reader, HolidayRequest.class);

                hr = jb.getValue();

                LOG.info("next returning" + hr.getEmployee());

            }


        }
        catch (NoSuchElementException nse){

            System.out.println("caught nse");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return hr;
    }

    public void remove() {

    }

    public void forEachRemaining(Consumer action) {

    }
}
