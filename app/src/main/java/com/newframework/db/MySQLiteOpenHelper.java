package com.newframework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;


/**
 * 数据库Helper类
 *
 * @author Created by luke on 2016/11/24.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        MigrationHelper.migrate(db, UserDao.class, GroupsDao.class, GroupMemberDao.class, ConnectDao.class, AskStatusDao.class, DrugsDao.class, UsefulWordDao.class, PatDao.class, PatTagsDao.class);
    }
}
