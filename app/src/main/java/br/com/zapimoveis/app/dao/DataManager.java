package br.com.zapimoveis.app.dao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/***
 * Created by Gustavo on 06/04/2017
 */

public class DataManager {

    /***
     * Persiste uma lista objetos da classe RealmModel
     * @param realmObjects objetos
     */
    public static <S extends RealmModel> void save(List<S> realmObjects) {
        Realm instance = Realm.getDefaultInstance();
        instance.beginTransaction();
        instance.insertOrUpdate(realmObjects);
        instance.commitTransaction();
        instance.close();
    }

    /***
     * Retorna objeto da classe RealmModel com base no id passado por parâmetro
     * @param codProperty codProperty
     * @param clazz classe referente ao tipo do objeto
     * @return objeto
     */
    public static <S extends RealmModel> S selectById(long codProperty, Class<S> clazz) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).equalTo("codProperty", codProperty).findFirst();
    }

    /***
     * Retorna todos os objetos da classe RealmModel existentes no banco de dados
     * @param clazz classe referente ao tipo do objeto
     * @return lista de objetos
     */
    public static <S extends RealmModel> RealmResults<S> selectAll(Class<S> clazz) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).findAll();
    }

    /***
     * Delete todos os objetos da classe RealmModel existentes no bando de dados
     * @param clazz classe referente ao tipo do objeto
     */
    public static <S extends RealmModel> void deleteAll(Class<S> clazz) {
        Realm instance = Realm.getDefaultInstance();
        instance.beginTransaction();
        instance.delete(clazz);
        instance.commitTransaction();
        instance.close();
    }

    /***
     * Retorna todos os objetos da classe RealmModel onde o resultado estejam dentro dos comparadores
     * @param clazz classe referente ao tipo do objeto
     * @param field nome do campo na tabela
     * @param from argumento inicial
     * @param to argumento final
     * @return
     */
    public static <S extends RealmModel> RealmResults<S> selectBetween(Class<S> clazz, String field, int from, int to) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).between(field, from, to).findAll();
    }

}