package edu.eschina.mall.model;

/**
 * 所有模型的id父类
 */
public class BaseId extends Base{
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
