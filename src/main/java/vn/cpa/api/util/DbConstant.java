package vn.cpa.api.util;

public class DbConstant {

    // Role table
    public static final int ROLE_ID_ADMIN = 1;
    public static final int ROLE_ID_USER = 2;

    // Status
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 2;
    public static final int STATUS_DELETED = 3;

    // First login
    public static final int FIRST_LOGIN = 1;
    public static final int NOT_FIRST_LOGIN = 2;

    // Type question code
    public static final String ONE_CHOICE = "ONE_CHOICE";
    public static final String MULTI_CHOICE = "MULTI_CHOICE";
    public static final String FILL_BLANK = "FILL_BLANK";
    public static final String MATCHING = "MATCHING";
    public static final String ESSAY = "ESSAY";

    // Level question code
    public static final String LEVEL_NORM = "LEVEL_NORM";
    public static final String LEVEL_HARD = "LEVEL_HARD";
    public static final String LEVEL_EASY = "LEVEL_EASY";

    // Text for reason requirement leave
    public static final int LEAVE_FOR_OTHER = 0;
    public static final int LEAVE_FOR_SLICK = 1;
    public static final int LEAVE_FOR_PRIVATE_WORK = 2;

    // Status for requirement leave
    public static final int WAIT_FOR_APPROVAL = 0;
    public static final int APPROVAL = 1;

    // Status for process notify
    public static final int NOT_PROCESS = 0;
    public static final int PROCESSING = 1;

    // Status for leave
    public static final long NOT_JOIN = -1;
    public static final long NOT_LEAVE = 0;
    public static final long LEAVED = 1;

    // Content for leaver
    public static final String LEAVE_FOR_STICK_STR = "Nghỉ do đau ốm";
    public static final String LEAVE_FOR_WORK_STR = "Gia đình có công việc";

    // Study Result
    public static final long IS_LEAVE_NEW_STUDY_RESULT = -1;
    public static final long IS_FINISH_STUDY_RESULT = 0;
    public static final long FINISH_STUDY_RESULT = 1;
    public static final long TYPE_UNIT_STUDY_RESULT = 2;
    public static final long TYPE_DOC_STUDY_RESULT = 1;

    // ExamStudent
    public static final long STATUS_ACTIVE_EXAM_STUDENT = 1;
    public static final long STATUS_IN_ACTIVE_EXAM_STUDENT = 0;
    public static final long IS_FINISHED_EXAM_STUDENT = 1;
    public static final long IS_UNFINISHED_EXAM_STUDENT = 0;

    // Status is required
    public static final long STATUS_REQUIRED = 1;

    // Status is reset result
    public static final int STATUS_RESET_RESULT_ALL = 1;
    public static final int STATUS_RESET_RESULT_PROGRAM = 2;

    // Type class
    public static final int CLASS_HAVE_TIME = 2;
}
