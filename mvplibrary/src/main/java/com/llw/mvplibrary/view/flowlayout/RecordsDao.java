package com.llw.mvplibrary.view.flowlayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 历史记录搜索操作类
 */
public class RecordsDao {
    private final String TABLE_NAME = "records";
    private SQLiteDatabase recordsDb;
    private RecordSQLiteOpenHelper recordHelper;
    private NotifyDataChanged mNotifyDataChanged;
    private String mUsername;

    public RecordsDao(Context context, String username) {
        recordHelper = new RecordSQLiteOpenHelper(context);
        mUsername = username;
    }

    public interface NotifyDataChanged {
        void notifyDataChanged();
    }

    /**
     * 设置数据变化监听
     */
    public void setNotifyDataChanged(NotifyDataChanged notifyDataChanged) {
        mNotifyDataChanged = notifyDataChanged;
    }

    /**
     * 移除数据变化监听
     */
    public void removeNotifyDataChanged() {
        if (mNotifyDataChanged != null) {
            mNotifyDataChanged = null;
        }
    }

    private synchronized SQLiteDatabase getWritableDatabase() {
        return recordHelper.getWritableDatabase();
    }

    private synchronized SQLiteDatabase getReadableDatabase() {
        return recordHelper.getReadableDatabase();
    }

    /**
     * 如果考虑操作频繁可以到最后不用数据库时关闭
     * <p>
     * 关闭数据库
     */
    public void closeDatabase() {
        if (recordsDb != null) {
            recordsDb.close();
        }
    }

    /**
     * 添加搜索记录
     *
     * @param record 记录
     */
    public void addRecords(String record) {
        //如果这条记录没有则添加，有则更新时间
        int recordId = getRecordId(record);
        try {
            recordsDb = getReadableDatabase();
            if (-1 == recordId) {
                ContentValues values = new ContentValues();
                values.put("username", mUsername);
                values.put("keyword", record);
                //添加搜索记录
                recordsDb.insert(TABLE_NAME, null, values);
            } else {
                Date d = new Date();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //更新搜索历史数据时间
                ContentValues values = new ContentValues();
                values.put("time", sdf.format(d));
                recordsDb.update(TABLE_NAME, values, "_id = ?", new String[]{Integer.toString(recordId)});
            }
            if (mNotifyDataChanged != null) {
                mNotifyDataChanged.notifyDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否含有该搜索记录
     *
     * @param record 记录
     * @return true | false
     */
    public boolean isHasRecord(String record) {
        boolean isHasRecord = false;
        Cursor cursor = null;
        try {
            recordsDb = getReadableDatabase();
            cursor = recordsDb.query(TABLE_NAME, null, "username = ?", new String[]{mUsername}, null, null, null);
            while (cursor.moveToNext()) {
                if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("keyword")))) {
                    isHasRecord = true;
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                //关闭游标
                cursor.close();
            }
        }
        return isHasRecord;
    }

    /**
     * 判断是否含有该搜索记录
     *
     * @param record 记录
     * @return id
     */
    public int getRecordId(String record) {
        int isHasRecord = -1;
        Cursor cursor = null;
        try {
            recordsDb = getReadableDatabase();
            cursor = recordsDb.query(TABLE_NAME, null, "username = ?", new String[]{mUsername}, null, null, null);
            while (cursor.moveToNext()) {
                if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("keyword")))) {
                    isHasRecord = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                //关闭游标
                cursor.close();
            }
        }
        return isHasRecord;
    }

    /**
     * 获取当前用户全部搜索记录
     *
     * @return 记录集合
     */
    public List<String> getRecordsList() {
        List<String> recordsList = new ArrayList<>();
        Cursor cursor = null;
        try {
            recordsDb = getReadableDatabase();
            cursor = recordsDb.query(TABLE_NAME, null, "username = ?", new String[]{mUsername}, null, null, "time desc");
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("keyword"));
                recordsList.add(name);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                //关闭游标
                cursor.close();
            }
        }
        return recordsList;
    }

    /**
     * 获取指定数量搜索记录
     *
     * @return 记录集合
     */
    public List<String> getRecordsByNumber(int recordNumber) {
        List<String> recordsList = new ArrayList<>();
        if (recordNumber < 0) {
            throw new IllegalArgumentException();
        } else if (0 == recordNumber) {
            return recordsList;
        } else {
            Cursor cursor = null;
            try {
                recordsDb = getReadableDatabase();
                cursor = recordsDb.query(TABLE_NAME, null, "username = ?", new String[]{mUsername}, null, null, "time desc limit " + recordNumber);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("keyword"));
                    recordsList.add(name);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    //关闭游标
                    cursor.close();
                }
            }
        }
        return recordsList;
    }

    /**
     * 模糊查询
     *
     * @param record 记录
     * @return 返回类似记录
     */
    public List<String> querySimlarRecord(String record) {
        List<String> similarRecords = new ArrayList<>();
        Cursor cursor = null;
        try {
            recordsDb = getReadableDatabase();
            cursor = recordsDb.query(TABLE_NAME, null, "username = ? and keyword like '%?%'", new String[]{mUsername, record}, null, null, "order by time desc");
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("keyword"));
                similarRecords.add(name);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                //关闭游标
                cursor.close();
            }
        }
        return similarRecords;
    }

    /**
     * 清除指定用户的搜索记录
     */
    public void deleteUsernameAllRecords() {
        try {
            recordsDb = getWritableDatabase();
            recordsDb.delete(TABLE_NAME, "username = ?", new String[]{mUsername});
            if (mNotifyDataChanged != null) {
                mNotifyDataChanged.notifyDataChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TABLE_NAME, "清除所有历史记录失败");
        } finally {
        }
    }

    /**
     * 清空数据库所有的历史记录
     */
    public void deleteAllRecords() {
        try {
            recordsDb = getWritableDatabase();
            recordsDb.execSQL("delete from " + TABLE_NAME);
            if (mNotifyDataChanged != null) {
                mNotifyDataChanged.notifyDataChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TABLE_NAME, "清除所有历史记录失败");
        } finally {
        }
    }

    /**
     * 通过id删除记录
     *
     * @param id 记录id
     * @return 返回删除id
     */
    public int deleteRecord(int id) {
        int d = -1;
        try {
            recordsDb = getWritableDatabase();
            d = recordsDb.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
            if (mNotifyDataChanged != null) {
                mNotifyDataChanged.notifyDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TABLE_NAME, "删除_id：" + id + "历史记录失败");
        }
        return d;
    }

    /**
     * 通过记录删除记录
     *
     * @param record 记录
     */
    public int deleteRecord(String record) {
        int recordId = -1;
        try {
            recordsDb = getWritableDatabase();
            recordId = recordsDb.delete(TABLE_NAME, "username = ? and keyword = ?", new String[]{mUsername, record});
            if (mNotifyDataChanged != null) {
                mNotifyDataChanged.notifyDataChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TABLE_NAME, "清除所有历史记录失败");
        }
        return recordId;
    }
}

