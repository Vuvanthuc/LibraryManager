package vn.cpa.api.util;

public class ErrorCode {

    public static final int PARAMETER_EXISTED = -1;
    public static final int FILE_TOO_LARGE = 417;
    public static final int OK = 0;
    public static final int CONFLICT = 409;
    public static final int NOT_FOUND = 404;
    public static final long REMOVED = -1;
    public static final int NOT_A_NUMBER = 1;
    public static final long INACTIVE = 0;
    public static final long ACTIVE = 1;
    public static final long STATUS = 1;
    public static final long FINISHED = 1;
    public static final long UN_FNNISHED = 0;
    public static final long EDU_UNIT_TEST = 2;

    //dinh nghia error code
    public static final int NOT_EXIST_CATEGORY_CLASS = 2;
    public static final int NOT_NULL_VALUE = 2;
    public static final int DUPLICATE_VALUE = 3;
    public static final int INTERNAL_ERROR = 500;

    //approvol
    public static final long APPROVAL_TO_CLASS = 1;
    public static final long UN_APPROVAL_TO_CLASS = 0;

    //leard
    public static final long FINISHED_LEARN_CLASS = 1;
    public static final long UNFINISHED_LEARN_CLASS = 0;

    //type question
    public static final long QUESTION_FOR_LOGIN = 1;
    public static final long QUESTION_FOR_EXAM = 2;

    //isfinised
    public static final long FINISHED_CLASS = 1;
    public static final long UNFINISHED_CLASS = 0;

    // IS TEST STUDENT
    public static final long IS_TEST = 0;
    public static final long IS_TESTED = 1;

    //check cau hoi theo yeu cau
    public static final long CORRECT = 1;
    public static final long INCORECT = 0;

    //
    public static final long LEARNED = 1;
    public static final long EDU_DOC = 2;
}

