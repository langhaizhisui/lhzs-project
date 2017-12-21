package cn.lhzs.common.util;

/**
 * Created by ZHX on 2017/11/23.
 */

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;

import java.util.LinkedHashSet;

import static cn.lhzs.common.config.Resources.JEST;

public class JestUtil {

    private static JestClient JestClient;

    private static ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, getConnectAddress());
        clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED, false);
        return clientConfig;
    }

    private static LinkedHashSet getConnectAddress() {
        String connectionUrl = JEST.getString("jest.http") + JEST.getString("jest.ip") + ":" + JEST.getString("jest.port");
        LinkedHashSet connect = new LinkedHashSet();
        connect.add(connectionUrl);
        return connect;
    }

    public static JestClient getJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig());
        if (JestClient == null) {
            JestClient = factory.getObject();
        }
        return JestClient;
    }
}