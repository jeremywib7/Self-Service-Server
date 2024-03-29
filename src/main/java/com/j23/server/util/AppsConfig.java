package com.j23.server.util;

public final class AppsConfig {
  public static final String appName = "selfservice/";

  public static boolean IS_PRODUCTION = false;

  public static final String DEV_FRONT_END_URL = "http://localhost:4201/";

  public static final String DEV_SERVER_URL = "http://localhost:8080/";
  public static final String PROD_SERVER_URL = "https://selfserviceonline.herokuapp.com/";

  // firebase storage

  public static final String PRODUCT_FOLDER = "Product/";
  public static final String USER_FOLDER = "User/";
  public static final String REPORT_FOLDER = "Report/";

  // report

  public static final String USER_REPORT_TITLE = "USER REPORT";
  public static final String USER_REPORT_PATH = "src/main/resources/reports/User.jrxml";

  public static final String SALES_REPORT_TITLE = "SALES REPORT";
  public static final String SALES_REPORT_PATH = "/reports/sales_report/Sales_Report.jrxml";

  // CONFIGURATION
  public static String MAIN_FRONTEND_URL = DEV_FRONT_END_URL;
  public static String MAIN_SERVER_URL = DEV_SERVER_URL;

  // MIDTRANS
  public static final String sandboxServerKey = "SB-Mid-server-TTNlxI6thXHFeOeHRyj7dj_B";
  public static final String sandboxClientKey = "SB-Mid-client-DhRq3WG08VqEtWo8";
  public static final String secondServerKey = "SB-Mid-server-9Nm5c-HJE65AjLNtTX-bRjqm";
  public static final String secondClientKey = "SB-Mid-client-GtbzK39rvs5El-bC";

  public static final String sandboxCreatorKey = "IRIS-330198f0-e49d-493f-baae-585cfded355d";
  public static final String sandboxApproverKey = "IRIS-1595c12b-6814-4e5a-bbbb-9bc18193f47b";

}
