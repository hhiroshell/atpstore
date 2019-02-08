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

package jp.gr.java_conf.hhiroshell.atpstore.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jp.gr.java_conf.hhiroshell.atpstore.model.OrderItemTableDefinition.Column;

public class OrderItemSearcher extends AtpStoreSearcher<OrderItem> {

    public OrderItemSearcher() {
        super();
        tableDefinition = OrderItemTableDefinition.getInstance();
    }

    @Override
    protected List<OrderItem> buildResult(ResultSet resultSet) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        while (resultSet.next()) {
            OrderItem.Builder builder = new OrderItem.Builder(resultSet.getLong(tableDefinition.getIdColumnLabel()));
            OrderItem orderItem;
            if (fetchAllColumns) {
                orderItem = builder
                        .setLineItemId(resultSet.getInt(Column.COL_LINE_ITEM_ID.getLabel()))
                        .setProductId(resultSet.getInt(Column.COL_PRODUCT_ID.getLabel()))
                        .setUnitPrice(resultSet.getDouble(Column.COL_UNIT_PRICE.getLabel()))
                        .setQuantity(resultSet.getInt(Column.COL_QUANTITY.getLabel()))
                        .setDispatchDate(Optional.ofNullable(
                                resultSet.getDate(Column.COL_DISPATCH_DATE.getLabel())).orElse(new Date(0))
                                .toLocalDate().atStartOfDay(ZoneId.of("America/Montreal")))
                        .setReturnDate(Optional.ofNullable(
                                resultSet.getDate(Column.COL_RETURN_DATE.getLabel())).orElse(new Date(0))
                                .toLocalDate().atStartOfDay(ZoneId.of("America/Montreal")))
                        .setGiftWrap(resultSet.getString(Column.COL_GIFT_WRAP.getLabel()))
                        .setCondition(resultSet.getString(Column.COL_CONDITION.getLabel()))
                        .setSupplierId(resultSet.getInt(Column.COL_SUPPLIER_ID.getLabel()))
                        .setEstimatedDelivery(Optional.ofNullable(
                                resultSet.getDate(Column.COL_ESTIMATED_DELIVERY.getLabel())).orElse(new Date(0))
                                .toLocalDate().atStartOfDay(ZoneId.of("America/Montreal")))
                        .Build();
            } else {
                orderItem = builder.Build();
            }
            orderItems.add(orderItem);
        }
        return orderItems;
    }

}