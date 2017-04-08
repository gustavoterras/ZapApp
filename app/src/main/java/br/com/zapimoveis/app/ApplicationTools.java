package br.com.zapimoveis.app;

import android.app.Application;

import br.com.zapimoveis.app.dao.EntityMigration;
import br.com.zapimoveis.app.dao.RealmConfiguration;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class ApplicationTools extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration.getInstance().init(this, 1, new EntityMigration());
    }
}
