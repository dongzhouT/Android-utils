package com.haierac.biz.cp.cloudplatformandroid.utils.dbhelper;

import android.database.sqlite.SQLiteDatabase;

import com.haierac.biz.cp.cloudplatformandroid.MyApplication;
import com.cloud.dao.DaoMaster;
import com.cloud.dao.DaoSession;

/**
 * Created by Administrator on 2016/8/16.
 */
public class CPDatabaseLoader {
    private static final String DBNAME = "cloudDB";
    private static DaoSession session;

    public static DaoSession getSession() {
        if (session == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getContext(),
                    DBNAME, null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster master = new DaoMaster(db);
            session = master.newSession();
        }
        return session;
    }
}
