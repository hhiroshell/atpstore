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

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class AtpStoreDataSource {

    private static final String KEY_DATABASE_USERNAME = "DATABASE_USERNAME";

    private static final String KEY_DATABASE_PASSWORD = "DATABASE_PASSWORD";

    private static final String KEY_DATABASE_SERVICE = "DATABASE_SERVICE";

    private static final String KEY_DATABASE_TNS_ADMIN = "DATABASE_TNS_ADMIN";

    private static final String CONNECTION_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";

    private static final int INITIAL_POOL_SIZE = 5;

    private static DataSource instance;

    // uninstanciable
    private AtpStoreDataSource() {}

    static public DataSource getInstance() {
        if (instance == null) {
            instance = getDataSource();
        }
        return instance;
    }

    /**
     * データソースを取得する
     *
     * @return データソース
     */
    private static DataSource getDataSource() {
        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        try {
            poolDataSource.setConnectionFactoryClassName(CONNECTION_FACTORY_CLASS_NAME);
            poolDataSource.setUser(System.getenv(KEY_DATABASE_USERNAME));
            poolDataSource.setPassword(System.getenv(KEY_DATABASE_PASSWORD));
            poolDataSource.setURL(new StringBuilder()
                    .append("jdbc:oracle:thin:@")
                    .append(System.getenv(KEY_DATABASE_SERVICE))
                    .append("?TNS_ADMIN=")
                    .append(System.getenv(KEY_DATABASE_TNS_ADMIN))
                    .toString());
            poolDataSource.setInitialPoolSize(INITIAL_POOL_SIZE);
            return poolDataSource;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

}
