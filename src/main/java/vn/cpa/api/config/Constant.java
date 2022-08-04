package vn.cpa.api.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {

    public static String STATIC_CONTEXT = "http://10.30.1.45:9080/resources/";

    public static String UPLOAD_PATH = "/u02/app_beta/mvs_frontend/dev/apache-tomcat-8.5.77/webapps/resources/";

//    public static String STATIC_CONTEXT = "https://mvs.arabicatech.vn/resources/";

//    public static String UPLOAD_PATH = "/home/vcs/public_html/resources/";

    public static String MESSAGE_FILE_LOG = "Tải lên thành công, bạn vui lòng kiểm tra file log để xem thông tin chi tiết!";

    public static double NOT_VALUE = 0d;

    public static int VN = 1;

    public static final int MAX_FILE_SIZE = 50000000;

    public static String[] FILE_EXCEL = {"xls", "xlsx", "xlsm"};

    public static String MESSAGE_ERROR_SIZE_FILE = "Size to large 50Mb!";

    public static String MESSAGE_UP_LOAD_FILE_SUCCESS = "Up load file success!";

    public static String MESSAGE_UP_LOAD_FILE_WRONG_TEMPLATE = "File không đúng định dạng!";

    public static String MESSAGE_FILE_EMPTY = "File không có dữ liệu!";

    public static String MESSAGE_ERROR_REPORT_FILE = "File error";

    public static String MESSAGE_EXIT_CLASS_STUDENT = "Không tồn tại mã lớp hoặc lớp đã bị xóa!";

    public static String MESSAGE_FILE_NOT_LIKE_TEMPLATE = "File không giống mẫu (Template)";

    public static String MESSAGE_FILE_ERROR = "Upload File Error";

    public static String MESSAGE_FILE_TYPE = "Không đúng định dạng file!";

    public static final String TIME_ZONE_DEFAULT = "Asia/Ho_Chi_Minh";

    public static String[] fileReportExcel = {"ReportClassHaveTime.xlsx", "ReportClassNoTime.xlsx", "ReportResult.xlsx", "ReportResultExam.xlsx"};

    public static final String FILE_REPORT_EXCEL_RESULT_EXAM = "ReportResultExam.xlsx";

    public static final String FILE_REPORT_EXCEL_STRUCT_QUESTION = "ImportStructQuestion.xlsx";

    public static final String FILE_0PERATOR_REPORT = "OperatorReport.xlsx";


    public static final String TYPE_OPERATOR_REPORT = "BÁO-CÁO-ĐIỀU-HÀNH";
    public static final String TYPE_CLASS_REPORT = "BÁO-CÁO-LỚP-HỌC";
    public static final String TYPE_RESULT_EXAM_REPORT = "BÁO-CÁO-KẾT-QUẢ-THI";
    public static final String TYPE_RESULT_LEARN_REPORT = "BÁO-CÁO-KẾT-QUẢ-LỚP-HỌC";
    public static final String TYPE_AFTER_EDUCATION_REPORT = "BÁO-CÁO-KẾT-QUẢ";
    public static final String TYPE_EDUCATION_REPORT = "BÁO-CÁO-ĐÀO-TẠO";
    public static final String TYPE_ACCOUNTING_REPORT = "BÁO-CÁO-CHI-PHÍ-ĐÀO-TẠO";

    public static final Long TYPE_STUDY_TIME_ACTIVE = 1L;
    public static final Long TYPE_STUDY_TIME_INACTIVE = 2L;

}
