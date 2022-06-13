package com.retro.lab.medicina_admin.Services;

public class URLS {

    private static final String ROOT_URL = "http://jalald.000webhostapp.com/api/api.php?apicall=";
    private static final String ROOT_NEW = "http://jalald.000webhostapp.com/api/admin_api.php?apicall=";

    public static final String URL_LOGIN_ADMIN = ROOT_NEW + "admin_login";

    public static final String GET_PHARMACY = ROOT_NEW + "getpharmace";

    public static final String NEW_PHARMACY = ROOT_NEW + "new_pharmace";
    public static final String NEW_MEDICINE = ROOT_NEW + "new_medicin";

    public static final String URL_GET_All_Medicines = ROOT_URL+"index";
    public static final String URL_GET_All_PHARMACIES = ROOT_NEW+"index";
    public static final String URL_GET_All_USERS = ROOT_NEW + "index_user";

    public static final String URL_UPDATE_PHARMACIES = ROOT_NEW + "update_pharmace";
    public static final String URL_UPDATE_MEDICINES = ROOT_NEW + "update_medicin";

    public static final String URL_DELETE_PHARMACY = ROOT_NEW+"delet_pharmace";
    public static final String URL_DELETE_MEDICINE = ROOT_NEW+"delet_medicin";

}
