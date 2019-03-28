package wangsen.sxcz.viewpager_test.expandable;

/**
 * Created by 王森 on 2018/11/29.
 */

/**
 * 封装类，封装属性
 *
 * Alt+Insert组合键唤出Generate菜单，可生成Constructor(构造方法)及Getter and Setter(get，set)方法
 */
public class ChildrenBean {
    private Integer id;
    private String name;

    public ChildrenBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
