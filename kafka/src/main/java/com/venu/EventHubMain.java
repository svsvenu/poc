package com.venu;

import com.azure.messaging.eventhubs.*;
import java.util.Arrays;
import java.util.List;

public class EventHubMain {



        private static final String connectionString = "Endpoint=sb://vijaygedela.servicebus.windows.net/;SharedAccessKeyName=venu;SharedAccessKey=UAXI3tDZMvmYm/U0wrP595MJpAglL5i9w/bS9m3iCNA=;EntityPath=test1";
        private static final String eventHubName = "test1";

        public static void main(String[] args) {
            publishEvents();
        }

    /**
     * Code sample for publishing events.
     * @throws IllegalArgumentException if the event data is bigger than max batch size.
     */
    public static void publishEvents() {
        // create a producer client
        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(connectionString, eventHubName)
                .buildProducerClient();

        // sample events in an array
        List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));

        // create a batch
        EventDataBatch eventDataBatch = producer.createBatch();

        for (EventData eventData : allEvents) {
            // try to add the event from the array to the batch
            if (!eventDataBatch.tryAdd(eventData)) {
                // if the batch is full, send it and then create a new batch
                producer.send(eventDataBatch);
                System.out.println("sent **");
                eventDataBatch = producer.createBatch();

                // Try to add that event that couldn't fit before.
                if (!eventDataBatch.tryAdd(eventData)) {
                    throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
                            + eventDataBatch.getMaxSizeInBytes());
                }
            }
        }
        // send the last batch of remaining events
        if (eventDataBatch.getCount() > 0) {
            producer.send(eventDataBatch);
        }
        producer.close();
    }

}
