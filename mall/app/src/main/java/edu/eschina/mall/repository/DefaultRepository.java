package edu.eschina.mall.repository;


/**
 *本项目默认仓库
 * 主要是从网络，数据库获取数据
 * 目前项目大部分操作都在这里
 * <p>如果项目每个模块之间有明显的区别，例如：有商场，有歌单，那就放到对应的模块的Repository
 */
public class DefaultRepository {
    private static DefaultRepository defaultRepository;


    private DefaultRepository(){

    }


    /**
     * 返回对象的唯一实例
     * <p>
     *     单例设计模式
     *     由于移动端很少有高并发
     *     所以这个就是简单判断
     * @return
     */
    public synchronized static DefaultRepository getInstance(){
        if(defaultRepository==null){
            defaultRepository=new DefaultRepository();
        }
        return defaultRepository;
    }
}
