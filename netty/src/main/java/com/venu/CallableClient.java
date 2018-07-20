package com.venu;

import java.util.concurrent.Callable;

public class CallableClient implements Callable {
    int count = 0;
    public CallableClient(int i) {
        count = i;
    }

    public Object call()  {
        try {
            NettyClient.getClientHandler(count);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("finished " + count);
        }
        return null;
    }
}
