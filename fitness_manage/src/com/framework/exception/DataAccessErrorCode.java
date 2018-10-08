package com.framework.exception;

public class DataAccessErrorCode {
    /**
     * 保存数据出错
     */
    public static final int SAVE_EXCEPTION = 1010;

    /**
     * 更新数据出错
     */
    public static final int UPDATE_EXCEPTION = 1011;

    /**
     * 删除数据出错
     */
    public static final int DELETE_EXCEPTION = 1012;

    /**
     * 查找一条数据出错
     */
    public static final int FIND_EXCEPTION = 1013;

    /**
     * 查询多条数据出错
     */
    public static final int LIST_EXCEPTION = 1014;

    /**
     * 分页查询数据出错
     */
    public static final int FIND_BY_PAGE_EXCEPTION = 1015;

    /**
     * 统计数据数量出错
     */
    public static final int COUNT_EXCEPTION = 1016;

    /**
     * 统计数据最大值出错
     */
    public static final int MAX_EXCEPTION = 1017;

    /**
     * 执行语句出错
     */
    public static final int EXECUTE_EXCEPTION = 1018;

    /**
     * 查找全部出错
     */
    public static final int FIND_ALL_EXCEPTION = 1019;

    /**
     * 存储过程执行错误码
     */
    public static final int EXECUTE_CALL_EXCEPTION = 1020;
}
