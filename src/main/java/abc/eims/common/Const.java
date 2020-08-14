package abc.eims.common;

/**
 * 存放静态常量的类，用来实现权限管理功能。
 *
 * @author wangzhe
 * @date 2020/8/10 8:40
 */
public class Const {

    /**系统管理员对应的JSON字符串*/
    public static final String SYSTEM_ADMINISTRATOR = "[\n" +
            "\t{\n" +
            "\t\t\"title\": \"员工管理\",\n" +
            "\t\t\"icon\": \"&#xe655;\",\n" +
            "\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"员工信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userList_0.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"添加员工\",\n" +
            "\t\t\t\t\"icon\": \"&#xe665;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userAdd_0.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"考勤管理\",\n" +
            "\t\t\"icon\": \"&#xe634;\",\n" +
            "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"考勤信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"个人中心\",\n" +
            "\t\t\"icon\": \"&#xe716;\",\n" +
            "\t\t\"href\": \"\",\n" +
            "\t\t\"spread\": false,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"个人资料\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"修改密码\",\n" +
            "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "]";

    /**普通管理员对应的JSON字符串*/
    public static final String ADMINISTRATOR = "[\n" +
            "\t{\n" +
            "\t\t\"title\": \"员工管理\",\n" +
            "\t\t\"icon\": \"&#xe655;\",\n" +
            "\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"员工信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userList_1.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"添加员工\",\n" +
            "\t\t\t\t\"icon\": \"&#xe665;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userAdd_1.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"考勤管理\",\n" +
            "\t\t\"icon\": \"&#xe634;\",\n" +
            "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"考勤信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"个人中心\",\n" +
            "\t\t\"icon\": \"&#xe716;\",\n" +
            "\t\t\"href\": \"\",\n" +
            "\t\t\"spread\": false,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"个人资料\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"修改密码\",\n" +
            "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "]";

    /**普通员工对应的JSON字符串*/
    public static final String CUSTOMER = "[\n" +
            "\t{\n" +
            "\t\t\"title\": \"员工管理\",\n" +
            "\t\t\"icon\": \"&#xe655;\",\n" +
            "\t\t\"href\": \"../resources/page/user/userList_2.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"员工信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userList_2.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"考勤管理\",\n" +
            "\t\t\"icon\": \"&#xe634;\",\n" +
            "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
            "\t\t\"spread\": true,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"考勤信息\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/news/newsList_2.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"title\": \"个人中心\",\n" +
            "\t\t\"icon\": \"&#xe716;\",\n" +
            "\t\t\"href\": \"\",\n" +
            "\t\t\"spread\": false,\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"个人资料\",\n" +
            "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"修改密码\",\n" +
            "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
            "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
            "\t\t\t\t\"spread\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "]";

}
