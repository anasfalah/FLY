package com.flyteam.mastermind.tool;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.core.spi.component.ProviderFactory;
import com.sun.jersey.core.spi.component.ProviderServices;
import com.sun.jersey.core.spi.factory.InjectableProviderFactory;
import com.sun.jersey.core.spi.factory.MessageBodyFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import org.mockito.Mockito;

/**
 *
 * @author jtapiat
 */
public class TestTool {

    public static ClientResponse getClientResponseMock() {
        ClientResponse cs = new ClientResponse(0, new InBoundHeaders(), new InputStream() {
            @Override
            public int read() throws IOException {
                return -1;
            }
        }, new MessageBodyFactory(new ProviderServices(new ProviderFactory(new InjectableProviderFactory()),
                new HashSet<Class<?>>(),
                new HashSet<Object>()),
                true));
        return Mockito.spy(cs);
    }
}
