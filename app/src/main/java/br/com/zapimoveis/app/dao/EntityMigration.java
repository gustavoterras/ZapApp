package br.com.zapimoveis.app.dao;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/***
 * Created by Gustavo on 06/04/2017
 */

public class EntityMigration implements RealmMigration {

    /***
     * Arquivo migration
     * @param realm realm
     * @param oldVersion número da versão interior do banco de dados
     * @param newVersion número da nova versão do banco de dados
     */
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
    }
}