package com.venu;

import com.azure.messaging.eventhubs.*;
import com.azure.messaging.eventhubs.models.EventPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventHubSend {


    private static final String connectionString = "Endpoint=sb://mteh12.servicebus.windows.net/;SharedAccessKeyName=testpolicy;SharedAccessKey=4y/KdZ5tRk2WyJ7HBPOGOUW85m179WMVazHvm7HubuI=;EntityPath=testeventhub";

  //  private static final String connectionString = "Endpoint=sb://vijaygedela.servicebus.windows.net/;SharedAccessKeyName=venu;SharedAccessKey=UAXI3tDZMvmYm/U0wrP595MJpAglL5i9w/bS9m3iCNA=;EntityPath=test1";
    private static final String eventHubName = "testeventhub";

    public static void main(String[] args) throws Exception {
        publishEvents();
    }

    /**
     * Code sample for publishing events.
     * @throws IllegalArgumentException if the event data is bigger than max batch size.
     */
    public static void publishEvents() throws InterruptedException {
        // create a producer client
        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(connectionString, eventHubName)
                .buildProducerClient();

        // sample events in an array
        ArrayList<EventData> data = new ArrayList<EventData>();
        List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));
        for ( int i=0; i<10000; i++) {
            data.add(new EventData("venu"));
        }

        // create a batch
        EventDataBatch eventDataBatch = producer.createBatch();

        for (EventData eventData : data) {

            System.out.println("In loope");

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
            System.out.println("batch size is  " + eventDataBatch.getCount());

            producer.send(eventDataBatch);
            System.out.println("sent ");


        }
        producer.close();

        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
                .connectionString(connectionString)
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();

// Receive newly added events from partition with id "0". EventPosition specifies the position
// within the Event Hub partition to begin consuming events.
        consumer.receiveFromPartition("0", EventPosition.earliest()).subscribe(event -> {
            // Process each event as it arrives.
            System.out.println("received" + event.getData().getBodyAsString());
        });

        Thread.sleep(10000);
// add sleep or System.in.read() to receive events before exiting the process.
    }

}
