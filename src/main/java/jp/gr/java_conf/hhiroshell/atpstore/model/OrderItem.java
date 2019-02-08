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

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.time.ZonedDateTime;

public class OrderItem implements AtpStoreObject {

    private final long orderId;
    private final int lineItemId;
    private final int productId;
    private final double unitPrice;
    private final int quantity;
    private final ZonedDateTime dispatchDate;
    private final ZonedDateTime returnDate;
    private final String giftWrap;
    private final String condition;
    private final int supplierId;
    private final ZonedDateTime estimatedDelivery;

    public static class Builder {

        private long orderId;
        private int lineItemId;
        private int productId;
        private double unitPrice;
        private int quantity;
        private ZonedDateTime dispatchDate;
        private ZonedDateTime returnDate;
        private String giftWrap;
        private String condition;
        private int supplierId;
        private ZonedDateTime estimatedDelivery;

        public Builder(long orderId) {
            this.orderId = orderId;
        }

        public OrderItem Build() {
            return new OrderItem(orderId, lineItemId, productId, unitPrice, quantity, dispatchDate, returnDate,
                    giftWrap, condition, supplierId, estimatedDelivery);
        }

        public Builder setLineItemId(int lineItemId) {
            this.lineItemId = lineItemId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setDispatchDate(ZonedDateTime dispatchDate) {
            this.dispatchDate = dispatchDate;
            return this;
        }

        public Builder setReturnDate(ZonedDateTime returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder setGiftWrap(String giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder setSupplierId(int supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder setEstimatedDelivery(ZonedDateTime estimatedDelivery) {
            this.estimatedDelivery = estimatedDelivery;
            return this;
        }

    }

    private OrderItem(long orderId, int lineItemId, int productId, double unitPrice, int quantity,
                     ZonedDateTime dispatchDate, ZonedDateTime returnDate, String giftWrap, String condition,
                     int supplierId, ZonedDateTime estimatedDelivery) {
        this.orderId = orderId;
        this.lineItemId = lineItemId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.dispatchDate = dispatchDate;
        this.returnDate = returnDate;
        this.giftWrap = giftWrap;
        this.condition = condition;
        this.supplierId = supplierId;
        this.estimatedDelivery = estimatedDelivery;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public int getProductId() {
        return productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public ZonedDateTime getDispatchDate() {
        return dispatchDate;
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    public String getGiftWrap() {
        return giftWrap;
    }

    public String getCondition() {
        return condition;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public ZonedDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }

    @Override
    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                .add("orderId", getOrderId())
                .add("lineItemId", getLineItemId())
                .add("productId", getProductId())
                .add("unitPrice", getUnitPrice())
                .add("quantity", getQuantity())
                .add("dispatchDate", getDispatchDate().toString())
                .add("returnDate", getReturnDate().toString())
                .add("giftWrap", getGiftWrap())
                .add("condition", getCondition())
                .add("supplierId", getSupplierId())
                .add("estimatedDelivery", getEstimatedDelivery().toString());
    }

}
