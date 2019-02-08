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

import java.util.ArrayList;
import java.util.List;

public class OrderItemTableDefinition implements TableDefinition {

    private static OrderItemTableDefinition instance = null;

    static OrderItemTableDefinition getInstance() {
        if (instance == null) {
            instance = new OrderItemTableDefinition();
        }
        return instance;
    }

    // uninstanciable
    private OrderItemTableDefinition() {}

    static final String TABLE_NAME = "ORDER_ITEMS";

    static enum Column {

        COL_ORDER_ID("ORDER_ID"),
        COL_LINE_ITEM_ID("LINE_ITEM_ID"),
        COL_PRODUCT_ID("PRODUCT_ID"),
        COL_UNIT_PRICE("UNIT_PRICE"),
        COL_QUANTITY("QUANTITY"),
        COL_DISPATCH_DATE("DISPATCH_DATE"),
        COL_RETURN_DATE("RETURN_DATE"),
        COL_GIFT_WRAP("GIFT_WRAP"),
        COL_CONDITION("CONDITION"),
        COL_SUPPLIER_ID("SUPPLIER_ID"),
        COL_ESTIMATED_DELIVERY("ESTIMATED_DELIVERY"),
        ;

        private final String label;

        private Column(String label) {
            this.label = label;
        }

        String getLabel() {
            return label;
        }

    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnLabel() {
        return Column.COL_ORDER_ID.getLabel();
    }

    @Override
    public List<String> getAllColumnLabels() {
        Column[] columns = Column.values();
        List<String> labels = new ArrayList<>(columns.length);
        for (Column column : columns) {
            labels.add(column.getLabel());
        }
        return labels;
    }

}
