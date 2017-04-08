package br.com.zapimoveis.app.dao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/***
 * Created by Gustavo on 06/04/2017
 */

public class DataManager {

    /***
     * Retorna próximo id
     * @param clazz classe referente ao tipo do objeto
     * @return id
     */
    public static <S extends RealmModel> long getNextId(Class<S> clazz) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).count() != 0 ? instance.where(clazz).max("id").intValue() + 1 : 1;
    }

    /***
     * Persiste o objeto da classe RealmModel
     * @param realmObject objeto
     */
    public static <S extends RealmModel> void save(S realmObject) {
        Realm instance = Realm.getDefaultInstance();
        instance.beginTransaction();
        instance.insertOrUpdate(realmObject);
        instance.commitTransaction();
        instance.close();
    }

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
     * Deleta o objeto da classe RealmObject com base no id passado por parâmetro
     * @param id id
     * @param clazz classe referente ao tipo do objeto
     */
    public static <S extends RealmObject> void deleteById(long id, Class<S> clazz) {
        Realm instance = Realm.getDefaultInstance();
        instance.beginTransaction();
        instance.where(clazz).equalTo("id", id).findFirst().deleteFromRealm();
        instance.commitTransaction();
        instance.close();
    }

    /***
     * Deleta o objeto da classe
     * @param realmObject objeto
     */
    public static <S extends RealmObject> void delete(S realmObject) {
        Realm instance = Realm.getDefaultInstance();
        instance.beginTransaction();
        realmObject.deleteFromRealm();
        instance.commitTransaction();
        instance.close();
    }

    /***
     * Retorna todos os objetos da classe RealmModel onde os argumentos sejam iguais
     * @param clazz classe referente ao tipo do objeto
     * @param args1 argumento (noma do campo na tabela)
     * @param args2 argumento comparador
     * @return lista de objetos
     */
    public static <S extends RealmModel> RealmResults<S> selectEqualsTo(Class<S> clazz, String args1, String args2) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).equalTo(args1, args2).findAll();
    }

    /***
     * Retorna todos os objetos da classe RealmModel onde o argumento comparador seja menor que o argumento comparado
     * @param clazz classe referente ao tipo do objeto
     * @param args1 argumento (noma do campo na tabela)
     * @param args2 argumento comparador
     * @return lista de objetos
     */
    public static <S extends RealmModel> RealmResults<S> selectLessThan(Class<S> clazz, String args1, int args2) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).lessThan(args1, args2).findAll();
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

    /***
     * Retorna todos os objetos da classe RealmModel onde o argumento comparador seja maior que o argumento comparado
     * @param clazz classe referente ao tipo do objeto
     * @param args1 argumento (noma do campo na tabela)
     * @param args2 argumento comparador
     * @return lista de objetos
     */
    public static <S extends RealmModel> RealmResults<S> selectGreaterThan(Class<S> clazz, String args1, int args2) {
        Realm instance = Realm.getDefaultInstance();
        return instance.where(clazz).greaterThan(args1, args2).findAll();
    }
}