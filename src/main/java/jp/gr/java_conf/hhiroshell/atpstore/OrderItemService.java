/*
 * Copyright (c) 2019 Hiroshi Hayakawa <hhiroshell@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.gr.java_conf.hhiroshell.atpstore;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;
import jp.gr.java_conf.hhiroshell.atpstore.model.AtpStoreSearcher;
import jp.gr.java_conf.hhiroshell.atpstore.model.OrderItem;
import jp.gr.java_conf.hhiroshell.atpstore.model.OrderItemSearcher;
import jp.gr.java_conf.hhiroshell.atpstore.model.SpecifyIdStrategy;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.sql.SQLException;
import java.util.List;

public class OrderItemService implements Service {

    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/{orderId}", this::echo);
    }

    private void echo(ServerRequest request, ServerResponse response) {
        OrderItemSearcher searcher = AtpStoreSearcher.getSearcher(OrderItemSearcher.class);
        List<OrderItem> orderItems = null;
        System.out.println(request.path().param("orderId"));
        try {
            orderItems = searcher
                    .fetchAllAttributes(true)
                    .setSearchStrategy(new SpecifyIdStrategy(request.path().param("orderId")))
                    .search();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (orderItems == null) {
            response.send(null);
            return;
        }
        JsonArrayBuilder returnObjectBuilder = Json.createArrayBuilder();
        orderItems.forEach( i -> {
            returnObjectBuilder.add(i.toJsonObjectBuilder());
        });
        response.send(returnObjectBuilder.build());
    }

}