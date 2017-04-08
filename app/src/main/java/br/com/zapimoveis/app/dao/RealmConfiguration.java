package br.com.zapimoveis.app.dao;

import android.content.Context;

import io.realm.Realm;

/***
 * Created by Gustavo on 06/04/2017
 */

public class RealmConfiguration {

    //Instância da classe
    private static RealmConfiguration instance = null;

    private RealmConfiguration(){}

    /***
     * Instância da classe
     * @return instância
     */
    public synchronized static RealmConfiguration getInstance() {
        if (instance == null)
            instance = new RealmConfiguration();

        return instance;
    }

    /***
     * Inicializa a confuração default do realm
     * @param context contexto da aplicação
     * @param schemaVersion versão do banco de dados
     * @param entityMigration arquivo de migration
     */
    public void init(Context context, long schemaVersion, EntityMigration entityMigration){
        Realm.init(context);
        io.realm.RealmConfiguration config = new io.realm.RealmConfiguration.Builder()
                .schemaVersion(schemaVersion)
                .migration(entityMigration)
                .build();

        Realm.setDefaultConfiguration(config);
    }
}